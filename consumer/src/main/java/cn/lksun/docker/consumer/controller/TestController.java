package cn.lksun.docker.consumer.controller;

import cn.lksun.docker.producer.exception.DockerClientConfigException;
import cn.lksun.docker.producer.exception.DockerServiceException;
import cn.lksun.docker.producer.request.CreateContainer;
import cn.lksun.docker.producer.response.container.CreateSuccess;
import cn.lksun.docker.producer.service.DockerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    DockerService dockerService;

    @GetMapping("create")
    public String create() throws InterruptedException {
        CreateContainer create = new CreateContainer("redis",null,null,null,null,null);
        try {
            CreateSuccess createSuccess = dockerService.create(create);
            return createSuccess.Id;
        } catch (DockerServiceException e) {
            return e.getMessage();
        } catch (DockerClientConfigException e) {
            return "靶机服务未启动";
        }
    }



    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") String id){
        try {
            dockerService.delete(id);
        } catch (DockerServiceException e) {
            return e.getMessage();
        } catch (DockerClientConfigException e) {
            return "靶机服务未启动";
        }
        return "ok";
    }


}
