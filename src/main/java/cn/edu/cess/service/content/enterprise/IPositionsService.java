package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.result.ResultPage;
import com.baomidou.mybatisplus.extension.service.IService;

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

    ResultPage getByPage(Integer page, Integer size, Positions positions);
}
