package cn.stylefeng.guns.modular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 日程控制器
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleIndexController {

    /**
     * 跳转到日程首页
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "/console_schedule_index.html";
    }

    /**
     * 跳转到日程
     * @return
     */
    @RequestMapping("/richeng")
    public String richeng(){
        return "/index1.html";
    }
}
