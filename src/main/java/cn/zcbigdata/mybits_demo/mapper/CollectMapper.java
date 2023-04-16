package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Classification;
import cn.zcbigdata.mybits_demo.entity.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface CollectMapper {
    int addCollect(Collect collect);

    int deleteCollectById(int id);

    int updateCollect(Collect collect);

    int selectCount();

    List<Collect> selectCollectAll(Map<String, Integer> map);

    List<Collect> selectCollectById(int id);

    int dishesCollect(Map<String, Object> params);

    int updateDishesCollectNumber(Map<String, Object> params);

    int dishesCollectNo(Map<String, Object> params);

    int updateDishesCollectNumberNo(Map<String, Object> params);
    int isDishesCollect(Map<String, Object> params);

    int postCollect(Map<String, Object> params);

    int updatePostCollectNumber(Map<String, Object> params);

    int postCollectNo(Map<String, Object> params);

    int updatePostCollectNumberNo(Map<String, Object> params);
    int isPostCollect(Map<String, Object> params);
}
