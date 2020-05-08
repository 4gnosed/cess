package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.ScoreItem;
import cn.edu.cess.entity.content.enterprise.ScoreSheet;
import cn.edu.cess.mapper.content.enterprise.ScoreSheetMapper;
import cn.edu.cess.service.content.enterprise.IScoreItemService;
import cn.edu.cess.service.content.enterprise.IScoreSheetService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@Service
public class ScoreSheetServiceImpl extends ServiceImpl<ScoreSheetMapper, ScoreSheet> implements IScoreSheetService {

    @Autowired
    IScoreItemService iScoreItemService;
    @Autowired
    IResumePositionsService iResumePositionsService;

    @Override
    public ScoreSheet add(Integer rid, Integer pid, ScoreSheet scoreSheet) {
        List<ScoreItem> itemList = scoreSheet.getItemList();
        save(scoreSheet);
        Integer scoreSheetId = getByAdvice(scoreSheet.getAdvice()).getId();
        scoreSheet.setId(scoreSheetId);
        iScoreItemService.adds(scoreSheetId, itemList);
        iResumePositionsService.setScoreSheetId(rid, pid, scoreSheetId);
        return scoreSheet;
    }

    @Override
    public ScoreSheet getByAdvice(String advice) {
        QueryWrapper<ScoreSheet> q = new QueryWrapper<>();
        q.eq(Constant.ADVICE, advice).last("LIMIT 1");
        return getOne(q);

    }

    @Override
    public ScoreSheet getByRidPid(Integer rid, Integer pid) {
        Integer scoreSheetId = iResumePositionsService.getSidByRidPid(rid, pid);
        if (scoreSheetId == null) {
            return null;
        }
        ScoreSheet scoreSheet = getById(scoreSheetId);
        List<ScoreItem> itemList = iScoreItemService.getsBySid(scoreSheetId);
        scoreSheet.setItemList(itemList);
        return scoreSheet;
    }

    @Override
    public boolean alter(ScoreSheet scoreSheet) {
        Integer scoreSheetId = scoreSheet.getId();
        List<ScoreItem> itemList = scoreSheet.getItemList();
        if (!iScoreItemService.updateBySid(scoreSheetId, itemList)) {
            return false;
        }
        UpdateWrapper<ScoreSheet> u = new UpdateWrapper<>();
        u.eq(Constant.ID, scoreSheetId);
        if (!update(scoreSheet, u)) {
            return false;
        }
        return true;
    }
}
