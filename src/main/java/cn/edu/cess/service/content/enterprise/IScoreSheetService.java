package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.ScoreSheet;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
public interface IScoreSheetService extends IService<ScoreSheet> {

    ScoreSheet add(Integer rid, Integer pid, ScoreSheet scoreSheet);

    ScoreSheet getByAdvice(String advice);

    ScoreSheet getByRidPid(Integer rid, Integer pid);

    boolean alter(ScoreSheet scoreSheet);
}
