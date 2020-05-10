package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.SheetOffer;
import cn.edu.cess.mapper.content.enterprise.SheetOfferMapper;
import cn.edu.cess.service.content.enterprise.ISheetOfferService;
import cn.edu.cess.service.content.student.IResumePositionsService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@Service
public class SheetOfferServiceImpl extends ServiceImpl<SheetOfferMapper, SheetOffer> implements ISheetOfferService {

    @Autowired
    IResumePositionsService iResumePositionsService;

    @Override
    public SheetOffer getByRidPid(Integer rid, Integer pid) {
        Integer sheetOfferId = iResumePositionsService.getOfferIdByRidPid(rid, pid);
        if (sheetOfferId == null) {
            return null;
        } else {
            return getById(sheetOfferId);
        }
    }

    @Override
    public SheetOffer add(Integer rid, Integer pid, SheetOffer sheetOffer) {
        save(sheetOffer);
        Integer positionsId = sheetOffer.getPositionsId();
        Integer newPositionsId = sheetOffer.getNewPositionsId();
        SheetOffer sheetOfferSaved = new SheetOffer();
        if (newPositionsId == null) {
            sheetOfferSaved = getByPid(positionsId);
        } else {
            sheetOfferSaved = getByNpid(newPositionsId);
        }
        Integer sheetOfferId = sheetOfferSaved.getId();
        iResumePositionsService.setSheetOfferId(rid, pid, sheetOfferId);
        return sheetOfferSaved;
    }

    @Override
    public boolean update(SheetOffer sheetOffer) {
        if (sheetOffer.getNewPositionsId() == null) {
            sheetOffer.setNewPositionsId(null);
            UpdateWrapper<SheetOffer> u = new UpdateWrapper<>();
            u.eq(Constant.ID, sheetOffer.getId()).set(Constant.NEW_POSITIONS_ID, null);
            return update(sheetOffer, u);
        } else {
            return updateById(sheetOffer);
        }
    }

    private SheetOffer getByNpid(Integer newPositionsId) {
        QueryWrapper<SheetOffer> q = new QueryWrapper<>();
        q.eq(Constant.NEW_POSITIONS_ID, newPositionsId);
        return getOne(q);
    }

    private SheetOffer getByPid(Integer positionsId) {
        QueryWrapper<SheetOffer> q = new QueryWrapper<>();
        q.eq(Constant.POSITIONS_ID, positionsId);
        return getOne(q);
    }
}
