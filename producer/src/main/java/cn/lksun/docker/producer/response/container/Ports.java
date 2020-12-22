package cn.lksun.docker.producer.response.container;

import lombok.Data;

@Data
public class Ports {

    public String IP;

    public Integer PrivatePort;

    public Integer PublicPort;

    public String Type;
}
