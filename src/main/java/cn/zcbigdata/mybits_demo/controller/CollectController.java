package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Collect;
import cn.zcbigdata.mybits_demo.entity.DishesClassification;
import cn.zcbigdata.mybits_demo.service.ClassificationService;
import cn.zcbigdata.mybits_demo.service.CollectService;
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
@RequestMapping(value = "/collect")
public class CollectController {
    private static final Logger logger = Logger.getLogger(CollectController.class);
    @Autowired
    private CollectService csService;

    @RequestMapping(value = "/addCollect", method = RequestMethod.POST)
    @ResponseBody
    public String addCollect(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        String type = request.getParameter("type");
        if (!UtilTools.checkNull(new String[]{userId, dpId,type})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Collect c = new Collect();
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.csService.addCollect(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteCollectById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCollectById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.csService.deleteCollectById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateCollect", method = RequestMethod.POST)
    @ResponseBody
    public String updateCollect(HttpServletRequest request) {
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
        Collect c = new Collect();
        c.setId(Integer.valueOf(idStr));
        c.setUserId(Integer.valueOf(userId));
        c.setType(Integer.valueOf(type));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.csService.updateCollect(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectCollectAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectCollectAll(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Collect> c = this.csService.selectCollectAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.csService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "userId", "dpId","type"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectCollectById", method = RequestMethod.GET)
    @ResponseBody
    public String selectCollectById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Collect> c = this.csService.selectCollectById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "userId", "dpId","type"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/dishesCollect", method = RequestMethod.POST)
    @ResponseBody
    public String dishesCollect(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = csService.dishesCollect(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/postCollect", method = RequestMethod.POST)
    @ResponseBody
    public String postCollect(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        int flag = csService.postCollect(userId, postId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/dishesCollectNo", method = RequestMethod.POST)
    @ResponseBody
    public String dishesCollectNo(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = csService.dishesCollectNo(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/postCollectNo", method = RequestMethod.POST)
    @ResponseBody
    public String postCollectNo(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postId = Integer.parseInt(request.getParameter("postId"));
        int flag = csService.postCollectNo(userId, postId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
