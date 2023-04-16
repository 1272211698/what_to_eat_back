package cn.zcbigdata.mybits_demo.entity.vo;

import lombok.Data;

@Data
public class PostVo {
    private Integer id;
    private String description;
    private String link;
    private Integer type;
    private Integer likeNumber;
    private Integer collectNumber;
    private Integer commentNumber;
    private Integer userId;
    private Integer dishesId;
    private String name;
    private String nickName;
}
