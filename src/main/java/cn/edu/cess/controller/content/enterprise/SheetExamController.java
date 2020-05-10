package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.SheetExam;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.ISheetExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
@RestController
@RequestMapping("/api/sheetExam")
public class SheetExamController extends AbstractClass {
    @Autowired
    ISheetExamService iSheetExamService;

    @GetMapping("")
    public Result get(@RequestParam Integer rid, @RequestParam Integer pid) {
        return ResultFactory.buildSuccessResult(iSheetExamService.getByRidPid(rid, pid));
    }

    @PostMapping("")
    public Result add(@RequestParam Integer rid, @RequestParam Integer pid, @RequestBody SheetExam sheetExam) {
        return ResultFactory.buildSuccessResult(iSheetExamService.add(rid, pid, sheetExam));
    }

    @PutMapping("")
    public Result alter(@RequestBody SheetExam sheetExam) {
        return ResultFactory.buildSuccessResult(iSheetExamService.update(sheetExam));
    }
}
