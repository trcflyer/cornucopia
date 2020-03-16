package com.easypay.cornucopiacommon;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class BaseRequestHuifu {

    @Autowired
    private DefaultRequestValue defaultRequestValue;

    public Map<String, String> dataMap = new HashMap<>();
    ;

    public void initMap() {
        dataMap.put("uuid", UUID.randomUUID().toString().replaceAll("-", ""));
        dataMap.put("infVersion", "1.0.0");
        dataMap.put("longitude", "121.553478");
        dataMap.put("latitude", "29.165789");
        dataMap.put("mobType", "00");
        dataMap.put("mobId", "111111");
        dataMap.put("oemId", defaultRequestValue.getOemId());
        dataMap.put("bagentId", defaultRequestValue.getBagentId());
        dataMap.put("agentId", defaultRequestValue.getAgentId());
    }
}
