package cn.zcbigdata.mybits_demo.entity;

import lombok.Data;

@Data
public class Post {
    private Integer id;
    private String description;
    private String link;
    private Integer type;
    private Integer likeNumber;
    private Integer collectNumber;
    private Integer commentNumber;
    private Integer userId;
    private Integer dishesId;
}
