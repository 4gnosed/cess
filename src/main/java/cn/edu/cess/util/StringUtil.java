package cn.edu.cess.util;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        } else {
            return "".equals(str.trim()) ? true : str.isEmpty();
        }
    }

    public static boolean isEmpty(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return true;
        } else {
            return ("".equals(str1.trim())) || ("".equals(str2.trim()))
                    ? true : str1.isEmpty() || str2.isEmpty();
        }
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(String str1, String str2) {
        return !isEmpty(str1, str2);
    }
}
