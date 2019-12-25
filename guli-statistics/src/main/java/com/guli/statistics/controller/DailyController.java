package com.guli.statistics.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.common.R;
import com.guli.statistics.client.UcenterClient;
import com.guli.statistics.entity.Daily;
import com.guli.statistics.service.DailyService;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-21
 */
@RestController
@CrossOrigin
@RequestMapping("/statistics/daily")
public class DailyController {



    @Autowired
    DailyService dailyService;

    @GetMapping("/getRegisterCount/{day}")
    public R getRegisterCount(@PathVariable("day") String day){
        Integer count = dailyService.getRegisterCount(day);
        return R.ok().data("register",count);
    }

    @GetMapping("/creatStatisticsByDay/{day}")
    public R creatStatisticsByDay(@PathVariable("day") String day){

        //先删除当天统计记录，再添加
        QueryWrapper<Daily> dailyQueryWrapper = new QueryWrapper<>();
        dailyQueryWrapper.eq("date_calculated", day);
        dailyService.remove(dailyQueryWrapper);

        Integer registerCount = dailyService.getRegisterCount(day);
        Integer loginNum = RandomUtils.nextInt(100, 200);//TODO
        Integer videoViewNum = RandomUtils.nextInt(100, 200);//TODO
        Integer courseNum = RandomUtils.nextInt(100, 200);//TODO

        Daily daily = new Daily();
        daily.setRegisterNum(registerCount);
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setDateCalculated(day);

        dailyService.save(daily);

        return R.ok();
    }


    /**
     * 根据传入的3个参数，查出统计结果
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/getCountDay/{type}/{begin}/{end}")
    public R getCountDay(@PathVariable String type,
                         @PathVariable String begin,
                         @PathVariable String end) {

        Map<String,Object> map = dailyService.getCountDay(type,begin,end);
        return R.ok().data("dayMap",map);
    }

}

