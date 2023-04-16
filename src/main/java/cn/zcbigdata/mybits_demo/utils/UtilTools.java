package cn.zcbigdata.mybits_demo.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 工具类
 *
 * @author yty
 */
public class UtilTools {
    /**
     * 参数为空的返回值
     */
    public static final String IS_NULL_RETURN_JSON = "{\"code\":\"9999\",\"msg\":\"缺少参数\"}";
    /**
     * 成功的返回值
     */
    public static final String SUCCESS_RETURN_JSON = "{\"code\":\"0000\",\"msg\":\"操作成功\"}";
    /**
     * 失败的返回值
     */
    public static final String FAIL_RETURN_JSON = "{\"code\":\"9999\",\"msg\":\"操作失败\"}";
    /**
     * 没有登录的返回值
     */
    public static final String NO_LOGIN_RETURN_JSON = "{\"code\":\"7777\",\"msg\":\"没有登录\"}";
    /**
     * 用户名非法字符的返回值
     */
    public static final String USER_WRONG_CHARACTERS_JSON = "{\"code\":\"5555\",\"msg\":\"非法字符\"}";
    /**
     * 昵称非法字符的返回值
     */
    public static final String NICK_WRONG_CHARACTERS_JSON = "{\"code\":\"6666\",\"msg\":\"非法字符\"}";
    /**
     * 密码非法字符的返回值
     */
    public static final String PASS_WRONG_CHARACTERS_JSON = "{\"code\":\"4444\",\"msg\":\"非法字符\"}";

    /**
     * 用于判空的方法
     *
     * @param args 将参数整合为String数组传入
     * @return 有空值时返回false，没有空值返回true
     */
    public static boolean checkNull(String[] args) {
        for (String arg : args) {
            if (arg == null || arg.trim().length() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 用于检查是否登录的方法
     *
     * @return 用户已经登录返回true，否则返回false
     */
    public static boolean checkLogin(HttpServletRequest request) {
        if (request.getHeader("userId") == null) {
            System.out.println("浏览器");
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                return false;
            }else {
                return true;
            }
        }else {
            System.out.println("小程序");
            String userId = request.getHeader("userId");
            if (Objects.equals(userId, "")) {
                return false;
            }else {
                return true;
            }
        }
    }

}
