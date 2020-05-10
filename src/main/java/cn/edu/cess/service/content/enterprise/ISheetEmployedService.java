package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.SheetEmployed;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
public interface ISheetEmployedService extends IService<SheetEmployed> {

    SheetEmployed getByRidPid(Integer rid, Integer pid);

    SheetEmployed add(Integer rid, Integer pid, SheetEmployed sheetEmployed);

    boolean update(SheetEmployed sheetEmployed);
}
