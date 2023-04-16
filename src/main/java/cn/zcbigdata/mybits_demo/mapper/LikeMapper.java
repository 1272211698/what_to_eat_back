package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LikeMapper {
    int addLike(Like like);

    int deleteLikeById(int id);

    int updateLike(Like like);

    int selectCount();

    List<Like> selectLikeAll(Map<String, Integer> map);

    List<Like> selectLikeById(int id);

    int dishesLike(Map<String, Object> params);

    int updateDishesLikeNumber(Map<String, Object> params);

    int dishesLikeNo(Map<String, Object> params);

    int updateDishesLikeNumberNo(Map<String, Object> params);

    int isDishesLike(Map<String, Object> params);

    int postLike(Map<String, Object> params);

    int updatePostLikeNumber(Map<String, Object> params);

    int postLikeNo(Map<String, Object> params);

    int updatePostLikeNumberNo(Map<String, Object> params);
    int isPostLike(Map<String, Object> params);
}
