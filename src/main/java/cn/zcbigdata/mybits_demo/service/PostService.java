package cn.zcbigdata.mybits_demo.service;

import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.Post;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.PostVo;

import java.util.List;
import java.util.Map;

public interface PostService {
    int addPost(Post post);

    int deletePostById(int id);

    int updatePost(Post post);

    int selectCount();

    List<Post> selectPostAll(int page, int limit);

    List<PostVo> selectPostAllVo(int page, int limit);

    List<Post> selectPostById(int id);

    BcdVo isPost(int userId, int i);
    List<PostVo> selectByUserId(int id);
    List<PostVo> selectLikeByUserId(int id);
    List<PostVo> selectCollectByUserId(int id);
}
