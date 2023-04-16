package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Collect;

import java.util.List;
import java.util.Map;

public interface CollectService {
    int addCollect(Collect collect);

    int deleteCollectById(int id);

    int updateCollect(Collect collect);

    int selectCount();

    List<Collect> selectCollectAll(int page, int limit);

    List<Collect> selectCollectById(int id);

    int dishesCollect(int userId, int dishesId);

    int postCollect(int userId, int postId);

    int dishesCollectNo(int userId, int dishesId);

    int postCollectNo(int userId, int postId);
}
