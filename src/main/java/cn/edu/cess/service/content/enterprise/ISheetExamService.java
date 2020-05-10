package cn.edu.cess.service.content.enterprise;

import cn.edu.cess.entity.content.enterprise.SheetExam;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-09
 */
public interface ISheetExamService extends IService<SheetExam> {

    SheetExam getByRidPid(Integer rid, Integer pid);

    SheetExam add(Integer rid, Integer pid, SheetExam sheetExam);

    boolean update(SheetExam sheetExam);
}
