package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.DishesClassification;

import java.util.List;
import java.util.Map;

public interface DishesClassificationService {
    int addDishesClassification(DishesClassification dishesClassification);

    int deleteDishesClassificationById(int id);

    int updateDishesClassification(DishesClassification dishesClassification);

    int selectCount();

    List<DishesClassification> selectDishesClassificationAll(int page, int limit);

    List<DishesClassification> selectDishesClassificationById(int id);
}
