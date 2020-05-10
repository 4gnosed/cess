package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.SheetContract;
import cn.edu.cess.entity.content.student.ResumePositions;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.ISheetContractService;
import cn.edu.cess.service.content.student.IResumePositionsService;
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
@RequestMapping("/api/sheetContract")
public class SheetContractController extends AbstractClass {
    @Autowired
    ISheetContractService iSheetContractService;
    @Autowired
    IResumePositionsService iResumePositionsService;

    @GetMapping("/getById")
    public Result getById(@RequestParam Integer cid) {
        return ResultFactory.buildSuccessResult(iSheetContractService.getById(cid));
    }

    @GetMapping("")
    public Result get(@RequestParam Integer rid, @RequestParam Integer pid) {
        return ResultFactory.buildSuccessResult(iSheetContractService.getByRidPid(rid, pid));
    }

    @PostMapping("")
    public Result add(@RequestParam Integer rid, @RequestParam Integer pid, @RequestBody SheetContract sheetContract) {
        return ResultFactory.buildSuccessResult(iSheetContractService.add(rid, pid, sheetContract));
    }

    @PutMapping("")
    public Result alter(@RequestBody SheetContract sheetContract) {
        return ResultFactory.buildSuccessResult(iSheetContractService.update(sheetContract));
    }

    @PutMapping("/confirm/enterprise")
    public Result enterpriseConfirmContract(@RequestParam Integer cid) {
        if (iSheetContractService.enterpriseConfirmContract(cid)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }

    @PutMapping("/confirm/student")
    public Result studentConfirmContract(@RequestParam Integer cid) {
        if (iSheetContractService.studentConfirmContract(cid)) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }

    @PutMapping("/confirm/school/enabled")
    public Result schoolConfirmContractEnabled(@RequestBody SheetContract sheetContract) {
        //更新学校契约
        boolean b1 = iSheetContractService.update(sheetContract);
        //确定学校契约
        boolean b2 = iSheetContractService.schoolConfirmContract(sheetContract.getId());
        //使其生效
        boolean b3 = iSheetContractService.enabled(sheetContract.getId());
        //更新简历状态，进入下一环节（待入职）
        ResumePositions resumePositions = iResumePositionsService.getBySheetContractId(sheetContract.getId());
        boolean b4 = iResumePositionsService.changeState(
                resumePositions.getRid(), resumePositions.getPid(), Constant.EMPLOYED_STATE_ID);
        if (b1 && b2 && b3 && b4) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }


    @PutMapping("/confirm/school/disabled")
    public Result schoolConfirmContractDisabled(@RequestBody SheetContract sheetContract) {
        //更新学校契约
        boolean b1 = iSheetContractService.update(sheetContract);
        //确定学校契约
        boolean b2 = iSheetContractService.schoolConfirmContract(sheetContract.getId());
        //使其失效
        boolean b3 = iSheetContractService.disabled(sheetContract.getId());
        if (b1 && b2 && b3) {
            return ResultFactory.buildSuccessResult("");
        }
        return ResultFactory.buildFailResult("");
    }
}
