package cn.lksun.docker.producer.request;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class HostConfig {
    public HashMap<String, List<HashMap<String,String>>> PortBindings = new HashMap<String, List<HashMap<String,String>>>();
}
