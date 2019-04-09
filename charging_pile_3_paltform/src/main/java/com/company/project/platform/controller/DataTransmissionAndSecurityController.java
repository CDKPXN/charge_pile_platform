package com.company.project.platform.controller;

import com.alibaba.fastjson.JSON;
import com.company.project.platform.common.GenPlatformResult;
import com.company.project.platform.common.PlatformRequestBody;
import com.company.project.platform.common.PlatformResult;
import com.company.project.platform.model.PlatformTokenModel;
import com.company.project.platform.util.PlatformToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author lides
 * @Description
 * @Date 18-9-15 15:09
 **/

@RestController
@RequestMapping("/evcs/20160701")
@Slf4j
public class DataTransmissionAndSecurityController {

    @GetMapping
    public String query_token() {
        String token = PlatformToken.getToken();
        return token;
    }

    @PostMapping("/query_token")
    public PlatformResult query_token(@RequestBody PlatformRequestBody platformRequestBody) throws Exception {
        //生成token，检验token也没做

        return GenPlatformResult.genSuccessResult("");
    }


}
