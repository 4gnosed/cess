package cn.edu.cess.constant;

/**
 * @Package: cn.edu.cess.constant
 * @Description:消息中间件相关常量
 * @Author: LuDeSong
 * @Date: 2021-8-9 14:04
 */

public class MqConstant {
    public static final String ROUTING_KEY = "routing.key";
    public static final String ROUTING_KEY_2 = "routing.key2";
    public static final String DEAD_LETTER_ROUTING_KEY = "dead.letter.routing.key";
    public static final String DELAYED_ROUTING_KEY = "delayed.routing.key";
    public static final String ROUTING_KEY_STAR = "top.*";
    public static final String ROUTING_KEY_WELL = "top.#";
    public static final String DIRECT_EXCHANGE_1 = "DIRECT_EXCHANGE_1";
    public static final String DIRECT_EXCHANGE_2 = "DIRECT_EXCHANGE_2";
    public static final String DEAD_LETTER_DIRECT_EXCHANGE = "DEAD_LETTER_DIRECT_EXCHANGE";
    public static final String DELAYED_DIRECT_EXCHANGE = "DELAYED_DIRECT_EXCHANGE";
    public static final String TOPIC_EXCHANGE_1 = "TOPIC_EXCHANGE_1";
    public static final String FANOUT_EXCHANGE_1 = "FANOUT_EXCHANGE_1";
    public static final String QUEUE1 = "QUEUE1";
    public static final String QUEUE2 = "QUEUE2";
    public static final String QUEUE3 = "QUEUE3";
    public static final String QUEUE4 = "QUEUE4";
    public static final String QUEUE5 = "QUEUE5";
    public static final String QUEUE6 = "QUEUE6";
    public static final String DEAD_LETTER_QUEUE = "DEAD_LETTER_QUEUE";
    public static final String DELAYED_QUEUE = "DELAYED_QUEUE";
}
