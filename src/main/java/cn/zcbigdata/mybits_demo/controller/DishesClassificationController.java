package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.DishesClassification;
import cn.zcbigdata.mybits_demo.service.DishesClassificationService;
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
@RequestMapping(value = "/dishesClassification")
public class DishesClassificationController {
    private static final Logger logger = Logger.getLogger(DishesClassificationController.class);
    @Autowired
    private DishesClassificationService dcService;

    @RequestMapping(value = "/addDishesClassification", method = RequestMethod.POST)
    @ResponseBody
    public String addDishesClassification(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String dishesId = request.getParameter("dishesId");
        String classificationId = request.getParameter("classificationId");
        if (!UtilTools.checkNull(new String[]{dishesId, classificationId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        DishesClassification dc = new DishesClassification();
        dc.setDishesId(Integer.valueOf(dishesId));
        dc.setClassificationId(Integer.valueOf(classificationId));
        int flag = this.dcService.addDishesClassification(dc);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteDishesClassificationById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDishesClassificationById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.dcService.deleteDishesClassificationById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateDishesClassification", method = RequestMethod.POST)
    @ResponseBody
    public String updateDishesClassification(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String dishesId = request.getParameter("dishesId");
        String classificationId = request.getParameter("classificationId");
        if (!UtilTools.checkNull(new String[]{idStr, classificationId, dishesId})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        DishesClassification dc = new DishesClassification();
        dc.setId(Integer.valueOf(idStr));
        dc.setDishesId(Integer.valueOf(dishesId));
        dc.setClassificationId(Integer.valueOf(classificationId));
        int flag = this.dcService.updateDishesClassification(dc);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesClassificationAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesClassificationAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<DishesClassification> dc = this.dcService.selectDishesClassificationAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.dcService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "dishesId", "classificationId"}, dc, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesClassificationById", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesClassificationById(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<DishesClassification> dc = this.dcService.selectDishesClassificationById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "dishesId", "classificationId"}, dc);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
