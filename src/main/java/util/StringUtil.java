package util;

/**
 * Created by Administrator on 2016/7/28.
 */
public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0) {
            return true;
        }
        return false;
    }
}
