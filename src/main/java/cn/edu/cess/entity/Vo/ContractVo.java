package cn.edu.cess.entity.Vo;

import cn.edu.cess.entity.content.enterprise.Enterprise;
import cn.edu.cess.entity.content.enterprise.Positions;
import cn.edu.cess.entity.content.enterprise.SheetContract;
import cn.edu.cess.entity.content.student.Student;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/5/10
 * @Description
 */
@Data
public class ContractVo {
    private SheetContract sheetContract;
    private Positions positions;
    private Student student;
    private Enterprise enterprise;
}
