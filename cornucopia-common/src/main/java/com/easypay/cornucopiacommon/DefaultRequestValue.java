package com.easypay.cornucopiacommon;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 请求汇付服务默认代理
 */

@Data
@Slf4j
@Service
public class DefaultRequestValue {

    @Value("${baseUrl}")
    String baseUrl;

}
