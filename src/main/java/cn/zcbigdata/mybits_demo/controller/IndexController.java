package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.utils.UtilTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 页面跳转相关接口
 *
 * @author ts119
 */
@Controller
public class IndexController {
    /**
     * 返回登录页面的Controller层方法
     *
     * @return 返回登录页面
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    /**
     * 登出接口，无需入参
     *
     * @param request HttpServletRequest
     * @return 返回状态码和提示信息
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return UtilTools.SUCCESS_RETURN_JSON;
    }

    /**
     * 跳转管理员端接口，无需入参
     *
     * @param request HttpServletRequest
     * @return 返回状态码和提示信息
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(HttpServletRequest request) {
        return "back1";
    }

    @RequestMapping(value = "/GoUser", method = RequestMethod.GET)
    public String GoUser(HttpServletRequest request) {
        return "userManage";
    }


    @RequestMapping(value = "/GoDishes", method = RequestMethod.GET)
    public String GoDishes(HttpServletRequest request) {
        return "dishesManage";
    }


    @RequestMapping(value = "/GoClassification", method = RequestMethod.GET)
    public String GoClassification(HttpServletRequest request) {
        return "classificationManage";
    }


    @RequestMapping(value = "/GoPost", method = RequestMethod.GET)
    public String GoPost(HttpServletRequest request) {
        return "postManage";
    }


    @RequestMapping(value = "/GoDishesComment", method = RequestMethod.GET)
    public String GoDishesComment(HttpServletRequest request) {
        return "dishesCommentManage";
    }


    @RequestMapping(value = "/GoPostComment", method = RequestMethod.GET)
    public String GoPostComment(HttpServletRequest request) {
        return "postCommentManage";
    }


    @RequestMapping(value = "/GoSchool", method = RequestMethod.GET)
    public String GoSchool(HttpServletRequest request) {
        return "schoolManage";
    }

    @RequestMapping(value = "/GoDishesClassification", method = RequestMethod.GET)
    public String GoDishesClassification(HttpServletRequest request) {
        return "dishesClassificationManage";
    }

}
