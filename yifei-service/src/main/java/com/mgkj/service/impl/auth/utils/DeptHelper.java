package com.mgkj.service.impl.auth.utils;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.mgkj.entity.auth.SysDept;

import java.util.ArrayList;
import java.util.List;

@DS("mes")
public class DeptHelper {
    //使用递归方法建菜单
    public static List<SysDept> buildTree(List<SysDept> SysDeptList) {
        //创建list集合，用于最终数据
        List<SysDept> trees = new ArrayList<>();
        //把所有菜单数据进行遍历
        for(SysDept SysDept:SysDeptList) {
            //递归入口进入
            //parentId=0是入口
            if(SysDept.getParentId().longValue()==0) {
                trees.add(getChildren(SysDept,SysDeptList));
            }
        }
        return trees;
    }

    public static SysDept getChildren(SysDept SysDept,
                                      List<SysDept> SysDeptList) {
        SysDept.setChildren(new ArrayList<SysDept>());
        //遍历所有菜单数据，判断 id 和 parentId对应关系
        for(SysDept it: SysDeptList) {
            if(SysDept.getId().longValue() == it.getParentId().longValue()) {
                if (SysDept.getChildren() == null) {
                    SysDept.setChildren(new ArrayList<>());
                }
                SysDept.getChildren().add(getChildren(it,SysDeptList));
            }
        }
        return SysDept;
    }
}
