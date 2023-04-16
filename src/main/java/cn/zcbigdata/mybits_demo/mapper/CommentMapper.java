package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Comment;
import cn.zcbigdata.mybits_demo.entity.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CommentMapper {
    int addComment(Comment comment);

    int deleteCommentById(int id);

    int updateComment(Comment comment);

    int selectCount();

    List<Comment> selectCommentAll(Map<String, Integer> map);

    List<Comment> selectCommentById(int id);
    List<CommentVo> findCommentsByDishesId(int dishesId);
    List<CommentVo> findCommentsByPostId(int postId);

    int dishesComment(Map<String, Object> params);

    int updateDishesCommentNumber(Map<String, Object> params);
    int postComment(Map<String, Object> params);

    int updatePostCommentNumber(Map<String, Object> params);
}
