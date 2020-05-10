package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.SheetEmployed;
import cn.edu.cess.mapper.content.enterprise.SheetEmployedMapper;
import cn.edu.cess.service.content.enterprise.ISheetEmployedService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SheetEmployedServiceImpl extends ServiceImpl<SheetEmployedMapper, SheetEmployed> implements ISheetEmployedService {
    @Autowired
    IResumePositionsService iResumePositionsService;

    @Override
    public SheetEmployed getByRidPid(Integer rid, Integer pid) {
        Integer EmployedId = iResumePositionsService.getEmployedIdByRidPid(rid, pid);
        if (EmployedId == null) {
            return null;
        } else {
            return getById(EmployedId);
        }
    }

    @Override
    public SheetEmployed add(Integer rid, Integer pid, SheetEmployed sheetEmployed) {
        save(sheetEmployed);
        SheetEmployed sheetEmployedSaved = getByEnterpriseStudentSign(sheetEmployed);
        Integer sheetEmployedId = sheetEmployedSaved.getId();
        iResumePositionsService.setSheetEmployedId(rid, pid, sheetEmployedId);
        return sheetEmployedSaved;
    }

    private SheetEmployed getByEnterpriseStudentSign(SheetEmployed sheetEmployed) {
        QueryWrapper<SheetEmployed> q = new QueryWrapper<>();
        q.eq(Constant.PREPARATION, sheetEmployed.getPreparation())
                .eq(Constant.REQUIREMENTS, sheetEmployed.getRequirements())
                .eq(Constant.ADVICE, sheetEmployed.getAdvice())
                .eq(Constant.ENTERPRISE_HOPE, sheetEmployed.getEnterpriseHope())
                .eq(Constant.REMARK, sheetEmployed.getRemark()).last("LIMIT 1");
        return getOne(q);
    }

    @Override
    public boolean update(SheetEmployed sheetEmployed) {
        return updateById(sheetEmployed);
    }

}
