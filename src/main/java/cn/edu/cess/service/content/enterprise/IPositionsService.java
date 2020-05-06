package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-18
 */
public interface IPositionsService extends IService<Positions> {

    void savePosition(Positions position);

    ResultPage getByPage(Integer page, Integer size, String keywords, Integer experienceId,
                         Integer degreeId, Integer salaryId, Integer financeId, Integer scaleId);

    Integer getUserIdByPid(Integer positionId);

    ResultPage getByPage(Integer page, Integer size, Integer eid);

    List<Positions> getPositionsListByEid(Integer eid);

    List<Positions> getPositionsListByUid(Integer userId);
}
