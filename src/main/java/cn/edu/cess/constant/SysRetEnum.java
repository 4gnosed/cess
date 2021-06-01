package cn.edu.cess.constant;

public enum SysRetEnum implements CommonEnum {

    DEFAULT_EXCEPTION("DEFAULT_EXCEPTION", "默认异常", "默认异常",""),

    ;


    /* 返回码 */
    private String code;

    /* 返回码描述 */
    private String desc;

    /* 名称 */
    private String name;

    /* 初始默认值 */
    private String defaultValue;

    private SysRetEnum(String code, String name, String desc, String defaultValue) {
        this.code = code;
        this.name = name;
        this.desc = desc;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
