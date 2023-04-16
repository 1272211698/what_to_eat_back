package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.ClassificationIds;
import cn.zcbigdata.mybits_demo.mapper.*;
import cn.zcbigdata.mybits_demo.service.DishesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishesServiceImpl implements DishesService {
    @Autowired
    private DishesMapper dishesMapper;
    @Autowired
    private LikeMapper lMapper;
    @Autowired
    private HateMapper hMapper;
    @Autowired
    private CollectMapper cMapper;

    @Override
    public int addDishes(Dishes dishes) {
        return this.dishesMapper.addDishes(dishes);
    }

    @Override
    public int deleteDishesById(int id) {
        return this.dishesMapper.deleteDishesById(id);
    }

    @Override
    public int updateDishes(Dishes dishes) {
        return this.dishesMapper.updateDishes(dishes);
    }

    @Override
    public int selectCount() {
        return this.dishesMapper.selectCount();
    }

    @Override
    public List<Dishes> selectDishesAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.dishesMapper.selectDishesAll(map);
    }

    @Override
    public List<Dishes> selectDishesById(int id) {
        return this.dishesMapper.selectDishesById(id);
    }

    @Override
    public List<Dishes> selectDishesByClassification(ClassificationIds classificationIds) {
        return dishesMapper.selectDishesByClassification(classificationIds);
    }

    @Override
    public List<Dishes> getDishesByName(String name) {
        return dishesMapper.selectDishesByName(name);
    }

    @Override
    public BcdVo isDishes(int userId, int dishesId) {
        Map<String, Object> map = new HashMap<>(2);
        BcdVo b = new BcdVo();
        map.put("userId", userId);
        map.put("dpId", dishesId);
        int countLike = lMapper.isDishesLike(map);
        int countHate = hMapper.isDishesHate(map);
        int countCollect = cMapper.isDishesCollect(map);
        b.setLikeFlag(countLike);
        b.setHateFlag(countHate);
        b.setCollectFlag(countCollect);
        return b;
    }

    @Override
    public List<Dishes> selectLikeByUserId(int id) {
        return this.dishesMapper.selectLikeByUserId(id);
    }

    @Override
    public List<Dishes> selectHateByUserId(int id) {
        return this.dishesMapper.selectHateByUserId(id);
    }

    @Override
    public List<Dishes> selectCollectByUserId(int id) {
        return this.dishesMapper.selectCollectByUserId(id);
    }
}
