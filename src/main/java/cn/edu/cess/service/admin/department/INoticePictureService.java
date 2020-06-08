package cn.edu.cess.service.admin.department;

import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.admin.department.NoticePicture;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-06-03
 */
public interface INoticePictureService extends IService<NoticePicture> {

    NoticePicture save(FileUrlVo fileUrlVo);
}
