package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.Post;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.PostVo;
import cn.zcbigdata.mybits_demo.service.CollectService;
import cn.zcbigdata.mybits_demo.service.PostService;
import cn.zcbigdata.mybits_demo.utils.JsonUtil;
import cn.zcbigdata.mybits_demo.utils.UtilTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/post")
public class PostController {
    private static final Logger logger = Logger.getLogger(PostController.class);
    @Autowired
    private PostService psService;

    @RequestMapping(value = "/addPost", method = RequestMethod.POST)
    @ResponseBody
    public String addPost(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String description = request.getParameter("description");
        String link = request.getParameter("link");
        String type = request.getParameter("type");
        String userId = request.getParameter("userId");
        String dishesId = request.getParameter("dishesId");
        if (!UtilTools.checkNull(new String[]{description, link, type, userId, dishesId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Post c = new Post();
        c.setDescription(description);
        c.setLink(link);
        c.setDishesId(Integer.valueOf(dishesId));
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        int flag = this.psService.addPost(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deletePostById", method = RequestMethod.POST)
    @ResponseBody
    public String deletePostById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.psService.deletePostById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    @ResponseBody
    public String updatePost(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String description = request.getParameter("description");
        String link = request.getParameter("link");
        String type = request.getParameter("type");
        String likeNumber = request.getParameter("likeNumber");
        String collectNumber = request.getParameter("collectNumber");
        String commentNumber = request.getParameter("commentNumber");
        String userId = request.getParameter("userId");
        String dishesId = request.getParameter("dishesId");
        if (!UtilTools.checkNull(new String[]{description, link, type, likeNumber, collectNumber, commentNumber, userId, dishesId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Post c = new Post();
        c.setId(Integer.valueOf(idStr));
        c.setDescription(description);
        c.setLink(link);
        c.setLikeNumber(Integer.valueOf(likeNumber));
        c.setCollectNumber(Integer.valueOf(collectNumber));
        c.setCommentNumber(Integer.valueOf(commentNumber));
        c.setDishesId(Integer.valueOf(dishesId));
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        int flag = this.psService.updatePost(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectPostAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectPostAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Post> c = this.psService.selectPostAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.psService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectPostAllVo", method = RequestMethod.GET)
    @ResponseBody
    public String selectPostAllVo(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<PostVo> c = this.psService.selectPostAllVo(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.psService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId", "name", "nickName"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectPostById", method = RequestMethod.GET)
    @ResponseBody
    public String selectPostById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Post> c = this.psService.selectPostById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/isPost", method = RequestMethod.GET)
    @ResponseBody
    public String isPost(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        int userId = Integer.parseInt(request.getHeader("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        BcdVo bcd = this.psService.isPost(userId,dishesId);
        try {
            return JsonUtil.objectToJson(new String[]{"likeFlag","hateFlag","collectFlag"}, bcd);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/selectByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String selectByUserId(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String id = request.getHeader("userId");
        List<PostVo> c;
        c = this.psService.selectByUserId(Integer.parseInt(id));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId","nickName","name"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/selectLikeByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String selectLikeByUserId(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String id = request.getHeader("userId");
        List<PostVo> c;
        c = this.psService.selectLikeByUserId(Integer.parseInt(id));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId","nickName","name"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectCollectByUserId", method = RequestMethod.GET)
    @ResponseBody
    public String selectCollectByUserId(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String id = request.getHeader("userId");
        List<PostVo> c = this.psService.selectCollectByUserId(Integer.parseInt(id));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "description", "link", "type", "likeNumber", "collectNumber", "commentNumber", "userId", "dishesId","nickName","name"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }


}
