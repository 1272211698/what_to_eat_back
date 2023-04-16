package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Comment;
import cn.zcbigdata.mybits_demo.entity.vo.CommentVo;
import cn.zcbigdata.mybits_demo.mapper.ClassificationMapper;
import cn.zcbigdata.mybits_demo.mapper.CommentMapper;
import cn.zcbigdata.mybits_demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addComment(Comment comment) {
        return this.commentMapper.addComment(comment);
    }

    @Override
    public int deleteCommentById(int id) {
        return this.commentMapper.deleteCommentById(id);
    }

    @Override
    public int updateComment(Comment comment) {
        return this.commentMapper.updateComment(comment);
    }

    @Override
    public int selectCount() {
        return this.commentMapper.selectCount();
    }

    @Override
    public List<Comment> selectCommentAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.commentMapper.selectCommentAll(map);
    }

    @Override
    public List<Comment> selectCommentById(int id) {
        return this.commentMapper.selectCommentById(id);
    }

    @Override
    public List<CommentVo> findCommentsByDishesId(int dishesId) {
        return commentMapper.findCommentsByDishesId(dishesId);
    }

    @Override
    public List<CommentVo> findCommentsByPostId(int postId) {
        return this.commentMapper.findCommentsByPostId(postId);
    }

    @Override
    public int dishesComment(int userId, int dishesId,String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        params.put("content", content);
        int f1 = commentMapper.dishesComment(params);
        int f2 = commentMapper.updateDishesCommentNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int postComment(int userId, int postId, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", postId);
        params.put("content", content);
        int f1 = commentMapper.postComment(params);
        int f2 = commentMapper.updatePostCommentNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }
}
