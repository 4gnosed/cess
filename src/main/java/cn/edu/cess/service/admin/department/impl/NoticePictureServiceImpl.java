package cn.edu.cess.service.admin.department.impl;

import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.admin.department.NoticePicture;
import cn.edu.cess.mapper.admin.department.NoticePictureMapper;
import cn.edu.cess.service.admin.department.INoticePictureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-06-03
 */
@Service
public class NoticePictureServiceImpl extends ServiceImpl<NoticePictureMapper, NoticePicture> implements INoticePictureService {

    @Override
    public NoticePicture save(FileUrlVo fileUrlVo) {
        NoticePicture noticePicture = new NoticePicture();
        noticePicture.setPname(fileUrlVo.getPath());
        save(noticePicture);
        return noticePicture;
    }
}
