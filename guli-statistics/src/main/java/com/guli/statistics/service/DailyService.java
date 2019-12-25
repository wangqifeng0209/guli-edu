package com.guli.statistics.service;

import com.guli.statistics.entity.Daily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author jason
 * @since 2019-12-21
 */
public interface DailyService extends IService<Daily> {

    Integer getRegisterCount(String day);

    Map<String, Object> getCountDay(String type, String begin, String end);
}
