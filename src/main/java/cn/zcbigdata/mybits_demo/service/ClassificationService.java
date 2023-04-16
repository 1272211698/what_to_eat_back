package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Classification;

import java.util.List;
import java.util.Map;

public interface ClassificationService {
    int addClassification(Classification classification);

    int deleteClassificationById(int id);

    int updateClassification(Classification classification);

    int selectCount();

    List<Classification> selectClassificationAll(int page, int limit);

    List<Classification> selectClassificationById(int id);
}
