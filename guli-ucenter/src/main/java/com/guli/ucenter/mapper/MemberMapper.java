package com.guli.ucenter.mapper;

import com.guli.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author jason
 * @since 2019-12-21
 */
@Repository
public interface MemberMapper extends BaseMapper<Member> {

    Integer getRegisterCount(String day);
}
