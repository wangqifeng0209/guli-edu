package com.guli.statistics.client;

import com.guli.edu.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Jason
 * @create 2019-12-21-20:39
 */

@Component
@FeignClient("guli-ucenter")
public interface UcenterClient {

    @GetMapping("/ucenter/member/getRegisterCount/{day}")
    R getRegisterCount(@PathVariable("day") String day);

}
