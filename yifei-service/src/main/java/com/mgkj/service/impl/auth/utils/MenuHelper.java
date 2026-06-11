package com.mgkj.service.impl.auth.utils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mgkj.entity.auth.SysMenu;

import java.util.ArrayList;
import java.util.List;

@DS("mes")
public class MenuHelper {
    //使用递归方法建菜单
    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        //创建list集合，用于最终数据
        List<SysMenu> trees = new ArrayList<>();
        //把所有菜单数据进行遍历
        for(SysMenu sysMenu:sysMenuList) {
            //递归入口进入
            //parentId=0是入口
            if(sysMenu.getParentId().longValue()==0) {
                trees.add(getChildren(sysMenu,sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildren(SysMenu sysMenu,
                                      List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断 id 和 parentId对应关系
        for(SysMenu it: sysMenuList) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue()) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildren(it,sysMenuList));
            }
        }
        return sysMenu;
    }

    //使用递归方法建菜单
//    public static List<SysMenu> buildTreeByIsSelect(List<SysMenu> sysMenuList) {
//        //创建list集合，用于最终数据
//        List<SysMenu> trees = new ArrayList<>();
//        //把所有菜单数据进行遍历
//        for(SysMenu sysMenu:sysMenuList) {
//            //递归入口进入
//            //parentId=0是入口
//            if(sysMenu.getParentId().longValue()==0 && sysMenu.isSelect() == true) {
//                trees.add(getChildrenByIsSelect(sysMenu,sysMenuList));
//            }
//        }
//        return trees;
//    }

    public static List<SysMenu> buildTreeByIsSelect(List<SysMenu> sysMenuList, Long targetRootId) {
        List<SysMenu> trees = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            // 改成根据传入的 targetRootId 来找根节点
            if (sysMenu.getId().equals(targetRootId) && sysMenu.isSelect()) {
                trees.add(getChildrenByIsSelect(sysMenu, sysMenuList));
            }
        }
        return trees;
    }

    public static SysMenu getChildrenByIsSelect(SysMenu sysMenu,
                                      List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        //遍历所有菜单数据，判断 id 和 parentId对应关系
        for(SysMenu it: sysMenuList) {
            if(sysMenu.getId().longValue() == it.getParentId().longValue() && it.isSelect() == true) {
                if (sysMenu.getChildren() == null) {
                    sysMenu.setChildren(new ArrayList<>());
                }
                sysMenu.getChildren().add(getChildrenByIsSelect(it,sysMenuList));
            }
        }
        return sysMenu;
    }
}
