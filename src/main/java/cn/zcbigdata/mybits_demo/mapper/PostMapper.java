package cn.zcbigdata.mybits_demo.mapper;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Post;
import cn.zcbigdata.mybits_demo.entity.vo.PostVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PostMapper {
    int addPost(Post post);

    int deletePostById(int id);

    int updatePost(Post post);

    int selectCount();

    List<Post> selectPostAll(Map<String, Integer> map);
    List<PostVo> selectPostAllVo(Map<String, Integer> map);

    List<Post> selectPostById(int id);
    List<PostVo> selectByUserId(int id);
    List<PostVo> selectLikeByUserId(int id);
    List<PostVo> selectCollectByUserId(int id);
}
