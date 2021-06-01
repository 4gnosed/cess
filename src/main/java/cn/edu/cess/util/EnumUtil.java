package cn.edu.cess.util;


import cn.edu.cess.constant.CommonEnum;

/**
 * @author: LuDeSong
 * @Date: 2020-12-2 18:42
 * @Description: 枚举工具类
 */

public class EnumUtil {
    /**
     * 返回指定编码的'枚举'
     *
     * @param code
     * @return
     */
    public static <T extends CommonEnum> T getEnumBycode(Class<T> clazz, String code) {
        for (T _enum : clazz.getEnumConstants()) {
            if (code.equals(_enum.getCode())) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 返回指定名称的'枚举'
     *
     * @param name
     * @return
     */
    public static <T extends CommonEnum> T getEnumByName(Class<T> clazz, String name) {
        for (T _enum : clazz.getEnumConstants()) {
            if (_enum.getCode().equals(name)) {
                return _enum;
            }
        }
        return null;
    }

    /**
     * 返回指定描述的'枚举'
     *
     * @param desc
     * @return
     */
    public static <T extends CommonEnum> T getEnumByDesc(Class<T> clazz, String desc) {
        for (T _enum : clazz.getEnumConstants()) {
            if (_enum.getCode().equals(desc)) {
                return _enum;
            }
        }
        return null;
    }
}
