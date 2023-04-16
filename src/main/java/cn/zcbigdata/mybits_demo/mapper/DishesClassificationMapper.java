package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.DishesClassification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DishesClassificationMapper {
    int addDishesClassification(DishesClassification dishesClassification);

    int deleteDishesClassificationById(int id);

    int updateDishesClassification(DishesClassification dishesClassification);

    int selectCount();

    List<DishesClassification> selectDishesClassificationAll(Map<String, Integer> map);

    List<DishesClassification> selectDishesClassificationById(int id);
}
