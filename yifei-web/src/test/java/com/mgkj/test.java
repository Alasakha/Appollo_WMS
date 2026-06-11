package com.mgkj;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mgkj.common.result.Result;
import com.mgkj.common.utils.MD5;
import com.mgkj.dto.MessageDto;
import com.mgkj.dto.PurchaseArrivalStorageWaitCheckDto;
import com.mgkj.entity.BmBarcodeDetail;
import com.mgkj.entity.InvBarcodeOperation;
import com.mgkj.entity.InvBarcodeTransaction;
import com.mgkj.entity.MOCTA;
import com.mgkj.mapper.MOCTAMapper;
import com.mgkj.mapper.PurchaseMapper;
import com.mgkj.service.*;
import com.mgkj.vo.PurchaseArrivalCheckHeader;
import com.mgkj.vo.PurchaseArrivalDVo;
import com.mgkj.vo.PurchaseReceiptStorageVo;
import com.mgkj.vo.WaitToInspectedVo;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author : ssy
 * @date: 2023-12-18
 * @Description:
 */
@SpringBootTest
public class test {

    @Resource
    private BmBarcodeDetailService bmBarcodeDetailService;

    @Test
    public void test() {
        List<BmBarcodeDetail> barCodeDetailList = new ArrayList<>();
        BmBarcodeDetail bmBarcodeDetail = new BmBarcodeDetail();
        bmBarcodeDetail.setBarcode("test-20260101");
        barCodeDetailList.add(bmBarcodeDetail);
        BmBarcodeDetail bmBarcodeDetail1 = new BmBarcodeDetail();
        bmBarcodeDetail1.setBarcode("test-20260102");
        barCodeDetailList.add(bmBarcodeDetail1);
        bmBarcodeDetailService.saveBatch(barCodeDetailList);
    }

}
