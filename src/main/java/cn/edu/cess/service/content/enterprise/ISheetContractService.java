package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.SheetContract;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
public interface ISheetContractService extends IService<SheetContract> {

    SheetContract getByRidPid(Integer rid, Integer pid);

    SheetContract add(Integer rid, Integer pid, SheetContract sheetContract);

    boolean update(SheetContract sheetContract);

    boolean enterpriseConfirmContract(Integer cid);

    boolean studentConfirmContract(Integer cid);

    boolean schoolConfirmContract(Integer cid);

    boolean enabled(Integer cid);

    boolean disabled(Integer cid);
}
