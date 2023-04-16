package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Classification;
import cn.zcbigdata.mybits_demo.entity.Dishes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Mapper
@Repository
public interface ClassificationMapper {
    int addClassification(Classification classification);

    int deleteClassificationById(int id);

    int updateClassification(Classification classification);

    int selectCount();

    List<Classification> selectClassificationAll(Map<String, Integer> map);

    List<Classification> selectClassificationById(int id);
}
