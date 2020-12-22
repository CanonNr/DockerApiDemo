package cn.lksun.docker.consumer;


import cn.lksun.docker.producer.exception.DockerServiceException;
import cn.lksun.docker.producer.request.CreateContainer;
import cn.lksun.docker.producer.response.container.CreateSuccess;
import cn.lksun.docker.producer.service.DockerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class ConsumerApplicationTests {
    @Resource
    DockerService dockerService;

    @Test
    void contextLoads() {
        CreateContainer create = new CreateContainer("redis",null,null,null,null);
        try {
            CreateSuccess createSuccess = dockerService.create(create);
            System.out.println(createSuccess.Id);
            System.out.println(dockerService.delete(createSuccess.Id));
        } catch (DockerServiceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
