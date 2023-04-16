package cn.zcbigdata.mybits_demo.controller;

import cn.zcbigdata.mybits_demo.entity.Dishes;
import cn.zcbigdata.mybits_demo.entity.User;
import cn.zcbigdata.mybits_demo.service.UserService;
import cn.zcbigdata.mybits_demo.utils.JsonUtil;
import cn.zcbigdata.mybits_demo.utils.UtilTools;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!UtilTools.checkNull(new String[]{userName, password})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setUserName(userName.trim());
        user.setPassword(password.trim());
        user = this.userService.userLogin(user);
        if (user == null) {
            return UtilTools.FAIL_RETURN_JSON;
        }
        HttpSession session = request.getSession();
        session.setAttribute("userId", user.getId().toString());
        return UtilTools.SUCCESS_RETURN_JSON;
    }

    @RequestMapping(value = "/loginForWX", method = RequestMethod.GET)
    @ResponseBody
    public String loginForWX(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (!UtilTools.checkNull(new String[]{userName, password})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setUserName(userName.trim());
        user.setPassword(password.trim());
        user = this.userService.userLogin(user);
        if (user == null) {
            return UtilTools.FAIL_RETURN_JSON;
        }
        String re = "{\"code\":\"0000\",\"msg\":\"操作失败\",\"userId\":" +
                user.getId() +
                "}";
        return re;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        String nickName = request.getParameter("nickName");
        String password = request.getParameter("password");
        if(userName.contains("？") || userName.contains("【") || userName.contains("】") || userName.contains("——") || userName.contains("（") || userName.contains("）") || userName.contains("￥") || userName.contains("……") || userName.contains("·") || userName.contains("！") || userName.contains("@") || userName.contains("!") || userName.contains("~") || userName.contains("#") || userName.contains("$") || userName.contains("%") || userName.contains("^") || userName.contains("&") || userName.contains("*") || userName.contains("(") || userName.contains(")") || userName.contains("-") || userName.contains("_") || userName.contains("+") || userName.contains("=") || userName.contains("{") || userName.contains("}") || userName.contains("[") || userName.contains("]") || userName.contains(";") || userName.contains(":") || userName.contains("'") || userName.contains("|") || userName.contains("/") || userName.contains("?") || userName.contains(">") || userName.contains("<") || userName.contains(",") || userName.contains(".") || userName.contains("：") || userName.contains("》") || userName.contains("《") || userName.contains("。") || userName.contains("，") || userName.contains("“") || userName.contains("‘") || userName.contains("、") || userName.contains("；")){
            return UtilTools.USER_WRONG_CHARACTERS_JSON;
        }
        if(nickName.contains("？") || nickName.contains("【") || nickName.contains("】") || nickName.contains("——") || nickName.contains("（") || nickName.contains("）") || nickName.contains("￥") || nickName.contains("……") || nickName.contains("·") || nickName.contains("！") || nickName.contains("@") || nickName.contains("!") || nickName.contains("~") || nickName.contains("#") || nickName.contains("$") || nickName.contains("%") || nickName.contains("^") || nickName.contains("&") || nickName.contains("*") || nickName.contains("(") || nickName.contains(")") || nickName.contains("-") || nickName.contains("_") || nickName.contains("+") || nickName.contains("=") || nickName.contains("{") || nickName.contains("}") || nickName.contains("[") || nickName.contains("]") || nickName.contains(";") || nickName.contains(":") || nickName.contains("'") || nickName.contains("|") || nickName.contains("/") || nickName.contains("?") || nickName.contains(">") || nickName.contains("<") || nickName.contains(",") || nickName.contains(".") || nickName.contains("：") || nickName.contains("》") || nickName.contains("《") || nickName.contains("。") || nickName.contains("，") || nickName.contains("“") || nickName.contains("‘") || nickName.contains("、") || nickName.contains("；")){
            return UtilTools.NICK_WRONG_CHARACTERS_JSON;
        }
        if (!UtilTools.checkNull(new String[]{userName, nickName,password})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setUserName(userName.trim());
        user.setNickName(nickName.trim());
        user.setPassword(password.trim());
        int flag = this.userService.addUser(user);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/deleteUserById", method = RequestMethod.POST)
    @ResponseBody
    public String deleteUserById(HttpServletRequest request, HttpSession session) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String idStr = request.getParameter("id");
        if (!UtilTools.checkNull(new String[]{idStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        int flag = this.userService.deleteUserById(Integer.parseInt(idStr.trim()));
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(HttpServletRequest request, HttpSession session) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String stuIdStr = request.getParameter("id");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String nickName = request.getParameter("nickName");
        if(userName.contains("？") || userName.contains("【") || userName.contains("】") || userName.contains("——") || userName.contains("（") || userName.contains("）") || userName.contains("￥") || userName.contains("……") || userName.contains("·") || userName.contains("！") || userName.contains("@") || userName.contains("!") || userName.contains("~") || userName.contains("#") || userName.contains("$") || userName.contains("%") || userName.contains("^") || userName.contains("&") || userName.contains("*") || userName.contains("(") || userName.contains(")") || userName.contains("-") || userName.contains("_") || userName.contains("+") || userName.contains("=") || userName.contains("{") || userName.contains("}") || userName.contains("[") || userName.contains("]") || userName.contains(";") || userName.contains(":") || userName.contains("'") || userName.contains("|") || userName.contains("/") || userName.contains("?") || userName.contains(">") || userName.contains("<") || userName.contains(",") || userName.contains(".") || userName.contains("：") || userName.contains("》") || userName.contains("《") || userName.contains("。") || userName.contains("，") || userName.contains("“") || userName.contains("‘") || userName.contains("、") || userName.contains("；")){
            return UtilTools.USER_WRONG_CHARACTERS_JSON;
        }
        if(nickName.contains("？") || nickName.contains("【") || nickName.contains("】") || nickName.contains("——") || nickName.contains("（") || nickName.contains("）") || nickName.contains("￥") || nickName.contains("……") || nickName.contains("·") || nickName.contains("！") || nickName.contains("@") || nickName.contains("!") || nickName.contains("~") || nickName.contains("#") || nickName.contains("$") || nickName.contains("%") || nickName.contains("^") || nickName.contains("&") || nickName.contains("*") || nickName.contains("(") || nickName.contains(")") || nickName.contains("-") || nickName.contains("_") || nickName.contains("+") || nickName.contains("=") || nickName.contains("{") || nickName.contains("}") || nickName.contains("[") || nickName.contains("]") || nickName.contains(";") || nickName.contains(":") || nickName.contains("'") || nickName.contains("|") || nickName.contains("/") || nickName.contains("?") || nickName.contains(">") || nickName.contains("<") || nickName.contains(",") || nickName.contains(".") || nickName.contains("：") || nickName.contains("》") || nickName.contains("《") || nickName.contains("。") || nickName.contains("，") || nickName.contains("“") || nickName.contains("‘") || nickName.contains("、") || nickName.contains("；")){
            return UtilTools.NICK_WRONG_CHARACTERS_JSON;
        }
        if(password.contains("？") || password.contains("【") || password.contains("】") || password.contains("——") || password.contains("（") || password.contains("）") || password.contains("￥") || password.contains("……") || password.contains("·") || password.contains("！") || password.contains("@") || password.contains("!") || password.contains("~") || password.contains("#") || password.contains("$") || password.contains("%") || password.contains("^") || password.contains("&") || password.contains("*") || password.contains("(") || password.contains(")") || password.contains("-") || password.contains("_") || password.contains("+") || password.contains("=") || password.contains("{") || password.contains("}") || password.contains("[") || password.contains("]") || password.contains(";") || password.contains(":") || password.contains("'") || password.contains("|") || password.contains("/") || password.contains("?") || password.contains(">") || password.contains("<") || password.contains(",") || password.contains(".") || password.contains("：") || password.contains("》") || password.contains("《") || password.contains("。") || password.contains("，") || password.contains("“") || password.contains("‘") || password.contains("、") || password.contains("；")){
            return UtilTools.PASS_WRONG_CHARACTERS_JSON;
        }
        if (!UtilTools.checkNull(new String[]{stuIdStr, userName, password, nickName})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        User user = new User();
        user.setId(Integer.valueOf(stuIdStr.trim()));
        user.setUserName(userName.trim());
        user.setPassword(password.trim());
        user.setNickName(nickName.trim());
        int flag = this.userService.updateUser(user);
        if (flag == 1) {
            return UtilTools.SUCCESS_RETURN_JSON;
        } else {
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectUserAll", method = RequestMethod.GET)
    @ResponseBody
    public String selectUserAll(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String pageStr = request.getParameter("page");
        String limitStr = request.getParameter("limit");
        if (!UtilTools.checkNull(new String[]{pageStr, limitStr})) {
            return UtilTools.IS_NULL_RETURN_JSON;
        }
        List<User> users = this.userService.selectUserAll(Integer.parseInt(pageStr.trim()), Integer.parseInt(limitStr.trim()));
        try {
            int count = this.userService.selectCount();
            return JsonUtil.listToNewLayJson(new String[]{"id", "userName", "password", "nickName"}, users, count);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @RequestMapping(value = "/selectUserById", method = RequestMethod.GET)
    @ResponseBody
    public String selectUserById(HttpServletRequest request) {
        if (!(UtilTools.checkLogin(request))) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        String userId = request.getHeader("userId");
        List<User> user = this.userService.selectUserById(Integer.parseInt(userId));
        try {
            return JsonUtil.listToLayJson(new String[]{"id", "userName", "password", "nickName"}, user);
        } catch (Exception e) {
            logger.error(e.getStackTrace());
            return UtilTools.FAIL_RETURN_JSON;
        }
    }

    @GetMapping(value = "loginCheck")
    @ResponseBody
    public String loginCheck(HttpServletRequest request) {
        if (!UtilTools.checkLogin(request)) {
            return UtilTools.NO_LOGIN_RETURN_JSON;
        }
        List<User> user = this.userService.selectUserById(Integer.parseInt(request.getSession().getAttribute("userId").toString()));
        try {
            return JsonUtil.objectToJson(new String[]{"id", "nickName"}, user.get(0));
        } catch (Exception e) {
            logger.error(e);
            return UtilTools.FAIL_RETURN_JSON;
        }
    }
}
