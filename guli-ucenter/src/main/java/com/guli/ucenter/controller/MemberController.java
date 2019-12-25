package com.guli.ucenter.controller;


import com.guli.edu.common.R;
import com.guli.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author jason
 * @since 2019-12-21
 */
@RestController
@CrossOrigin
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    MemberService memberService;

    @GetMapping("/getRegisterCount/{day}")
    public R getRegisterCount(@PathVariable String day){
        Integer count = memberService.getRegisterCount(day);
        return R.ok().data("register",count);
    }
}

