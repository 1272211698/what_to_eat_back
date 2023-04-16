package cn.zcbigdata.mybits_demo.entity.vo;

import lombok.Data;

@Data
public class CommentVo {
    private Integer userId;
    private String content;
    private String nickName;
}
