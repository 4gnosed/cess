package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.SheetOffer;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.ISheetOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/api/sheetOffer")
public class SheetOfferController extends AbstractClass {
    @Autowired
    ISheetOfferService iSheetOfferService;

    @GetMapping("")
    public Result get(@RequestParam Integer rid, @RequestParam Integer pid) {
        return ResultFactory.buildSuccessResult(iSheetOfferService.getByRidPid(rid, pid));
    }

    @PostMapping("")
    public Result add(@RequestParam Integer rid, @RequestParam Integer pid, @RequestBody SheetOffer sheetOffer) {
        return ResultFactory.buildSuccessResult(iSheetOfferService.add(rid, pid, sheetOffer));
    }

    @PutMapping("")
    public Result alter(@RequestBody SheetOffer sheetOffer) {
        return ResultFactory.buildSuccessResult(iSheetOfferService.update(sheetOffer));
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam Integer oid) {
        return ResultFactory.buildSuccessResult(iSheetOfferService.getById(oid));
    }

    @GetMapping("/getAll")
    public Result getAll() {
        return ResultFactory.buildSuccessResult(iSheetOfferService.list());
    }
}
