package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Classification;
import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.service.ClassificationService;
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
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/classification")
public class ClassificationController {
    private static final Logger logger = Logger.getLogger(ClassificationController.class);
    @Autowired
    private ClassificationService classificationService;

    @RequestMapping(value = "/addClassification", method = RequestMethod.POST)
    @ResponseBody
    public String addClassification(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String content = request.getParameter("content");
        if (!UtilTools.checkNull(new String[]{content})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Classification Classification = new Classification();
        Classification.setContent(content);
        int flag = this.classificationService.addClassification(Classification);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteClassificationById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteClassificationById(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.classificationService.deleteClassificationById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateClassification", method = RequestMethod.POST)
    @ResponseBody
    public String updateClassification(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        String content = request.getParameter("content");
        if (!UtilTools.checkNull(new String[]{idStr, content})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        Classification classification = new Classification();
        classification.setId(Integer.valueOf(idStr));
        classification.setContent(content);
        int flag = this.classificationService.updateClassification(classification);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectClassificationAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectClassificationAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<Classification> classification = this.classificationService.selectClassificationAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.classificationService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "content"}, classification, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectClassificationById", method = RequestMethod.GET)
    @ResponseBody
    public String selectClassificationById(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        List<Classification> classification = this.classificationService.selectClassificationById(Integer.parseInt(idStr));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "content"}, classification);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

}
