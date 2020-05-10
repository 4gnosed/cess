package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.SheetOffer;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
public interface ISheetOfferService extends IService<SheetOffer> {

    SheetOffer getByRidPid(Integer rid, Integer pid);

    SheetOffer add(Integer rid, Integer pid, SheetOffer sheetOffer);

    boolean update(SheetOffer sheetOffer);
}
