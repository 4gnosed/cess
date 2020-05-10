package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.SheetContract;
import cn.edu.cess.mapper.content.enterprise.SheetContractMapper;
import cn.edu.cess.service.content.enterprise.ISheetContractService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
@Service
public class SheetContractServiceImpl extends ServiceImpl<SheetContractMapper, SheetContract> implements ISheetContractService {
    @Autowired
    IResumePositionsService iResumePositionsService;

    @Override
    public SheetContract getByRidPid(Integer rid, Integer pid) {
        Integer ContractId = iResumePositionsService.getContractIdByRidPid(rid, pid);
        if (ContractId == null) {
            return null;
        } else {
            return getById(ContractId);
        }
    }

    @Override
    public SheetContract add(Integer rid, Integer pid, SheetContract sheetContract) {
        sheetContract.setEnterpriseConfirm(true);
        sheetContract.setStudentConfirm(false);
        sheetContract.setSchoolConfirm(false);
        sheetContract.setEnabled(false);
        save(sheetContract);
        SheetContract sheetContractSaved = getByEnterpriseStudentSign(sheetContract);
        Integer sheetContractId = sheetContractSaved.getId();
        iResumePositionsService.setSheetContractId(rid, pid, sheetContractId);
        return sheetContractSaved;
    }

    private SheetContract getByEnterpriseStudentSign(SheetContract sheetContract) {
        QueryWrapper<SheetContract> q = new QueryWrapper<>();
        q.eq(Constant.ENTERPRISE_SIGN, sheetContract.getEnterpriseSign())
                .eq(Constant.STUDENT_SIGN, sheetContract.getStudentSign()).last("LIMIT 1");
        return getOne(q);
    }

    @Override
    public boolean update(SheetContract sheetContract) {
//        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
//        u.eq(Constant.ID, sheetContract.getId());
//        if (sheetContract.getEnterpriseSign() != null && sheetContract.getEnterpriseSign() != "") {
//            u.set(Constant.ENTERPRISE_CONFIRM, true);
//        }
//        if (sheetContract.getStudentSign() != null && sheetContract.getStudentSign() != "") {
//            u.set(Constant.STUDENT_CONFIRM, true);
//        }
//        if (sheetContract.getSchoolSign() != null && sheetContract.getSchoolSign() != "") {
//            u.set(Constant.SCHOOL_CONFIRM, true);
//        }
//        return update(sheetContract, u);
        return updateById(sheetContract);
    }

    @Override
    public boolean enterpriseConfirmContract(Integer cid) {
        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
        u.eq(Constant.ID, cid).set(Constant.ENTERPRISE_CONFIRM, true);
        return update(u);
    }

    @Override
    public boolean studentConfirmContract(Integer cid) {
        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
        u.eq(Constant.ID, cid).set(Constant.STUDENT_CONFIRM, true);
        return update(u);
    }

    @Override
    public boolean schoolConfirmContract(Integer cid) {
        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
        u.eq(Constant.ID, cid).set(Constant.SCHOOL_CONFIRM, true);
        return update(u);
    }

    @Override
    public boolean enabled(Integer cid) {
        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
        u.eq(Constant.ID, cid).set(Constant.ENABLED, true);
        return update(u);
    }

    @Override
    public boolean disabled(Integer contractId) {
        UpdateWrapper<SheetContract> u = new UpdateWrapper<>();
        u.eq(Constant.ID, contractId).set(Constant.ENABLED, false);
        return update(u);
    }
}
