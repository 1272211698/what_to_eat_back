package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Hate;
import cn.zcbigdata.mybits_demo.entity.Like;
import cn.zcbigdata.mybits_demo.service.HateService;
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
@RequestMapping(value = "/hate")
public class HateController {
    private static final Logger logger = Logger.getLogger(HateController.class);
    @Autowired
    private HateService hsService;

    @RequestMapping(value = "/addHate", method = RequestMethod.POST)
    @ResponseBody
    public String addHate(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        if (!UtilTools.checkNull(new String[]{userId, dpId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Hate c = new Hate();
        c.setUserId(Integer.valueOf(userId));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.hsService.addHate(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteHateById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteHateById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.hsService.deleteHateById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateHate", method = RequestMethod.POST)
    @ResponseBody
    public String updateHate(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String userId = request.getParameter("userId");
        String dpId = request.getParameter("dpId");
        if (!UtilTools.checkNull(new String[]{userId, dpId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Hate c = new Hate();
        c.setId(Integer.valueOf(idStr));
        c.setUserId(Integer.valueOf(userId));
        c.setDpId(Integer.valueOf(dpId));
        int flag = this.hsService.updateHate(c);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectHateAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectHateAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Hate> c = this.hsService.selectHateAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.hsService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "userId", "dpId"}, c, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectHateById", method = RequestMethod.GET)
    @ResponseBody
    public String selectHateById(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Hate> c = this.hsService.selectHateById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "userId", "dpId"}, c);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/dishesBad", method = RequestMethod.POST)
    @ResponseBody
    public String dishesBad(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = hsService.dishesBad(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
    @RequestMapping(value = "/dishesBadNo", method = RequestMethod.POST)
    @ResponseBody
    public String dishesBadNo(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        int flag = hsService.dishesBadNo(userId, dishesId);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
