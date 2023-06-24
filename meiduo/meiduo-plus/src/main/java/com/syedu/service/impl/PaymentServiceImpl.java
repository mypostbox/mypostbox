package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Payment;
import com.syedu.service.PaymentService;
import com.syedu.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tb_payment】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService{

}




