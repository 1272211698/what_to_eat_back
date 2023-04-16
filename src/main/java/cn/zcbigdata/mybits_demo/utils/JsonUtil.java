package cn.zcbigdata.mybits_demo.utils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Json工具类
 *
 * @author yty
 */
public class JsonUtil {
    public static String listToNewLayJson(String[] fields, List<?> data, int count) throws Exception {
        StringBuilder re = new StringBuilder();
        re.append("{\"code\":0,\"msg\":\"获取成功\",\"count\":");
        re.append(count);
        re.append(",\"data\":[");
        if (data.isEmpty()) {
            re.append("]}");
            return re.toString();
        }
        for (Object object : data) {
            re.append(beanToJson(fields, object));
            re.append(',');
        }
        re.deleteCharAt(re.length() - 1);
        re.append("]}");
        return re.toString();
    }


    /**
     * 将list转换为layui风格的json
     *
     * @param fields String数组形式的字段列表
     * @param data   传入的列表，内容需要为javaBean格式的类
     * @return 返回转换好的json
     * @throws Exception 传入的字段列表或list内的对象格式不对抛出异常
     */
    public static String listToLayJson(String[] fields, List<?> data) throws Exception {
        StringBuilder re = new StringBuilder();
        re.append("[{\"status\":0}, {\"message\": \"成功\" }, {\"count\": 1000},{\"rows\":{\"item\":[");
        if (data == null || data.isEmpty()) {
            re.append("]}}]");
            return re.toString();
        }
        for (Object object : data) {
            re.append(beanToJson(fields, object));
            re.append(',');
        }
        re.deleteCharAt(re.length() - 1);
        re.append("]}}]");
        return re.toString();
    }

    /**
     * 将list转换为json
     *
     * @param fields String数组形式的字段列表
     * @param data   传入的列表，内容需要为javaBean格式的类
     * @return 返回转换好的json
     * @throws Exception 传入的字段列表或list内的对象格式不对抛出异常
     */
    public static String listToJson(String[] fields, List<?> data) throws Exception {
        StringBuilder re = new StringBuilder();
        re.append("{\"code\":\"0000\",\"msg\":\"成功\",\"data\":[");
        if (data == null || data.isEmpty()) {
            re.append("]}");
            return re.toString();
        }
        for (Object object : data) {
            re.append(beanToJson(fields, object));
            re.append(',');
        }
        re.deleteCharAt(re.length() - 1);
        re.append("]}");
        return re.toString();
    }

    /**
     * 将javaBean对象转换为带有状态码和消息的json
     *
     * @param fields String数组形式的字段列表
     * @param object 传入的javaBean对象
     * @return 返回转换好的json
     * @throws Exception 传入的字段列表或对象格式不对抛出异常
     */
    public static String objectToJson(String[] fields, Object object) throws Exception {
        return "{\"code\":\"0000\",\"msg\":\"成功\",\"data\":" + beanToJson(fields, object) + '}';
    }

    /**
     * 将javaBean对象转换为简单的json
     *
     * @param fields String数组形式的字段列表
     * @param object 传入的javaBean对象
     * @return 返回转换好的json
     * @throws Exception 传入的字段列表或对象格式不对抛出异常
     */
    public static String beanToJson(String[] fields, Object object) throws Exception {
        StringBuilder jsonStr = new StringBuilder();
        jsonStr.append('{');
        for (String field : fields) {
            jsonStr.append('\"').append(field).append("\":\"");
            // 根据传入的字段列表拼接get方法名
            String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
            Method method = object.getClass().getDeclaredMethod(methodName);
            // 通过反射获取数据
            Object tmp = method.invoke(object);
            String str;
            // 判空
            if (tmp != null) {
                str = tmp.toString();
            } else {
                str = "";
            }
            // 转义数据中的双引号，防止破坏json格式
            if (str.contains("\"")) {
                char[] arr = str.toCharArray();
                for (char c : arr) {
                    if (c == '\"') {
                        jsonStr.append('\\');
                    }
                    jsonStr.append(c);
                }
            } else {
                jsonStr.append(str);
            }
            jsonStr.append("\",");
        }
        // 删除多余逗号
        jsonStr.deleteCharAt(jsonStr.length() - 1);
        jsonStr.append('}');
        return jsonStr.toString();
    }

}
