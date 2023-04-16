package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Like;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface LikeService {
    int addLike(Like like);

    int deleteLikeById(int id);

    int updateLike(Like like);

    int selectCount();

    List<Like> selectLikeAll(int page, int limit);

    List<Like> selectLikeById(int id);

    int dishesLike(int userId, int dishesId);
    int postLike(int userId, int postId);

    int dishesLikeNo(int userId, int dishesId);
    int postLikeNo(int userId, int postId);
}
