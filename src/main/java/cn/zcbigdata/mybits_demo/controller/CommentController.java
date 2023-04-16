package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Comment;
import cn.zcbigdata.mybits_demo.entity.vo.CommentVo;
import cn.zcbigdata.mybits_demo.service.CollectService;
import cn.zcbigdata.mybits_demo.service.CommentService;
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
@RequestMapping(value = "/comment")
public class CommentController {
    private static final Logger logger = Logger.getLogger(CommentController.class);
    @Autowired
    private CommentService cmService;

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    @ResponseBody
    public String addComment(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        if (!UtilTools.checkNull(new String[]{userId, dpId, type, content})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Comment c = new Comment();
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        c.setContent(content);
        int flag = this.cmService.addComment(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteCommentById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCommentById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.cmService.deleteCommentById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateComment", method = RequestMethod.POST)
    @ResponseBody
    public String updateComment(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        if (!UtilTools.checkNull(new String[]{userId, dpId, type, content})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Comment c = new Comment();
        c.setId(Integer.valueOf(idStr));
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        c.setContent(content);
        int flag = this.cmService.updateComment(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectCommentAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectCommentAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Comment> c = this.cmService.selectCommentAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.cmService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "userId", "dpId", "type", "content"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectCommentById", method = RequestMethod.GET)
    @ResponseBody
    public String selectCommentById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Comment> c = this.cmService.selectCommentById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "userId", "dpId", "type", "content"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/findCommentsByDishesId", method = RequestMethod.GET)
    @ResponseBody
    public String findCommentsByDishesId(HttpServletRequest request) {
        // 获取dishes_id参数
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        // 调用Service层的findCommentsByDishesId方法
        List<CommentVo> comments = cmService.findCommentsByDishesId(dishesId);
        // 返回JSON格式的查询结果
        try {
            return JsonUtil.listToLayJson(new String[]{"userId", "nickName", "content"}, comments);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/findCommentsByPostId", method = RequestMethod.GET)
    @ResponseBody
    public String findCommentsByPostId(HttpServletRequest request) {
        // 获取dishes_id参数
        int postId = Integer.parseInt(request.getParameter("postId"));
        // 调用Service层的findCommentsByDishesId方法
        List<CommentVo> comments = cmService.findCommentsByPostId(postId);
        // 返回JSON格式的查询结果
        try {
            return JsonUtil.listToLayJson(new String[]{"userId", "nickName", "content"}, comments);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/dishesComment", method = RequestMethod.POST)
    @ResponseBody
    public String dishesComment(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        String content = request.getParameter("content");
        int flag = cmService.dishesComment(userId, dishesId, content);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/postComment", method = RequestMethod.POST)
    @ResponseBody
    public String postComment(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        String content = request.getParameter("content");
        int flag = cmService.postComment(userId, postId, content);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
