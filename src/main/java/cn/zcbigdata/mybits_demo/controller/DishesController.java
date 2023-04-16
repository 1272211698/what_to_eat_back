package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.vo.BcdVo;
import cn.zcbigdata.mybits_demo.entity.vo.ClassificationIds;
import cn.zcbigdata.mybits_demo.service.DishesService;
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
import java.util.*;

import static cn.zcbigdata.mybits_demo.utils.JsonUtil.beanToJson;

@Slf4j
@Controller
@RequestMapping(value = "/dishes")
public class DishesController {
    private static final Logger logger = Logger.getLogger(DishesController.class);
    @Autowired
    private DishesService dishesService;

    @RequestMapping(value = "/addDishes", method = RequestMethod.POST)
    @ResponseBody
    public String addDishes(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String link = request.getParameter("link");
        if (!UtilTools.checkNull(new String[]{name, priceStr,description,link})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Dishes dishes = new Dishes();
        dishes.setName(name);
        dishes.setPrice(Integer.valueOf(priceStr));
        dishes.setDescription(description);
        dishes.setLink(link);
        int flag = this.dishesService.addDishes(dishes);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteDishesById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteDishesById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.dishesService.deleteDishesById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateDishes", method = RequestMethod.POST)
    @ResponseBody
    public String updateDishes(HttpServletRequest request, HttpSession session) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String goodNumber = request.getParameter("goodNumber");
        String badNumber = request.getParameter("badNumber");
        String collectNumber = request.getParameter("collectNumber");
        String link = request.getParameter("link");
        if (!UtilTools.checkNull(new String[]{idStr, name, price, description,goodNumber,badNumber,collectNumber})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Dishes dishes = new Dishes();
        dishes.setId(Integer.valueOf(idStr));
        dishes.setName(name);
        dishes.setPrice(Integer.valueOf(price));
        dishes.setDescription(description);
        dishes.setGoodNumber(Integer.valueOf(goodNumber));
        dishes.setBadNumber(Integer.valueOf(badNumber));
        dishes.setCollectNumber(Integer.valueOf(collectNumber));
        dishes.setLink(link);
        int flag = this.dishesService.updateDishes(dishes);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesAll(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Dishes> dishes = this.dishesService.selectDishesAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.dishesService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, dishes, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesById", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesById(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Dishes> dishes = this.dishesService.selectDishesById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, dishes);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/isDishes", method = RequestMethod.GET)
    @ResponseBody
    public String isDishes(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        int userId = Integer.parseInt(request.getHeader("userId"));
        int dishesId = Integer.parseInt(request.getParameter("dishesId"));
        BcdVo bcd = this.dishesService.isDishes(userId,dishesId);
        try {
            return JsonUtil.objectToJson(new String[]{"likeFlag","hateFlag","collectFlag"}, bcd);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesByClassification", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesByClassification(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idstr = request.getParameter("ids");
        idstr = idstr.replaceAll("[\\[\\]\"]", ""); // 去掉方括号和引号
        String[] idArr = idstr.split(","); // 按照逗号分隔符切分成多个子串
        List<Integer> ids = new ArrayList<>();
        for (String id : idArr) {
            ids.add(Integer.parseInt(id)); // 将每个子串解析成整数并添加到List<Integer>中
        }
        ClassificationIds classificationIds = new ClassificationIds();
        classificationIds.setIds(ids);
        classificationIds.setCount(ids.size());
        List<Dishes> d = dishesService.selectDishesByClassification(classificationIds);
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, d);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectDishesByName", method = RequestMethod.GET)
    @ResponseBody
    public String selectDishesByName(HttpServletRequest request) {
        String name = request.getParameter("name");
        List<Dishes> d = dishesService.getDishesByName(name);
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, d);
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
        List<Dishes> dishes;
            dishes = this.dishesService.selectLikeByUserId(Integer.parseInt(id));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, dishes);
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
        List<Dishes> dishes;
            dishes = this.dishesService.selectCollectByUserId(Integer.parseInt(id));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "name", "price", "description", "goodNumber", "badNumber", "collectNumber", "commentNumber", "schoolId","link"}, dishes);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
