package cn.lksun.docker.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

public class RestTemplateConfig {
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
