package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.content.enterprise.IEnterpriseService;
import cn.edu.cess.service.content.enterprise.IUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/api/enterprise")
public class EnterpriseController extends AbstractClass {

    @Autowired
    IUserEnterpriseService iUserEnterpriseService;

    @Autowired
    IEnterpriseService iEnterpriseService;

    @GetMapping("")
    public Result getAll() {
        List<Enterprise> enterpriseList = iEnterpriseService.list();
        return ResultFactory.buildSuccessResult(enterpriseList);
    }

    @GetMapping("/getById")
    public Result getById(@RequestParam Integer eid) {
        Enterprise enterprise = iEnterpriseService.getById(eid);
        iEnterpriseService.fillData(enterprise);
        return ResultFactory.buildSuccessResult(enterprise);
    }

    @GetMapping("/getOne")
    public Result getOneEnterprise(@RequestParam("userId") Integer userId) {
        //根据用户角色绑定对应的角色对象
        UserEnterprise userEnterprise = iUserEnterpriseService.getByUid(userId);
        if (userEnterprise == null) {
            //未填写信息
            return ResultFactory.buildEmptyResult("");
        }
        Enterprise enterprise = iEnterpriseService.getById(userEnterprise.getEid());
        iEnterpriseService.fillData(enterprise);
        if (userEnterprise.getEnabled() == false) {
            //未审核通过
            return ResultFactory.buildNotCheckResult(enterprise);
        }
        return ResultFactory.buildSuccessResult(enterprise);
    }

    @PutMapping("")
    public Result editEnterprise(@RequestBody Enterprise enterprise) {
        iEnterpriseService.updateEnterprise(enterprise);
        return ResultFactory.buildSuccessResult("");
    }

    @PostMapping("")
    public Result addEnterprise(@RequestBody Enterprise enterprise) {
//        enterprise.setId(iEnterpriseService.getLastId() + 1);
        Integer userId = enterprise.getUserId();
        iEnterpriseService.save(enterprise);
        enterprise = iEnterpriseService.getByName(enterprise.getName());
        UserEnterprise userEnterprise = new UserEnterprise();
        userEnterprise.setUid(userId);
        userEnterprise.setEid(enterprise.getId());
        userEnterprise.setEnabled(false);
        iUserEnterpriseService.save(userEnterprise);
        return ResultFactory.buildSuccessResult(enterprise);
    }
}
