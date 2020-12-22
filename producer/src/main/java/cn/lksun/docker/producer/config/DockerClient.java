package cn.lksun.docker.producer.config;

import org.springframework.stereotype.Component;

public class DockerClient {

    private String DOMAIN = "http://127.0.0.1";

    private String PORT = "4789";

    private String API_VERSION;

    public DockerClient setHost(String domain) {
        this.DOMAIN = domain;
        return this;
    }

    public DockerClient setPort(String port) {
        this.PORT = port;
        return this;
    }

    public DockerClient setApiVersion(String port) {
        this.PORT = port;
        return this;
    }

    public String getUrl() {
        StringBuilder url = new StringBuilder();
        url.append(DOMAIN);

        if (PORT.length() > 0) {
            url.append(":").append(PORT);
        }

        if (API_VERSION.length() > 0) {
            url.append("/").append(API_VERSION);
        }

        return url.toString();
    }
}
