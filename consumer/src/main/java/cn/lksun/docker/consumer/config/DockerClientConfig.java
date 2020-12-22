package cn.lksun.docker.consumer.config;

import cn.lksun.docker.producer.config.DockerClient;
import cn.lksun.docker.producer.service.DockerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {

    public DockerClient dockerClient(){
        return new DockerClient().
                setHost("http://172.16.2.30").
                setPort("4789").
                setApiVersion("v1.26");
    }

    @Bean
    public DockerService dockerService(){
        return new DockerService().setClient(dockerClient());
    }
}
