package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Like;
import cn.zcbigdata.mybits_demo.mapper.LikeMapper;
import cn.zcbigdata.mybits_demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Override
    public int addLike(Like like) {
        return this.likeMapper.addLike(like);
    }

    @Override
    public int deleteLikeById(int id) {
        return this.likeMapper.deleteLikeById(id);
    }

    @Override
    public int updateLike(Like like) {
        return this.likeMapper.updateLike(like);
    }

    @Override
    public int selectCount() {
        return this.likeMapper.selectCount();
    }

    @Override
    public List<Like> selectLikeAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.likeMapper.selectLikeAll(map);
    }

    @Override
    public List<Like> selectLikeById(int id) {
        return this.likeMapper.selectLikeById(id);
    }


    @Override
    public int dishesLike(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = likeMapper.dishesLike(params);
        int f2 = likeMapper.updateDishesLikeNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int postLike(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", postId);
        int f1 = likeMapper.postLike(params);
        int f2 = likeMapper.updatePostLikeNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int dishesLikeNo(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = likeMapper.dishesLikeNo(params);
        int f2 = likeMapper.updateDishesLikeNumberNo(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int postLikeNo(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", postId);
        int f1 = likeMapper.postLikeNo(params);
        int f2 = likeMapper.updatePostLikeNumberNo(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }
}
