package cn.edu.cess.controller.seckill;

import cn.edu.cess.entity.seckill.Good;
import cn.edu.cess.result.Result;
import cn.edu.cess.util.HttpSendUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Package: cn.edu.cess.controller.seckill
 * @Description:对接秒杀系统
 * @Author: LuDeSong
 * @Date: 2021-7-28 14:58
 */

@RestController
@RequestMapping("/api/seckill")
public class SeckillController {

    @Value("${seckill.url}")
    String seckillUrl;

    @GetMapping("/good/buy")
    public Result buyGood(@RequestParam("goodId") String goodId,
                          @RequestParam("quantity") Integer quantity,
                          @RequestParam("userId") Integer userId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("goodId", goodId);
        map.put("quantity", quantity.toString());
        map.put("userId", userId.toString());
        return HttpSendUtil.doGet(seckillUrl + "/good/buy", map, Result.class);
    }

    @PostMapping("/good")
    public Result saveGood(@RequestBody Good good) {
        return HttpSendUtil.doPost(seckillUrl + "/good", JSON.toJSONString(good), Result.class);
    }

    @GetMapping("/order")
    public Result queryOrder(@RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "10") Integer size,
                             @RequestParam String goodId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page", page.toString());
        map.put("size", size.toString());
        map.put("goodId", goodId);
        return HttpSendUtil.doGet(seckillUrl + "/order", map, Result.class);

    }

}
