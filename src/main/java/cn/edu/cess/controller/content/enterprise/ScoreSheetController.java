package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.content.enterprise.ScoreSheet;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IScoreSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/api/scoreSheet")
public class ScoreSheetController extends AbstractClass {
    @Autowired
    IScoreSheetService iScoreSheetService;

    @PostMapping("")
    public Result add(@RequestParam Integer rid, @RequestParam Integer pid, @RequestBody ScoreSheet scoreSheet) {
        return ResultFactory.buildSuccessResult(iScoreSheetService.add(rid, pid, scoreSheet));
    }

    @GetMapping("")
    public Result get(@RequestParam Integer rid, @RequestParam Integer pid) {
        return ResultFactory.buildSuccessResult(iScoreSheetService.getByRidPid(rid, pid));
    }

    @PutMapping("")
    public Result alert(@RequestBody ScoreSheet scoreSheet) {
        if (iScoreSheetService.alter(scoreSheet)) {
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }
}
