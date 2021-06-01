package cn.edu.cess.exception;


import cn.edu.cess.constant.SysRetEnum;

public class CustomException extends RuntimeException {


    private String messageCode;
    private String messageContent;

    /**
     * 默认应用异常
     *
     * @param messageId
     * @param messageContent
     */
    public CustomException(String messageId, String messageContent) {
        super(messageContent, new Throwable(messageContent));
        this.messageCode = messageId;
        this.messageContent = messageContent;
    }

    public CustomException(SysRetEnum sysRetEnum) {
        super(sysRetEnum.getDesc(), new Throwable(sysRetEnum.getDesc()));
        this.messageCode = sysRetEnum.getCode();
        this.messageContent = sysRetEnum.getDesc();
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

}
