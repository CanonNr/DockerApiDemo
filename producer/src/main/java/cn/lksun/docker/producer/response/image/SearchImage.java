package cn.lksun.docker.producer.response.image;

import lombok.Data;

@Data
public class SearchImage {

    public String name;

    public String description;

    public boolean is_official;

    public boolean is_automated;

    public Integer star_count;

}
