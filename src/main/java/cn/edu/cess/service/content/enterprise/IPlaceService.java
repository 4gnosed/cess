package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.Place;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-03
 */
public interface IPlaceService extends IService<Place> {

    void fillData(String talkDate, List<Place> placeList);

    Place getPlaceByEid(Integer id);
}
