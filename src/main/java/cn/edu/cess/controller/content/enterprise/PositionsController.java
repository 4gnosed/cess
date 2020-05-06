package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.util.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
@RestController
@RequestMapping("/api/positions")
public class PositionsController extends AbstractClass {
    @Autowired
    IPositionsService iPositionsService;

    @PostMapping("")
    public Result addPosition(@RequestBody Positions position) {
        position.setUpdateTime(DateTimeUtils.getCurrentDate());
        position.setEnabled(true);
        iPositionsService.savePosition(position);
        return ResultFactory.buildSuccessResult("");
    }

    @PutMapping("")
    public Result alterPosition(@RequestBody Positions position) {
        position.setUpdateTime(DateTimeUtils.getCurrentDate());
        position.setEnabled(true);
        iPositionsService.updateById(position);
        return ResultFactory.buildSuccessResult("");
    }

    @GetMapping("")
    public Result getAll(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,
                         @RequestParam(required = false) String keywords,
                         @RequestParam(required = false) Integer experienceId,
                         @RequestParam(required = false) Integer degreeId,
                         @RequestParam(required = false) Integer salaryId,
                         @RequestParam(required = false) Integer financeId,
                         @RequestParam(required = false) Integer scaleId) {
        return ResultFactory.buildSuccessResult(iPositionsService.getByPage(page, size, keywords, experienceId,
                degreeId, salaryId, financeId, scaleId));
    }

    @GetMapping("/getByEid")
    public Result getByEid(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           @RequestParam() Integer eid) {
        return ResultFactory.buildSuccessResult(iPositionsService.getByPage(page, size, eid));
    }

    @GetMapping("/getAllByUid")
    public Result getAllByUid(@RequestParam() Integer userId) {
        return ResultFactory.buildSuccessResult(iPositionsService.getPositionsListByUid(userId));
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam Integer positionId) {
        return ResultFactory.buildSuccessResult(iPositionsService.getById(positionId));
    }
}
