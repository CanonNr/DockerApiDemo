package cn.lksun.docker.producer.request;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;

@Data
public class CreateContainer implements HttpRequest{

    /**
     * 镜像名称
     */
    private String Image;

    /**
     * 以 ["VAR=value", ...] 的格式
     */
    private LinkedList<String> Env;

    /**
     * 以指定的字符串或字符串数组形式运行的命令。
     */
    private LinkedList<String> Cmd;

    /**
     * {"<port>/<tcp|udp|sctp>": {}}
     * 例如:  22/tcp: {}
     */
    private HashMap<String, HashMap<String, String>> ExposedPorts;

    /**
     * 挂载路径
     */
    private HashMap<String, HashMap<String, String>> Volumes;

    public CreateContainer(String Image,
                           LinkedList<String> Env,
                           LinkedList<String> Cmd,
                           HashMap<String, HashMap<String, String>> ExposedPorts,
                           HashMap<String, HashMap<String, String>> Volumes) {
        this.Image = Image;
        this.Env = Env;
        this.Cmd = Cmd;
        this.ExposedPorts = ExposedPorts;
        this.Volumes = Volumes;
    }

    public CreateContainer() {

    }
}
