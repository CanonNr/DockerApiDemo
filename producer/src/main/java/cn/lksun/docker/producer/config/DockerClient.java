package cn.lksun.docker.producer.config;

import org.springframework.stereotype.Component;

public class DockerClient {

    public String DOMAIN = "http://127.0.0.1";

    public String PORT = "4789";

    public DockerClient setHost(String domain) {
        this.DOMAIN = domain;
        return this;
    }

    public DockerClient setPort(String port) {
        this.PORT = port;
        return this;
    }

    public String getUrl() {
        StringBuilder url = new StringBuilder();
        url.append(DOMAIN);
        if (PORT.length() > 0) {
            url.append(":").append(PORT);
        }
        return url.toString();
    }
}
