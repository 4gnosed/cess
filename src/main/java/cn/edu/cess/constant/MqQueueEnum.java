package cn.edu.cess.constant;

public enum MqQueueEnum {
    QUEUE1(MqConstant.QUEUE1),
    QUEUE2(MqConstant.QUEUE2),
    QUEUE3(MqConstant.QUEUE3),
    QUEUE4(MqConstant.QUEUE4),
    QUEUE5(MqConstant.QUEUE5),
    QUEUE6(MqConstant.QUEUE6),
    DEAD_LETTER_QUEUE(MqConstant.DEAD_LETTER_QUEUE),
    DELAYED_QUEUE(MqConstant.DELAYED_QUEUE)


    ;


    /* 队列 */
    private String queue;


    private MqQueueEnum(String queue) {
        this.queue = queue;
    }

    public String getQueue() {
        return queue;
    }
}
