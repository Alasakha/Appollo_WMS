package com.mgkj.service.impl;

import com.mgkj.dto.SelectDto;
import com.mgkj.entity.Copma;
import com.mgkj.mapper.CopmaMapper;
import com.mgkj.service.CopmaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mgkj.vo.CustomVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ssy
 * @since 2024-01-08
 */
@Service
public class CopmaServiceImpl extends ServiceImpl<CopmaMapper, Copma> implements CopmaService {

    @Resource
    private CopmaMapper copmaMapper;

    @Override
    public CustomVo getDetailedCustomerInfo(String ma001) {
        CustomVo customVo = copmaMapper.getDetailedCustomerInfo(ma001);
//        customVo.setMA076(customVo.getMR003().trim()+"("+customVo.getMA076().trim()+")");
//        customVo.setMA017(customVo.getMR003().trim()+"("+customVo.getMA017().trim()+")");
//        customVo.setMA018(customVo.getMR003().trim()+"("+customVo.getMA018().trim()+")");
//        customVo.setMA016(customVo.getMV002().trim()+"("+customVo.getMA016().trim()+")");
//        customVo.setMA085(customVo.getMV002().trim()+"("+customVo.getMA085().trim()+")");
//        customVo.setMA014(customVo.getMF002().trim()+"("+customVo.getMA014().trim()+")");
        if (customVo != null) {
            if (customVo.getMR003() != null) {
                String mr003 = customVo.getMR003().trim();
                if (customVo.getMA076() != null) {
                    customVo.setMA076(mr003 + "(" + customVo.getMA076().trim() + ")");
                }
                if (customVo.getMA017() != null) {
                    customVo.setMA017(mr003 + "(" + customVo.getMA017().trim() + ")");
                }
                if (customVo.getMA018() != null) {
                    customVo.setMA018(mr003 + "(" + customVo.getMA018().trim() + ")");
                }
            }
            if (customVo.getMV002() != null) {
                String mv002 = customVo.getMV002().trim();
                if (customVo.getMA016() != null) {
                    customVo.setMA016(mv002 + "(" + customVo.getMA016().trim() + ")");
                }
                if (customVo.getMA085() != null) {
                    customVo.setMA085(mv002 + "(" + customVo.getMA085().trim() + ")");
                }
            }
            if (customVo.getMF002() != null) {
                String mf002 = customVo.getMF002().trim();
                if (customVo.getMA014() != null) {
                    customVo.setMA014(mf002 + "(" + customVo.getMA014().trim() + ")");
                }
            }
            if (customVo.getNJ002() != null && customVo.getMA041() != null) {
                String nj002 = customVo.getNJ002().trim();
                customVo.setMA041(nj002 + "(" + customVo.getMA041().trim() + ")");
            }
            if (customVo.getNA003() != null && customVo.getMA031() != null) {
                String na003 = customVo.getNA003().trim();
                customVo.setMA031(na003 + "(" + customVo.getMA031().trim() + ")");
            }
            if (customVo.getIA002() != null && customVo.getMA115() != null) {
                String ia002 = customVo.getIA002().trim();
                customVo.setMA115(ia002 + "(" + customVo.getMA115().trim() + ")");
            }

            DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
            DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            // 初次交易日 MA021
            if (customVo.getMA021() != null && !customVo.getMA021().trim().isEmpty()) {
                String firstDate = customVo.getMA021();
                try {
                    Date date = inputFormat.parse(firstDate);
                    customVo.setMA021(outputFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            // 最近交易日 MA022
            if (customVo.getMA022() != null && !customVo.getMA022().trim().isEmpty()) {
                String endDate = customVo.getMA022();
                try {
                    Date date = inputFormat.parse(endDate);
                    customVo.setMA022(outputFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        if (customVo.getMA037().trim().equals("A")){
            customVo.setMA037("A.专用发票");
        }else if (customVo.getMA037().trim().equals("B")){
            customVo.setMA031("B.普通发票");
        }else if(customVo.getMA037().trim().equals("C")){
            customVo.setMA031("C.其他");
        }
        if (!customVo.getMA043().isEmpty()){
            customVo.setMA043("每月"+customVo.getMA043().trim()+"日");
        }
        //1.应税内含,2.应税外加,3.零税率,4.免税,9.不计税
        switch (customVo.getMA038()){
            case "1":
                customVo.setMA038("1.应税内含");
                break;
            case "2":
                customVo.setMA038("2.应税外加");
                break;
            case "3":
                customVo.setMA038("3.零税率");
                break;
            case "4":
                customVo.setMA038("4.免税");
                break;
            case "9":
                customVo.setMA038("9.不计税");
                break;
        }
//        customVo.setMA041(customVo.getNJ002().trim()+"("+customVo.getMA041().trim()+")");
//        customVo.setMA031(customVo.getNA003().trim()+"("+customVo.getMA031().trim()+")");
//        customVo.setMA115(customVo.getIA002().trim()+"("+customVo.getMA115().trim()+")");
//
//        DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
//        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
//        //TODO 初次交易日	MA021								显示：2024-01-08
//        String firstDate = customVo.getMA021();
//        try {
//            Date date = inputFormat.parse(firstDate);
//            customVo.setMA021(outputFormat.format(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        //TODO 最近交易日	MA022								显示：2024-01-08
//        String endDate = customVo.getMA022();
//        try {
//            Date date = inputFormat.parse(endDate);
//            customVo.setMA022(outputFormat.format(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        customVo.setMA029("N.信用额度不控制");
        //订单审核 1.不检查 2.警告 3.拒绝[DEF:"1"]
        switch (customVo.getMA088()){
            case "1":
                customVo.setMA088("订单审核:信用超额时不检查");
                break;
            case "2":
                customVo.setMA088("订单审核:信用超额时警告");
                break;
            case "3":
                customVo.setMA088("订单审核:信用超额时拒绝");
                break;
        }
        //销货审核 1.不检查 2.警告 3.拒绝[DEF:"1"]
        switch (customVo.getMA089()){
            case "1":
                customVo.setMA089("销货单审核:信用超额时不检查");
                break;
            case "2":
                customVo.setMA089("销货单审核:信用超额时警告");
                break;
            case "3":
                customVo.setMA089("销货单审核:信用超额时拒绝");
                break;
        }
        return customVo;
    }

    @Override
    public List<CustomVo> getCustomerInfoByTerm(SelectDto selectDto) {
        List<CustomVo> list = copmaMapper.getCustomerInfoByTerm(selectDto);
        ArrayList<CustomVo> customVos = new ArrayList<>();
        for (CustomVo customVo : list) {
            if (customVo.getMV002()==null){
                customVo.setMA016(customVo.getMA016().trim());
            }else {
                customVo.setMA016(customVo.getMV002().trim()+"("+customVo.getMA016().trim()+")");
            }
            //1:已核准、2:尚待核准、3:不准交易[DEF:1]
            switch (customVo.getMA097()){
                case "1":
                    customVo.setMA097("已核准");
                    break;
                case "2":
                    customVo.setMA097("尚待核准");
                    break;
                case "3":
                    customVo.setMA097("不准交易");
                    break;
            }
            customVos.add(customVo);
        }
        return list;
    }
}
