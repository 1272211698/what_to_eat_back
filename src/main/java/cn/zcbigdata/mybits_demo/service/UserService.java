package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.User;

import java.util.List;

public interface UserService {
    User userLogin(User user);

    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    int selectCount();

    List<User> selectUserAll(int page, int limit);

    List<User> selectUserById(int id);
}
