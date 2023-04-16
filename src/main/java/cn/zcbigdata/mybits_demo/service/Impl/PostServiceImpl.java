package cn.zcbigdata.mybits_demo.service.Impl;

import cn.zcbigdata.mybits_demo.entity.Post;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.PostVo;
import cn.zcbigdata.mybits_demo.mapper.CollectMapper;
import cn.zcbigdata.mybits_demo.mapper.HateMapper;
import cn.zcbigdata.mybits_demo.mapper.LikeMapper;
import cn.zcbigdata.mybits_demo.mapper.PostMapper;
import cn.zcbigdata.mybits_demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private LikeMapper lMapper;
    @Autowired
    private HateMapper hMapper;
    @Autowired
    private CollectMapper cMapper;
    @Override
    public int addPost(Post post) {
        return this.postMapper.addPost(post);
    }

    @Override
    public int deletePostById(int id) {
        return this.postMapper.deletePostById(id);
    }

    @Override
    public int updatePost(Post post) {
        return this.postMapper.updatePost(post);
    }

    @Override
    public int selectCount() {
        return this.postMapper.selectCount();
    }

    @Override
    public List<Post> selectPostAll(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.postMapper.selectPostAll(map);
    }
    @Override
    public List<PostVo> selectPostAllVo(int page, int limit) {
        Integer startIndex = (page - 1) * limit;
        Map<String, Integer> map = new HashMap<>(2);
        map.put("startIndex", startIndex);
        map.put("pageSize", limit);
        return this.postMapper.selectPostAllVo(map);
    }

    @Override
    public List<Post> selectPostById(int id) {
        return this.postMapper.selectPostById(id);
    }

    @Override
    public BcdVo isPost(int userId, int postId) {
        Map<String, Object> map = new HashMap<>(2);
        BcdVo b = new BcdVo();
        map.put("userId", userId);
        map.put("dpId", postId);
        int countLike = lMapper.isPostLike(map);
        int countCollect = cMapper.isPostCollect(map);
        b.setLikeFlag(countLike);
        b.setHateFlag(2);
        b.setCollectFlag(countCollect);
        return b;
    }

    @Override
    public List<PostVo> selectByUserId(int id) {
        return this.postMapper.selectByUserId(id);
    }

    @Override
    public List<PostVo> selectLikeByUserId(int id) {
        return this.postMapper.selectLikeByUserId(id);
    }

    @Override
    public List<PostVo> selectCollectByUserId(int id) {
        return this.postMapper.selectCollectByUserId(id);
    }
}
