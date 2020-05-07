package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ResumePositions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-22
 */
public interface IResumePositionsService extends IService<ResumePositions> {

    boolean saveResumePositions(Integer positionId, Integer rid);

    List<ResumePositions> getByPid(Integer pid);

    boolean changeState(Integer rid, Integer pid, Integer stateId);

    boolean deleteResumePostions(Integer rid, Integer pid);

    boolean setScoreSheetId(Integer rid, Integer pid, Integer scoreSheetId);

    Integer getSidByRidPid(Integer rid, Integer pid);

    Integer getOidByRidPid(Integer rid, Integer pid);

    void setSheetOfferId(Integer rid, Integer pid, Integer sheetOfferId);
}
