package cn.edu.cess.entity.content.enterprise;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sheet_offer")
public class SheetOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门
     */
    @TableField("department")
    private String department;

    /**
     * 职位id
     */
    @TableField("positions_id")
    private Integer positionsId;

    /**
     * 新的职位id
     */
    @TableField("new_positions_id")
    private Integer newPositionsId;

    /**
     * 试用期，以月为单位
     */
    @TableField("probation")
    private Integer probation;

    /**
     * 试用期月薪，以元为单位
     */
    @TableField("probation_month_salary")
    private Integer probationMonthSalary;

    /**
     * 转正后月薪，以元为单位
     */
    @TableField("regular_month_salary")
    private Integer regularMonthSalary;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


}
