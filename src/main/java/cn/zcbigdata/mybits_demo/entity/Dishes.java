package cn.zcbigdata.mybits_demo.entity;

import lombok.Data;

@Data
public class Dishes {
    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private Integer goodNumber;
    private Integer badNumber;
    private Integer collectNumber;
    private Integer commentNumber;
    private Integer schoolId;
    private String link;
}
