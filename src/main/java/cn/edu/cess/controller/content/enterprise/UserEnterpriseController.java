package cn.edu.cess.controller.content.enterprise;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.entity.User;
import cn.edu.cess.entity.Vo.UserEnterpriseVo;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.UserEnterprise;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.result.ResultPage;
import cn.edu.cess.service.IUserService;
import cn.edu.cess.service.content.enterprise.IEnterpriseService;
import cn.edu.cess.service.content.enterprise.IUserEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-09
 */
@RestController
@RequestMapping("/api/userEnterprise")
public class UserEnterpriseController extends AbstractClass {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserEnterpriseService iUserEnterpriseService;
    @Autowired
    IEnterpriseService iEnterpriseService;
    
    @RequestMapping("")
    public Result getUserEnterpriseVoByPage(@RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        ArrayList<UserEnterpriseVo> userEnterpriseVos = new ArrayList<>();
        List<UserEnterprise> userEnterpriseList = iUserEnterpriseService.getUserEnterprisesByPage(page, size);
        //遍历所有用户-企业的关系对象
        for (UserEnterprise userEnterprise : userEnterpriseList) {
            UserEnterpriseVo userEnterpriseVo = new UserEnterpriseVo();
            User user = iUserService.getById(userEnterprise.getUid());
            Enterprise enterprise = iEnterpriseService.getById(userEnterprise.getEid());
            //填充企业其它信息
            iEnterpriseService.fillData(enterprise);
            userEnterpriseVo.setId(userEnterprise.getId());
            userEnterpriseVo.setUser(user);
            userEnterpriseVo.setEnterprise(enterprise);
            userEnterpriseVo.setEnabled(userEnterprise.getEnabled());
            userEnterpriseVos.add(userEnterpriseVo);
        }
        ResultPage resultPage = new ResultPage();
        resultPage.setTotal((long) userEnterpriseList.size());
        resultPage.setData(userEnterpriseVos);
        return ResultFactory.buildSuccessResult(resultPage);
    }

    @PutMapping("/status")
    public Result checkStatus(@RequestParam("enabled") boolean enabled, @RequestParam("id") String id) {
        iUserEnterpriseService.updateStatus(enabled, id);
        return ResultFactory.buildSuccessResult("");
    }
}
