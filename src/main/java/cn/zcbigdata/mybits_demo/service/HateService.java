package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Hate;
import cn.zcbigdata.mybits_demo.entity.Like;

import java.util.List;

public interface HateService {
    int addHate(Hate hate);

    int deleteHateById(int id);

    int updateHate(Hate hate);

    int selectCount();

    List<Hate> selectHateAll(int page, int limit);

    List<Hate> selectHateById(int id);

    int dishesBad(int userId, int dishesId);

    int dishesBadNo(int userId, int dishesId);
}
