package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IPositionsService;
import cn.edu.cess.util.DateTimeUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.edu.cess.base.AbstractClass;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
@Api("职位信息api")
@RestController
@RequestMapping("/api/positions")
public class PositionsController extends AbstractClass {
    @Autowired
    IPositionsService iPositionsService;

    @ApiOperation("添加职位")
    @PostMapping("")
    public Result addPosition(@RequestBody Positions position) {
        position.setUpdateTime(DateTimeUtils.getCurrentDate());
        position.setEnabled(true);
        iPositionsService.savePosition(position);
        return ResultFactory.buildSuccessResult("");
    }

    @ApiOperation("更新职位")
    @PutMapping("")
    public Result alterPosition(@RequestBody Positions position) {
        position.setUpdateTime(DateTimeUtils.getCurrentDate());
        position.setEnabled(true);
        iPositionsService.updateById(position);
        return ResultFactory.buildSuccessResult("");
    }

    @ApiOperation("分页查询职位")
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

    @ApiOperation("通过企业ID查询职位")
    @GetMapping("/getByEid")
    public Result getByEid(@RequestParam(defaultValue = "1") Integer page,
                           @RequestParam(defaultValue = "10") Integer size,
                           @RequestParam() Integer eid) {
        return ResultFactory.buildSuccessResult(iPositionsService.getByPage(page, size, eid));
    }

    @ApiOperation("通过用户ID查询职位")
    @GetMapping("/getAllByUid")
    public Result getAllByUid(@RequestParam() Integer userId) {
        return ResultFactory.buildSuccessResult(iPositionsService.getPositionsListByUid(userId));
    }

    @ApiOperation("通过ID查询职位")
    @GetMapping("/getById")
    public Result getById(@RequestParam Integer positionId) {
        return ResultFactory.buildSuccessResult(iPositionsService.getById(positionId));
    }

    @ApiOperation("查询各薪资分布职位数量")
    @GetMapping("/getNumber")
    public Result getAll() {
        return ResultFactory.buildSuccessResult(iPositionsService.getNumber());
    }

    @ApiOperation("查询职位薪资")
    @PostMapping("/getSalaryIds")
    public Result getSalaryIds(@RequestBody() String ids) {
        List<Integer> positionIds = JSONObject.parseArray(ids, Integer.class);
        return ResultFactory.buildSuccessResult(iPositionsService.getSalaryIds(positionIds));
    }
}
