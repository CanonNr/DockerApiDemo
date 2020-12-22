package cn.lksun.docker.producer.response.container;

import lombok.Data;

import java.util.List;

@Data
public class ContainerInfo {
    public String Id;

    public List<String> Names;

    public String Image;

    public String ImageID;

    public String Command;

    public Integer Created;

    public List<Ports> Ports;

}

