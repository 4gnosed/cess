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
        return ResultFactory.buildSuccessResult(iEnterpriseService.list());
    }

    @GetMapping("/getOne")
    public Result getOneEnterprise(@RequestParam("userId") Integer userId) {
        //根据用户角色绑定对应的角色对象
        UserEnterprise userEnterprise = iUserEnterpriseService.getByUid(userId);
        if (userEnterprise == null) {
            //未填写信息
            return ResultFactory.buildEmptyResult("");
        } else if (userEnterprise.getEnabled() == false) {
            //未审核通过
            return ResultFactory.buildFailResult("");
        }
        Enterprise Enterprise = iEnterpriseService.getById(userEnterprise.getEid());
        iEnterpriseService.fillData(Enterprise);
        return ResultFactory.buildSuccessResult(Enterprise);
    }

    @PutMapping("")
    public Result editEnterprise(@RequestBody Enterprise enterprise) {
        iEnterpriseService.updateEnterprise(enterprise);
        return ResultFactory.buildSuccessResult("");
    }

    @PostMapping("")
    public Result addEnterprise(@RequestBody Enterprise enterprise) {
//        enterprise.setId(iEnterpriseService.getLastId() + 1);
        enterprise = iEnterpriseService.getByName(enterprise.getName());
        UserEnterprise userEnterprise = new UserEnterprise();
        userEnterprise.setUid(enterprise.getUserId());
        userEnterprise.setEid(enterprise.getId());
        userEnterprise.setEnabled(false);
        iUserEnterpriseService.save(userEnterprise);
        iEnterpriseService.save(enterprise);
        return ResultFactory.buildSuccessResult(enterprise);
    }
}
