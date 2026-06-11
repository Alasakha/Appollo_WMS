package com.mgkj.dto;

import com.mgkj.entity.IBasics;
import com.mgkj.entity.IBody;
import com.mgkj.entity.IHead;
import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: LiangPanDeng
 * @Date: 2023/11/28/22:37
 * @Description:
 */
@Data
public class IHeadDTO {
    private IHead iHead;
    private List<IBody> iBodyList;
    private List<IBasics> iBasicsList;
    private String code;
    /*private IHead ihead;
    private List<IBody> ibodylist;
    private List<IBasics> ibasicslist;*/
}
