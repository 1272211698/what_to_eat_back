package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Hate;
import cn.zcbigdata.mybits_demo.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HateMapper {
    int addHate(Hate hate);

    int deleteHateById(int id);

    int updateHate(Hate hate);

    int selectCount();

    List<Hate> selectHateAll(Map<String, Integer> map);

    List<Hate> selectHateById(int id);


    int isDishesHate(Map<String, Object> params);
    int dishesBad(Map<String, Object> params);
    int updateDishesBadNumber(Map<String, Object> params);
    int dishesBadNo(Map<String, Object> params);
    int updateDishesBadNumberNo(Map<String, Object> params);
}
