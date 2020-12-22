package cn.lksun.docker.producer.service;
import cn.lksun.docker.producer.config.DockerClient;
import cn.lksun.docker.producer.exception.DockerClientConfigException;
import cn.lksun.docker.producer.exception.DockerServiceException;
import cn.lksun.docker.producer.request.CreateContainer;
import cn.lksun.docker.producer.response.container.ContainerInfo;
import cn.lksun.docker.producer.response.container.CreateSuccess;
import cn.lksun.docker.producer.response.container.Ports;
import cn.lksun.docker.producer.util.ObjectFormat;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.net.URLEncoder;
import java.util.*;

@Slf4j
public class DockerService {

    private final String DOCKER_CLIENT_CONFIG_ERROR = "靶机服务未启动或配置错误";

    DockerClient dockerClient;

    HttpClient httpClient = new HttpClient();

    public DockerService setClient(DockerClient dockerClient){
        this.dockerClient = dockerClient;
        return this;
    }

    /**
     * 创建容器
     * @return String
     */
    public CreateSuccess create(CreateContainer create) throws DockerServiceException, DockerClientConfigException {
        log.info("(1/3) Docker Create -  Object : {}",create);
        String url = dockerClient.getUrl() + "/containers/create";
        Map<Object, Object> map = new ObjectFormat().ObjectToMap(create);
        log.info("(2/3) Docker Create - Map : {}",map);
        try{
            String result = httpClient.postForMap(url, map);
            CreateSuccess createSuccess = JSON.parseObject(result, CreateSuccess.class);
            this.start(createSuccess.Id);
            return createSuccess;
        }catch (HttpClientErrorException e){
            log.error("Container Create Error : {},{}",e.getRawStatusCode(),e.getResponseBodyAsString());
            throw new DockerServiceException("创建失败");
        }catch (ResourceAccessException e){
            log.error(DOCKER_CLIENT_CONFIG_ERROR);
            throw new DockerClientConfigException();
        }
    }

    /**
     * 启动容器
     * @return String
     */
    private void start(String id) throws DockerServiceException, DockerClientConfigException {
        log.info("(3/3) Docker Create Start - Id : {}",id);
        String url = dockerClient.getUrl() + "/containers/"+id+"/start";
        try{
            httpClient.postForMap(url,null);
        }catch (HttpClientErrorException e){
            log.error("Container Start Error : {},{}",e.getRawStatusCode(),e.getResponseBodyAsString());
            throw new DockerServiceException("启动失败");
        }catch (ResourceAccessException e){
            log.error(DOCKER_CLIENT_CONFIG_ERROR);
            throw new DockerClientConfigException();
        }
    }

    /**
     * 删除
     */
    public void delete(String id) throws DockerServiceException, DockerClientConfigException {
        log.info("Docker Delete - Id : {}",id);
        String stoUrl = dockerClient.getUrl() + "/containers/"+id+"/stop";
        String deleteUrl = dockerClient.getUrl() + "/containers/"+id;
        try{
            httpClient.postForMap(stoUrl,null);
            httpClient.delete(deleteUrl);
        }catch (HttpClientErrorException e){
            log.error("Container Delete Error : {},{}",e.getRawStatusCode(),e.getResponseBodyAsString());
            throw new DockerServiceException("删除失败");
        }catch (ResourceAccessException e){
            log.error(DOCKER_CLIENT_CONFIG_ERROR);
            throw new DockerClientConfigException();
        }
    }

    public ContainerInfo getContainerById(String id) throws DockerServiceException, DockerClientConfigException {
        log.info("Docker Get Container - Id : {}",id);
        String filters = URLEncoder.encode("filters={\"id\":[\""+id+"\"]}");
        String url = dockerClient.getUrl() + "/containers/json?"+filters;
        try{
            System.out.println(url);
            String result = httpClient.get(url);
            log.info(result);
            List<ContainerInfo> containerList = JSON.parseArray(result, ContainerInfo.class);
            if(!containerList.isEmpty() && Objects.nonNull(containerList.get(0))){
                return containerList.get(0);
            }
            throw new DockerServiceException("获取失败");
        }catch (HttpClientErrorException e){
            log.error("Container Delete Error : {},{}",e.getRawStatusCode(),e.getResponseBodyAsString());
            throw new DockerServiceException("获取失败");
        }catch (ResourceAccessException e){
            log.error(DOCKER_CLIENT_CONFIG_ERROR);
            throw new DockerClientConfigException();
        }
    }

    public Integer getPublicPort(List<Ports> ports,Integer privatePort) throws DockerServiceException {
        for (Ports port:ports) {
            if (port.PrivatePort.equals(privatePort)){
                return port.PublicPort;
            }
        }
        return null;
    }

}
