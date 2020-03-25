package cn.edu.cess.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-03-23
 */
@Data
@TableName("admin_menu")
public class AdminMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private int id;
    /**
     * 与 Vue 路由中的 path 对应，即地址路径
     */
    private String path;

    /**
     * 与 Vue 路由中的 name 属性对应
     */
    private String name;

    /**
     * 中文名称，用于渲染导航栏（菜单）界面
     */
    private String nameZh;

    /**
     * element 图标类名，用于渲染菜单名称前的小图标
     */
    private String iconCls;

    /**
     * 组件名，用于解析路由对应的组件
     */
    private String component;

    /**
     * 父节点 id，用于存储导航栏层级关系
     */
    private Integer parentId;

    @TableField(exist = false)
    private List<AdminMenu> children;

}
