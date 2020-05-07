package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.Place;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IPlaceService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/api/place")
public class PlaceController extends AbstractClass {
    @Autowired
    IPlaceService iPlaceService;

    @GetMapping("")
    public Result getAll(@RequestParam String talkDate) {
        List<Place> placeList = iPlaceService.list();
        iPlaceService.fillData(talkDate,placeList);
        return ResultFactory.buildSuccessResult(placeList);
    }
}
