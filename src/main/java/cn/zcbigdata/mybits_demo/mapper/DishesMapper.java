package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.Post;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.entity.vo.ClassificationIds;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DishesMapper {
    int addDishes(Dishes dishes);

    int deleteDishesById(int id);

    int updateDishes(Dishes dishes);

    int selectCount();

    List<Dishes> selectDishesAll(Map<String, Integer> map);

    List<Dishes> selectDishesById(int id);

    List<Dishes> selectDishesByClassification(ClassificationIds classificationIds);
    List<Dishes> selectDishesByName(String name);

    List<Dishes> selectLikeByUserId(int id);
    List<Dishes> selectHateByUserId(int id);
    List<Dishes> selectCollectByUserId(int id);

}
