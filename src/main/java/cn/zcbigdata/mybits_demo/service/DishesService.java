package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.ClassificationIds;

import java.util.List;

public interface DishesService {
    int addDishes(Dishes dishes);

    int deleteDishesById(int id);

    int updateDishes(Dishes dishes);

    int selectCount();

    List<Dishes> selectDishesAll(int page, int limit);

    List<Dishes> selectDishesById(int id);

    List<Dishes> selectDishesByClassification(ClassificationIds classificationIds);

    List<Dishes> getDishesByName(String name);

    BcdVo isDishes(int userId, int dishesId);

    List<Dishes> selectLikeByUserId(int id);
    List<Dishes> selectHateByUserId(int id);
    List<Dishes> selectCollectByUserId(int id);
}
