package cn.edu.cess.util;

public class ExceptionUtil {
    public ExceptionUtil() {
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        } else if (object instanceof String && StringUtil.isEmpty((String)object)) {
            throw new IllegalArgumentException(message);
        }
    }
}