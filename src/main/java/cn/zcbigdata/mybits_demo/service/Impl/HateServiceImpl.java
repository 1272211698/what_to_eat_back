package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Hate;
import cn.zcbigdata.mybits_demo.mapper.HateMapper;
import cn.zcbigdata.mybits_demo.mapper.LikeMapper;
import cn.zcbigdata.mybits_demo.service.HateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HateServiceImpl implements HateService {
    @Autowired
    private HateMapper hateMapper;

    @Override
    public int addHate(Hate hate) {
        return this.hateMapper.addHate(hate);
    }

    @Override
    public int deleteHateById(int id) {
        return this.hateMapper.deleteHateById(id);
    }

    @Override
    public int updateHate(Hate hate) {
        return this.hateMapper.updateHate(hate);
    }

    @Override
    public int selectCount() {
        return this.hateMapper.selectCount();
    }

    @Override
    public List<Hate> selectHateAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.hateMapper.selectHateAll(map);
    }

    @Override
    public List<Hate> selectHateById(int id) {
        return this.hateMapper.selectHateById(id);
    }

    @Override
    public int dishesBad(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = hateMapper.dishesBad(params);
        int f2 = hateMapper.updateDishesBadNumber(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }

    @Override
    public int dishesBadNo(int userId, int dishesId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("dpId", dishesId);
        int f1 = hateMapper.dishesBadNo(params);
        int f2 = hateMapper.updateDishesBadNumberNo(params);
        if(f1 + f2 == 2) return 1;
        else return 0;
    }
}
