package com.mgkj.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mgkj.common.result.Result;
import com.mgkj.exception.YiFeiException;
import com.mgkj.dto.IHeadDTO;
import com.mgkj.dto.IHeadPageDTO;
import com.mgkj.dto.Pojo;
import com.mgkj.entity.IBasics;
import com.mgkj.entity.IBody;
import com.mgkj.entity.IHead;
import com.mgkj.entity.PageResult;
import com.mgkj.mapper.IBasicsMapper;
import com.mgkj.mapper.IBodyMapper;
import com.mgkj.mapper.IHeadMapper;
import com.mgkj.mapper.SFCTEMapper;
import com.mgkj.service.IHeadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class IHeadServiceImpl implements IHeadService {
    @Resource
    private IHeadMapper iHeadMapper;
    @Resource
    private IBodyMapper iBodyMapper;
    @Resource
    private IBasicsMapper iBasicsMapper;

    @Resource
    private SFCTEMapper sfcteMapper;


    @Override
    @Transactional
    public void add(IHeadDTO iHeadDTO) {
        try{

            Integer del = iHeadMapper.del(iHeadDTO.getIHead().getUid());
            if (del>0){
                List<IBody> iBodyList = iBodyMapper.getAllByI_uid(iHeadDTO.getIHead().getUid());
                for (IBody iBody : iBodyList) {
                    Integer i_uid = iBody.getUid();
                    iBasicsMapper.del(i_uid);
                }
                Integer del1 = iBodyMapper.del(iHeadDTO.getIHead().getUid());
            }

            IHead iHead = iHeadDTO.getIHead();

            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
            iHead.setStartTime(dateFormat.format(new Date()));
            //IHead iHead = iHeadDTO.getIhead();

            List<IBody> iBodyList = iHeadDTO.getIBodyList();
            //List<IBody> iBodyList = iHeadDTO.getIbodylist();
            List<IBasics> iBasicsList = iHeadDTO.getIBasicsList();
            //List<IBasics> iBasicsList = iHeadDTO.getIbasicslist();

//            System.out.println(iHead);
//            System.out.println(iBodyList.toString());
//            System.out.println(iBasicsList.toString());

//        iHead.setCreatTime(TimeUtils.getNowTime().substring(0,8));
            iHeadMapper.add(iHead);
            Integer uid = iHead.getUid();
            System.out.println("uid: " + uid);
            //List<Integer> i_uidList = new ArrayList<>();
            for (IBody iBody : iBodyList) {
                iBody.setI_uid(uid);
                iBodyMapper.add(iBody);
                Integer i_uid = iBody.getUid();
                iBody.setUid(i_uid);
                //i_uidList.add(i_uid);
            }

            for (int i = 0; i < iBasicsList.size(); i++) {
                IBasics iBasics = iBasicsList.get(i);
                Integer i_uid = 0;
                for (IBody iBody : iBodyList) {
                    System.out.println("iBody mg003  " + iBody.getMg003());
                    System.out.println("iBasics mg003  " + iBasics.getMg003());
                    if (iBody.getMg003().trim().equals( iBasics.getMg003().trim())){
                        System.out.println("****");
                        i_uid = iBody.getUid();
                        break;
                    }else System.out.println("-------");
                }

                iBasics.setI_uid(i_uid);
                System.out.println("++++++" + iBasics.getValues().size());
                for (int i1 = 0; i1 < iBasics.getValues().size(); i1++) {
                    iBasics.setNumOn(i1+1);
                    iBasics.setValue(iBasics.getValues().get(i1));
                    //iBasics.setI_uid(i_uidList.get(i));
                    iBasicsMapper.add(iBasics);
                }


            }

//        MoctyStatus moctyStatus = new MoctyStatus();
//        moctyStatus.setPid(iHeadDTO.getCode());
//        this.moctyStatusMapper.insert(moctyStatus);
            this.sfcteMapper.updateYourEntity(iHeadDTO.getCode());
        }catch (Exception e){
            throw new YiFeiException(500,"新增失败,发生异常"+e.getMessage());
        }
    }

    @Override
    public PageResult page(IHeadPageDTO iHeadPageDTO) {
        PageHelper.startPage(iHeadPageDTO.getPage(),iHeadPageDTO.getPagesize());
        Page<List<IHead>> iHeadList = (Page)iHeadMapper.page(iHeadPageDTO);
        long total = iHeadList.getTotal();
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(iHeadList.getResult());
        return pageResult;
    }

    @Override
    public IHeadDTO getAll(Integer uid) {
        IHead iHead = iHeadMapper.getByUid(uid);
        List<IBody> iBodyList = iBodyMapper.getAllByI_uid(uid);
        System.out.println("ibodylist :" + iBodyList.size());
        List<IBasics> iBasicsList = new ArrayList<>();
        for (int i = 0; i < iBodyList.size(); i++) {
            System.out.println("i " + i);
            Integer i_uid = iBodyList.get(i).getUid();
            List<IBasics> iBasicsList1 = iBasicsMapper.getByi_uid(i_uid);
            for (IBasics basics : iBasicsList1) {
                iBasicsList.add(basics);
            }

        }
        IHeadDTO iHeadDTO = new IHeadDTO();
        iHeadDTO.setIHead(iHead);
        iHeadDTO.setIBodyList(iBodyList);
        iHeadDTO.setIBasicsList(iBasicsList);
        /*iHeadDTO.setIhead(iHead);
        iHeadDTO.setIbodylist(iBodyList);
        iHeadDTO.setIbasicslist(iBasicsList);*/
        return iHeadDTO;
    }

    @Override
    public List<IHeadDTO> getAlllList(List<Integer> ids) {
        //List<IHeadDTO> iHeadDTOS = iHeadMapper.getAllList(ids);
        List<IHeadDTO> iHeadDTOS = new ArrayList<>();
        for (int j = 0; j < ids.size(); j++) {
            Integer uid = ids.get(j);
            IHead iHead = iHeadMapper.getByUid(uid);
            List<IBody> iBodyList = iBodyMapper.getAllByI_uid(uid);
            for (IBody iBody : iBodyList) {
                iBody.setI_uid(uid);
            }
            System.out.println(iBodyList.toString());
            List<IBasics> iBasicsList = new ArrayList<>();
            for (int i = 0; i < iBodyList.size(); i++) {
                Integer i_uid = iBodyList.get(i).getUid();
                List<IBasics> iBasicsList1 = iBasicsMapper.getByi_uid(i_uid);
                for (IBasics basics : iBasicsList1) {
                    basics.setI_uid(i_uid);
                    iBasicsList.add(basics);
                }

            }

            IHeadDTO iHeadDTO = new IHeadDTO();
            iHeadDTO.setIHead(iHead);
            iHeadDTO.setIBodyList(iBodyList);
            iHeadDTO.setIBasicsList(iBasicsList);

            iHeadDTOS.add(iHeadDTO);
        }

        return iHeadDTOS;
    }

    @Override
    public IHeadDTO allId(Integer uid) {
        IHead iHead = iHeadMapper.getByUid(uid);
        List<IBody> iBodyList = iBodyMapper.getAllByI_uids(uid);
        System.out.println("ibodylist :" + iBodyList.size());
        List<IBasics> iBasicsList = new ArrayList<>();
        for (int i = 0; i < iBodyList.size(); i++) {
            System.out.println("i " + i);
            Integer i_uid = iBodyList.get(i).getUid();
            List<IBasics> iBasicsList1 = iBasicsMapper.getByi_uid(i_uid);
            for (IBasics basics : iBasicsList1) {
                iBasicsList.add(basics);
            }
        }
        IHeadDTO iHeadDTO = new IHeadDTO();
        iHeadDTO.setIHead(iHead);
        iHeadDTO.setIBodyList(iBodyList);
        iHeadDTO.setIBasicsList(iBasicsList);
        /*iHeadDTO.setIhead(iHead);
        iHeadDTO.setIbodylist(iBodyList);
        iHeadDTO.setIbasicslist(iBasicsList);*/
        return iHeadDTO;
    }

    @Override
    public List<IHeadDTO> allIds(Pojo pojo) {
        List<IHeadDTO> list = new ArrayList<>();
        List<IHead> byUidS = iHeadMapper.getByUidS(pojo.getArticle(),pojo.getStartTime(),pojo.getEndTime());
        if (!byUidS.isEmpty()){
            byUidS.stream().forEach(arr -> {
                List<IBody> iBodyList = iBodyMapper.getAllByI_uids(arr.getUid());
                List<IBasics> iBasicsList = new ArrayList<>();
//                for (int i = 0; i < iBodyList.size(); i++) {
//                    System.out.println("i " + i);
//                    Integer i_uid = iBodyList.get(i).getUid();
//                    List<IBasics> iBasicsList1 = iBasicsMapper.getByi_uid(i_uid);
//                    for (IBasics basics : iBasicsList1) {
//                        iBasicsList.add(basics);
//                    }
//                }
                iBodyList.stream().forEach(arrs -> {
                    List<IBasics> iBasicsList1 = iBasicsMapper.getByi_uid(arrs.getI_uid());
                    iBasicsList.addAll(iBasicsList1);
                });
                IHeadDTO iHeadDTO = new IHeadDTO();
                iHeadDTO.setIHead(arr);
                iHeadDTO.setIBodyList(iBodyList);
                iHeadDTO.setIBasicsList(iBasicsList);
                list.add(iHeadDTO);
            });
        }
        return list;
    }

    @Override
    @Transactional
    public Result del(Integer id) {
       try{
           IHead iHead = iHeadMapper.getByUid(id);
           this.sfcteMapper.updateBgs(iHead.getTa001()+'-'+iHead.getTa002()+'-'+iHead.getTa004(),"待检验");
           Integer del = iHeadMapper.del(id);
           if (del>0){
               List<IBody> iBodyList = iBodyMapper.getAllByI_uid(id);
               for (IBody iBody : iBodyList) {
                   Integer i_uid = iBody.getUid();
                   iBasicsMapper.del(i_uid);
               }
               Integer del1 = iBodyMapper.del(id);
               if (del1>0){
                   return Result.ok("删除成功");
               }
           }
           return Result.fail("删除失败");
       }catch (Exception e){
           throw new YiFeiException(500,"删除发生异常"+e.getMessage());
       }
    }
}
