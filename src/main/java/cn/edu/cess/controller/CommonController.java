package cn.edu.cess.controller;

import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.util.SnowFlakeId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Package: cn.edu.cess.controller
 * @Description:
 * @Author: LuDeSong
 * @Date: 2021-6-8 20:46
 */

@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/getSnowFlakeId")
    public Result getSnowFlakeId(){
        long generate = SnowFlakeId.generate();
        return ResultFactory.buildSuccessResult(generate);
    }
}
