package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Classification;
import cn.zcbigdata.mybits_demo.mapper.ClassificationMapper;
import cn.zcbigdata.mybits_demo.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Autowired
    private ClassificationMapper classificationMapper;

    @Override
    public int addClassification(Classification classification) {
        return this.classificationMapper.addClassification(classification);
    }

    @Override
    public int deleteClassificationById(int id) {
        return this.classificationMapper.deleteClassificationById(id);
    }

    @Override
    public int updateClassification(Classification classification) {
        return this.classificationMapper.updateClassification(classification);
    }

    @Override
    public int selectCount() {
        return this.classificationMapper.selectCount();
    }

    @Override
    public List<Classification> selectClassificationAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.classificationMapper.selectClassificationAll(map);
    }

    @Override
    public List<Classification> selectClassificationById(int id) {
        return this.classificationMapper.selectClassificationById(id);
    }
}
