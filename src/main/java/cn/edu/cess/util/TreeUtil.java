package cn.edu.cess.util;

import cn.edu.cess.entity.AdminMenu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author Gnosed Lu
 * @Date 2020/3/28
 * @Description
 */
public class TreeUtil {

    private static List<Integer> menusIds = new ArrayList<>();

    /**
     * 首次遍历所有菜单项，根据其parentId再次遍历菜单项，找到id值与该parentId相等的父菜单，
     * 将子菜单放入父菜单，注意不能删除子菜单，因此改子菜单可能是其它菜单的父菜单。
     * 再次遍历所有菜单项，清除树的所有子节点（子菜单），只留下根节点（父菜单）。
     *
     * @param menus
     */
    public static void treeMenus(List<AdminMenu> menus) {
        for (AdminMenu subMenu : menus) {
            for (AdminMenu superMenu : menus) {
                if (superMenu.getId() == subMenu.getParentId()) {
                    List<AdminMenu> children = superMenu.getChildren();
                    if (children == null) {
                        children = new ArrayList<>();
                    }
                    children.add(subMenu);
                    superMenu.setChildren(children);
                    break;
                }
            }
        }
        //注意ConcurrentModificationException
        Iterator<AdminMenu> it = menus.iterator();
        while (it.hasNext()) {
            AdminMenu menu = it.next();
            if (menu.getParentId() > 0) {
                it.remove();
            }
        }
    }

    /**
     * 递归遍历，返回多级菜单中所有父子菜单的id
     *
     * @param menus
     * @return
     */
    public static List<Integer> recurMenusForIds(List<AdminMenu> menus) {
        for (AdminMenu menu : menus) {
            menusIds.add(menu.getId());
            if (menu.getChildren() == null) {
                continue;
            }
            recurMenusForIds(menu.getChildren());
        }
        return menusIds;
    }
}
