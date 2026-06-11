package com.mgkj.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.entity.Messagepz;
import com.mgkj.mapper.MessagepzMapper;
import com.mgkj.service.MessagepzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author 14501
* @description 针对表【messagePz】的数据库操作Service实现
* @createDate 2024-09-06 09:03:07
*/
@Service
@Slf4j
public class MessagepzServiceImpl extends ServiceImpl<MessagepzMapper, Messagepz>
    implements MessagepzService {
//
//    @Resource
//    private MessagepzMapper messagepzMapper;
//
//    @Resource
//    private MessageconfigurationMapper messageconfigurationMapper;
//
//    @Resource
//    private CmsmvMapper cmsmvMapper;
//
//    @Resource
//    private ZnxMapper znxMapper;
//
//    @Override
//    @Transactional
//    public Result saveMessage(List<Messagepz> messagepzList) {
//        try{
//            if (!messagepzList.isEmpty()){
//               messagepzList.forEach(arr -> {
//                   this.messagepzMapper.insert(arr);
//               });
//               return Result.success("新增成功");
//            }
//            return Result.error("请输入正确的参数");
//        }catch (Exception e){
//            throw new YiFeiException(500, "消息配置新增发生异常"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result queryIPage(Integer current, Integer size) {
//        try{
//            Map<String,Object> map = new HashMap<>();
//            PageHelper.startPage(current,size);
//            List<Messagepz> messagepzList = this.messagepzMapper.selectList(null);
//            PageInfo<Messagepz> messagepzPageInfo = new PageInfo<>(messagepzList);
//            map.put("pages", messagepzPageInfo.getPages());
//            map.put("total", messagepzPageInfo.getTotal());
//            map.put("list", messagepzPageInfo.getList());
//            return Result.success(map);
//        }catch (Exception e){
//            throw new YiFeiException(500,"消息配置分页发生异常"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result deleteMessage(List<Integer> uuidList) {
//        try{
//            boolean b = this.removeByIds(uuidList);
//            if (b) {
//                return Result.success();
//            }
//            return Result.error("删除失败");
//        }catch (Exception e){
//            throw new YiFeiException(500,"删除消息配置发生异常"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result<Messagepz> queryOne(Integer uuid) {
//        try{
//            Messagepz messagepz = this.messagepzMapper.selectById(uuid);
//            return Result.success(messagepz);
//        }catch (Exception e){
//            throw new YiFeiException(500,"查询详情信息发生异常"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result updateMessage(Messagepz messagepz) {
//        try{
//            int update = this.messagepzMapper.updateById(messagepz);
//            if (update>0){
//                return Result.success();
//            }
//            return Result.error("修改失败");
//        }catch (Exception e){
//            throw new YiFeiException(500,"修改发生异常"+e.getMessage());
//        }
//    }
//
//    @Override
//    public Result PushMessage(String url,String resultString,String reqParam) {
//        try{
////            Map<String, String> jsonMap = com.alibaba.fastjson2.JSON.parseObject(reqParam, new com.alibaba.fastjson2.TypeReference<HashMap<String, String>>() {
////            });
////            log.info("12345678"+jsonMap);
//            Messagepz messagepz = this.messagepzMapper.selectOne(new LambdaQueryWrapper<Messagepz>().eq(Messagepz::getPath, url));
//            if (messagepz!=null){
//                Messageconfiguration messageconfiguration = this.messageconfigurationMapper.selectById(messagepz.getUid());
//                if (messageconfiguration!=null){
//                    if (StringUtils.isNotEmpty(messagepz.getPersonnel())){
//                        String tc001 = reqParam.split("tc001=")[1].split(",")[0];
//                        String tc002 = reqParam.split("tc002=")[1].split(",")[0];
//                        String udf12 = reqParam.split("udf11=")[1].split(",")[0];
//                        if (messagepz.getPersonnel().contains(",")){
//                            String[] split = messagepz.getPersonnel().split(",");
//                            for (String arr:split) {
//                                Znx znx=Znx.builder().code(tc001+":"+tc002).status(0).content(udf12+messageconfiguration.getContent())
//                                        .notificationCode(arr).processId(null).title("通知").creationTime(DateUtil.format(new Date(), "yyyy-MM-dd")).build();
//                                this.znxMapper.insert(znx);
//                            }
//                        }else {
//                            Znx znx=Znx.builder().code(tc001+":"+tc002).status(0).content(udf12+messageconfiguration.getContent())
//                                    .notificationCode(messagepz.getPersonnel()).processId(null).title("通知").creationTime(DateUtil.format(new Date(), "yyyy-MM-dd")).build();
//                            this.znxMapper.insert(znx);
//                        }
//                    }
//                }
//            }
//        }catch (Exception e){
//            throw new YiFeiException(500,"消息通知发生异常"+e.getMessage());
//        }
//        return null;
//    }
}




