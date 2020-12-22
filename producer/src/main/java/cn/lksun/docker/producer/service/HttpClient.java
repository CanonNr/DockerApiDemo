package cn.lksun.docker.producer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

public class HttpClient {

    RestTemplate restTemplate = new RestTemplate();

    public HttpEntity<Map<Object, Object>> getHttpEntity(Map<Object,Object> map){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Map<Object, Object>>(map, headers);
    }

    public String postForMap(String url,Map<Object,Object> map){
        HttpEntity<Map<Object, Object>> request = getHttpEntity(map);
        ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        return entity.getBody();
    }

    public void delete(String url){
        restTemplate.delete(url);
    }

}
