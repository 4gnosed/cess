package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.EnterprisePlace;
import cn.edu.cess.entity.content.enterprise.Place;
import cn.edu.cess.mapper.content.enterprise.EnterprisePlaceMapper;
import cn.edu.cess.service.content.enterprise.IEnterprisePlaceService;
import cn.edu.cess.service.content.enterprise.IEnterpriseService;
import cn.edu.cess.service.content.enterprise.IPlaceService;
import cn.edu.cess.util.DateTimeUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-03
 */
@Service
public class EnterprisePlaceServiceImpl extends ServiceImpl<EnterprisePlaceMapper, EnterprisePlace> implements IEnterprisePlaceService {

    @Autowired
    IPlaceService iPlaceService;
    @Autowired
    IEnterpriseService iEnterpriseService;

    @Override
    public List<EnterprisePlace> getByTalkDate(String talkDate) {
        QueryWrapper<EnterprisePlace> q = new QueryWrapper<>();
        q.eq(Constant.TALK_DATE, talkDate);
        return list(q);
    }

    @Override
    public EnterprisePlace getByEid(Integer id) {
        QueryWrapper<EnterprisePlace> q = new QueryWrapper<>();
        q.eq(Constant.EID, id).last("LIMIT 1");
        return getOne(q);
    }

    @Override
    public boolean save(Integer eid, Integer pid, Integer talkTimeId) {
        //验证是否存在
        Place place = iPlaceService.getById(pid);
        Enterprise enterprise = iEnterpriseService.getById(eid);
        if (place == null || enterprise == null) {
            return false;
        }
        EnterprisePlace enterprisePlace = new EnterprisePlace();
        enterprisePlace.setEid(eid);
        enterprisePlace.setPid(pid);
        enterprisePlace.setTalkTimeId(talkTimeId);
        Date talkDate = enterprise.getTalkDate();
        enterprisePlace.setTalkDate(talkDate);
        //是否过期
        if (talkDate.compareTo(DateTimeUtils.getCurrentDate()) >= 0) {
            enterprisePlace.setExpire(false);
        } else {
            enterprisePlace.setExpire(true);
        }
        save(enterprisePlace);
        return true;
    }
}
