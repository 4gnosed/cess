package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.Vo.NumberVo;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.EnterprisePositions;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务类
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

    void fillData(Positions positions);

    void fillData(Positions positions, QueryWrapper<EnterprisePositions> epQueryWrapper);

    void fillData(Collection<Positions> positionsCollection, QueryWrapper<EnterprisePositions> epQueryWrapper);

    Enterprise getEnterpriseByPid(Integer pid);

    NumberVo getNumber();

    List<Integer> getSalaryIds(List<Integer> positionIds);
}
