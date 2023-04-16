package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.mapper.ClassificationMapper;
import cn.zcbigdata.mybits_demo.mapper.CollectMapper;
import cn.zcbigdata.mybits_demo.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Override
    public int addCollect(Collect collect) {
        return this.collectMapper.addCollect(collect);
    }

    @Override
    public int deleteCollectById(int id) {
        return this.collectMapper.deleteCollectById(id);
    }

    @Override
    public int updateCollect(Collect collect) {
        return this.collectMapper.updateCollect(collect);
    }

    @Override
    public int selectCount() {
        return this.collectMapper.selectCount();
    }

    @Override
    public List<Collect> selectCollectAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.collectMapper.selectCollectAll(map);
    }

    @Override
    public List<Collect> selectCollectById(int id) {
        return this.collectMapper.selectCollectById(id);
    }

    @Override
    public int dishesCollect(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = collectMapper.dishesCollect(params);
        int f2 = collectMapper.updateDishesCollectNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int postCollect(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", postId);
        int f1 = collectMapper.postCollect(params);
        int f2 = collectMapper.updatePostCollectNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int dishesCollectNo(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = collectMapper.dishesCollectNo(params);
        int f2 = collectMapper.updateDishesCollectNumberNo(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int postCollectNo(int userId, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", postId);
        int f1 = collectMapper.postCollectNo(params);
        int f2 = collectMapper.updatePostCollectNumberNo(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }
}
