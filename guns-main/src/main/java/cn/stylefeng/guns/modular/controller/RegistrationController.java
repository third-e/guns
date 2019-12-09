package cn.stylefeng.guns.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
import cn.stylefeng.guns.modular.model.MailboxDto;
import cn.stylefeng.guns.modular.service.IsAutoReviewService;
import cn.stylefeng.guns.modular.service.MailService;
import cn.stylefeng.guns.sys.core.constant.Const;
import cn.stylefeng.guns.sys.core.constant.dictmap.UserDict;
import cn.stylefeng.guns.sys.modular.system.model.UserDto;
import cn.stylefeng.guns.sys.modular.system.service.UserService;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.kernel.model.exception.RequestEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 注册页面控制
 */

@Controller
public class RegistrationController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;
    @Autowired
    private IsAutoReviewService autoReviewService;

    /**
     * 跳转到注册界面
     * @return
     */
    @RequestMapping(value = "/registration")
    public String hello(){
        if (LoginContextHolder.getContext().hasLogin()) {
            return REDIRECT + "/";
        } else {
            return "/registration.html";
        }
    }
    /**
     * 点击注册执行动作
     */

    @RequestMapping(value = "/registration_add",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData registeredUser(UserDto user, MailboxDto mailboxDto){
        this.userService.addUser(user);
        mailboxDto.setMailTitle("账号注册审核通知！");
        if(ConstantsContext.getRegisteredReviewOpen()){
            autoReviewService.setUserRole(user.getAccount());
            mailboxDto.setContent("您好，"+mailboxDto.getName()+"先生/女士，您申请的账号审核通过，现可以登录了！");
        }else {
            mailboxDto.setContent("您好，"+mailboxDto.getName()+"先生/女士，您申请的账号正在审核中，请您耐心等待！");
        }

        this.mailService.sendMail(mailboxDto);
        return SUCCESS_TIP;
    }
}
