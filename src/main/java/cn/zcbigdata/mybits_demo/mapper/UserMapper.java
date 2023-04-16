package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {
    User userLogin(User user);

    int addUser(User user);

    int deleteUserById(int id);

    int updateUser(User user);

    int selectCount();

    List<User> selectUserAll(Map<String, Integer> map);

    List<User> selectUserById(int id);
}
