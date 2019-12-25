package com.guli.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.edu.common.R;
import com.guli.statistics.client.UcenterClient;
import com.guli.statistics.entity.Daily;
import com.guli.statistics.mapper.DailyMapper;
import com.guli.statistics.service.DailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author jason
 * @since 2019-12-21
 */
@Service
public class DailyServiceImpl extends ServiceImpl<DailyMapper, Daily> implements DailyService {

    @Autowired
    UcenterClient ucenterClient;
    @Override
    public Integer getRegisterCount(String day) {
        R r = ucenterClient.getRegisterCount(day);
        Integer registerCount = (Integer)r.getData().get("register");
        return registerCount;
    }

    @Override
    public Map<String, Object> getCountDay(String type, String begin, String end) {

        QueryWrapper<Daily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated",type);

        List<Daily> list = baseMapper.selectList(wrapper);

        Map<String, Object> map = new HashMap<>();
        List<String> dates = new ArrayList<>();
        List<String> counts = new ArrayList<>();

        for (Daily daily : list) {
            dates.add(daily.getDateCalculated());

            //无法直接确定要获取那个字段的结果，先要对type进行判断
            switch (type) {
                case "login_num":
                    counts.add(daily.getLoginNum().toString());
                    break;
                case "register_num":
                    counts.add(daily.getRegisterNum().toString());
                    break;
                case "video_view_num":
                    counts.add(daily.getVideoViewNum().toString());
                    break;
                case "course_num":
                    counts.add(daily.getCourseNum().toString());
                    break;
            }
        }

        map.put("dates", dates);
        map.put("counts", counts);
        return map;
    }
}
