package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.Like;
import cn.zcbigdata.mybits_demo.service.CollectService;
import cn.zcbigdata.mybits_demo.service.LikeService;
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
@RequestMapping(value = "/like")
public class LikeController {
    private static final Logger logger = Logger.getLogger(LikeController.class);
    @Autowired
    private LikeService lsService;

    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    @ResponseBody
    public String addLike(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        String type = request.getParameter("type");
        if (!UtilTools.checkNull(new String[]{userId, dpId,type})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Like c = new Like();
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.lsService.addLike(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteLikeById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteLikeById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.lsService.deleteLikeById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateLike", method = RequestMethod.POST)
    @ResponseBody
    public String updateLike(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        String type = request.getParameter("type");
        if (!UtilTools.checkNull(new String[]{userId, dpId, type})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Like c = new Like();
        c.setId(Integer.valueOf(idStr));
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.lsService.updateLike(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectLikeAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectLikeAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Like> c = this.lsService.selectLikeAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.lsService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "userId", "dpId","type"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectLikeById", method = RequestMethod.GET)
    @ResponseBody
    public String selectLikeById(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Like> c = this.lsService.selectLikeById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "userId", "dpId","type"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/dishesLike", method = RequestMethod.POST)
    @ResponseBody
    public String dishesLike(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = lsService.dishesLike(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/postLike", method = RequestMethod.POST)
    @ResponseBody
    public String postLike(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        int flag = lsService.postLike(userId, postId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/dishesLikeNo", method = RequestMethod.POST)
    @ResponseBody
    public String dishesLikeNo(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = lsService.dishesLikeNo(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/postLikeNo", method = RequestMethod.POST)
    @ResponseBody
    public String postLikeNo(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        int flag = lsService.postLikeNo(userId, postId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
