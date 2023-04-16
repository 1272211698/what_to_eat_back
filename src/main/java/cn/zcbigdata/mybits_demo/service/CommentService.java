package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Comment;
import cn.zcbigdata.mybits_demo.entity.vo.CommentVo;

import java.util.List;
import java.util.Map;

public interface CommentService {
    int addComment(Comment comment);

    int deleteCommentById(int id);

    int updateComment(Comment comment);

    int selectCount();

    List<Comment> selectCommentAll(int page, int limit);

    List<Comment> selectCommentById(int id);

    List<CommentVo> findCommentsByDishesId(int dishesId);
    List<CommentVo> findCommentsByPostId(int postId);

    int dishesComment(int userId, int dishesId, String content);
    int postComment(int userId, int postId, String content);

}
