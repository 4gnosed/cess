package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.SheetEmployed;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.ISheetEmployedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/api/sheetEmployed")
public class SheetEmployedController extends AbstractClass {
    @Autowired
    ISheetEmployedService iSheetEmployedService;

    @GetMapping("")
    public Result get(@RequestParam Integer rid, @RequestParam Integer pid) {
        return ResultFactory.buildSuccessResult(iSheetEmployedService.getByRidPid(rid, pid));
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam Integer eid) {
        return ResultFactory.buildSuccessResult(iSheetEmployedService.getById(eid));
    }

    @PostMapping("")
    public Result add(@RequestParam Integer rid, @RequestParam Integer pid, @RequestBody SheetEmployed sheetEmployed) {
        return ResultFactory.buildSuccessResult(iSheetEmployedService.add(rid, pid, sheetEmployed));
    }

    @PutMapping("")
    public Result alter(@RequestBody SheetEmployed sheetEmployed) {
        return ResultFactory.buildSuccessResult(iSheetEmployedService.update(sheetEmployed));
    }
}
