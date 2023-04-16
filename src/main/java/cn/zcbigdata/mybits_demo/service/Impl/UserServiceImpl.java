package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.mapper.UserMapper;
import cn.zcbigdata.mybits_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User userLogin(User user) {
        return this.userMapper.userLogin(user);
    }

    @Override
    public int addUser(User user) {
        return this.userMapper.addUser(user);
    }

    @Override
    public int deleteUserById(int id) {
        return this.userMapper.deleteUserById(id);
    }

    @Override
    public int updateUser(User user) {
        return this.userMapper.updateUser(user);
    }

    @Override
    public int selectCount() {
        return this.userMapper.selectCount();
    }

    @Override
    public List<User> selectUserAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.userMapper.selectUserAll(map);
    }


    @Override
    public List<User> selectUserById(int id) {
        return this.userMapper.selectUserById(id);
    }
}
