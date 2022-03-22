package cn.edu.cess.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;


@Component
public class RedisKeyExpirationListener implements MessageListener {

    protected Logger log = LoggerFactory.getLogger(getClass());


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String key = message.toString();
        log.info("RedisKeyExpirationListener--redis过期键：{}", key);
    }
}
