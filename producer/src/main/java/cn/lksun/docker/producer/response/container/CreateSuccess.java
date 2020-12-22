package cn.lksun.docker.producer.response.container;

import lombok.Data;

import java.util.LinkedList;

@Data
public class CreateSuccess {

    public String Id;

    public LinkedList<Object> Warnings;

}
