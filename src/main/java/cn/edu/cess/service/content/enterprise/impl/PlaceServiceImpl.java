package cn.edu.cess.service.content.enterprise.impl;

import cn.edu.cess.entity.content.enterprise.EnterprisePlace;
import cn.edu.cess.entity.content.enterprise.Place;
import cn.edu.cess.mapper.content.enterprise.PlaceMapper;
import cn.edu.cess.service.content.enterprise.IEnterprisePlaceService;
import cn.edu.cess.service.content.enterprise.IPlaceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class PlaceServiceImpl extends ServiceImpl<PlaceMapper, Place> implements IPlaceService {

    @Autowired
    IEnterprisePlaceService iEnterprisePlaceService;

    @Override
    public void fillData(String talkDate, List<Place> placeList) {
        List<EnterprisePlace> enterprisePlaceList = iEnterprisePlaceService.getByTalkDate(talkDate);
        for (EnterprisePlace enterprisePlace : enterprisePlaceList) {
            //已过期
            if (enterprisePlace.getExpire()) {
                continue;
            }
            for (Place place : placeList) {
                //地点,日期相同，则填充地点当天被占用的情况
                if (place.getId() == enterprisePlace.getPid()) {
                    Integer talkTimeId = enterprisePlace.getTalkTimeId();
                    switch (talkTimeId) {
                        case 1:
                            place.setTime1(true);
                            break;
                        case 2:
                            place.setTime2(true);
                            break;
                        case 3:
                            place.setTime3(true);
                            break;
                        case 4:
                            place.setTime4(true);
                            break;
                        case 5:
                            place.setTime5(true);
                            break;
                    }
                }
            }
        }
    }

    @Override
    public Place getPlaceByEid(Integer id) {
        EnterprisePlace enterprisePlace = iEnterprisePlaceService.getByEid(id);
        if (enterprisePlace == null) {
            return null;
        }
        return getById(enterprisePlace.getPid());
    }
}
