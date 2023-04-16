package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.DishesClassification;
import cn.zcbigdata.mybits_demo.mapper.DishesClassificationMapper;
import cn.zcbigdata.mybits_demo.service.DishesClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DishesClassificationServiceImpl implements DishesClassificationService {
    @Autowired
    private DishesClassificationMapper dishesClassificationMapper;

    @Override
    public int addDishesClassification(DishesClassification dishesClassification) {
        return this.dishesClassificationMapper.addDishesClassification(dishesClassification);
    }

    @Override
    public int deleteDishesClassificationById(int id) {
        return this.dishesClassificationMapper.deleteDishesClassificationById(id);
    }

    @Override
    public int updateDishesClassification(DishesClassification dishesClassification) {
        return this.dishesClassificationMapper.updateDishesClassification(dishesClassification);
    }

    @Override
    public int selectCount() {
        return this.dishesClassificationMapper.selectCount();
    }

    @Override
    public List<DishesClassification> selectDishesClassificationAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.dishesClassificationMapper.selectDishesClassificationAll(map);
    }

    @Override
    public List<DishesClassification> selectDishesClassificationById(int id) {
        return this.dishesClassificationMapper.selectDishesClassificationById(id);
    }
}
