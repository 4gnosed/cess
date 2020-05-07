package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.ScoreItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
public interface IScoreItemService extends IService<ScoreItem> {

    boolean adds(Integer scoreSheetId, List<ScoreItem> itemList);

    List<ScoreItem> getsBySid(Integer scoreSheetId);

    boolean updateBySid(Integer scoreSheetId, List<ScoreItem> itemList);
}
