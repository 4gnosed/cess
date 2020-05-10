package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.SheetExam;
import cn.edu.cess.mapper.content.enterprise.SheetExamMapper;
import cn.edu.cess.service.content.enterprise.ISheetExamService;
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
public class SheetExamServiceImpl extends ServiceImpl<SheetExamMapper, SheetExam> implements ISheetExamService {

    @Autowired
    IResumePositionsService iResumePositionsService;

    @Override
    public SheetExam getByRidPid(Integer rid, Integer pid) {
        Integer examId = iResumePositionsService.getExamIdByRidPid(rid, pid);
        if (examId == null) {
            return null;
        } else {
            return getById(examId);
        }
    }

    @Override
    public SheetExam add(Integer rid, Integer pid, SheetExam sheetExam) {
        save(sheetExam);
        SheetExam sheetExamSaved = getByNotId(sheetExam);
        Integer sheetExamId = sheetExamSaved.getId();
        iResumePositionsService.setSheetExamId(rid,pid, sheetExamId);
        return sheetExamSaved;
    }

    private SheetExam getByNotId(SheetExam sheetExam) {
        QueryWrapper<SheetExam> q = new QueryWrapper<>();
        q.eq(Constant.CONTENT, sheetExam.getContent())
                .eq(Constant.SCORE, sheetExam.getScore())
                .eq(Constant.DATE, sheetExam.getDate())
                .eq(Constant.TIME, sheetExam.getTime())
                .eq(Constant.DURATION, sheetExam.getDuration()).last("LIMIT 1");
        return getOne(q);
    }

    @Override
    public boolean update(SheetExam sheetExam) {
        return updateById(sheetExam);
    }
}
