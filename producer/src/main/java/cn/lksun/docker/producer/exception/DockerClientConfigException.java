package cn.lksun.docker.producer.exception;

public class DockerClientConfigException extends Exception{
    public DockerClientConfigException(String message) {
        super(message);
    }

    public DockerClientConfigException() {

    }
}
