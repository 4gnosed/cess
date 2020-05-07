package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.ScoreItem;
import cn.edu.cess.mapper.content.enterprise.ScoreItemMapper;
import cn.edu.cess.service.content.enterprise.IScoreItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@Service
public class ScoreItemServiceImpl extends ServiceImpl<ScoreItemMapper, ScoreItem> implements IScoreItemService {

    @Override
    public boolean adds(Integer scoreSheetId, List<ScoreItem> itemList) {
        for (ScoreItem scoreItem : itemList) {
            scoreItem.setScoreSheetId(scoreSheetId);
            save(scoreItem);
        }
        return true;
    }

    @Override
    public List<ScoreItem> getsBySid(Integer scoreSheetId) {
        QueryWrapper<ScoreItem> q = new QueryWrapper<>();
        q.eq(Constant.SCORE_SHEET_ID, scoreSheetId);
        List<ScoreItem> itemList = list(q);
        if (itemList.size() == 0) {
            itemList.add(new ScoreItem());
        }
        return itemList;
    }

    @Override
    public boolean updateBySid(Integer scoreSheetId, List<ScoreItem> itemList) {
        QueryWrapper<ScoreItem> q = new QueryWrapper<>();
        q.eq(Constant.SCORE_SHEET_ID, scoreSheetId);
        remove(q);
        return adds(scoreSheetId, itemList);
    }
}
