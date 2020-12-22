package cn.lksun.docker.consumer;

import cn.lksun.docker.producer.exception.DockerClientConfigException;
import cn.lksun.docker.producer.exception.DockerServiceException;
import cn.lksun.docker.producer.response.container.ContainerInfo;
import cn.lksun.docker.producer.service.DockerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@SpringBootTest
class ConsumerApplicationTests {
    @Resource
    DockerService dockerService;

    @Test
    void contextLoads() throws DockerServiceException, DockerClientConfigException {
//        HashMap<String, Object> exposedPorts = new HashMap<>();
//        HostConfig hostConfig = new HostConfig();
//        exposedPorts.put("80/tcp",null);
//        LinkedList<HashMap<String,String>> portLists = new LinkedList<>();
//        HashMap<String, String> portConfig = new HashMap<>();
//        portConfig.put("80/tcp",null);
//        portLists.add(portConfig);
//        hostConfig.PortBindings.put("80/tcp",portLists);
//
//        CreateContainer create = new CreateContainer("nginx",null,null,exposedPorts,null,hostConfig);
//        try {
//            CreateSuccess createSuccess = dockerService.create(create);
//            System.out.println(createSuccess);
//        } catch (DockerServiceException e) {
//            System.out.println(e.getMessage());
//        } catch (DockerClientConfigException e) {
//            System.out.println("error");
//        }

        ContainerInfo container = dockerService.getContainerById("b93f362f480d760efc82720f4896017f6721c5fecfce6708f27904bd1c5eed20");
        System.out.println(container);

        Integer publicPort = dockerService.getPublicPort(container.Ports, 80);
        System.out.println(publicPort);
    }
}
