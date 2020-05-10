package cn.edu.cess.service.content.student;

import cn.edu.cess.entity.content.student.ResumePositions;
import cn.edu.cess.entity.content.student.Student;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-22
 */
public interface IResumePositionsService extends IService<ResumePositions> {

    boolean saveResumePositions(Integer positionId, Integer rid);

    List<ResumePositions> getByPid(Integer pid);

    List<ResumePositions> getByRid(Integer rid);

    boolean changeState(Integer rid, Integer pid, Integer stateId);

    boolean deleteResumePositions(Integer rid, Integer pid);

    boolean setScoreSheetId(Integer rid, Integer pid, Integer scoreSheetId);

    Integer getScoreIdByRidPid(Integer rid, Integer pid);

    Integer getOfferIdByRidPid(Integer rid, Integer pid);

    Integer getExamIdByRidPid(Integer rid, Integer pid);

    Integer getContractIdByRidPid(Integer rid, Integer pid);

    Integer getEmployedIdByRidPid(Integer rid, Integer pid);

    ResumePositions getResumePositions(Integer rid, Integer pid);

    void setSheetOfferId(Integer rid, Integer pid, Integer sheetOfferId);

    void setSheetExamId(Integer rid, Integer pid, Integer sheetExamId);

    void setSheetContractId(Integer rid, Integer pid, Integer sheetContractIId);

    void setSheetEmployedId(Integer rid, Integer pid, Integer sheetEmployedId);

    ResultPage getContractVosByPage(Integer page, Integer size);

    Student getStudentByResumeId(Integer rid);

    ResumePositions getBySheetContractId(Integer sheetContractId);
}
