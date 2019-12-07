package cn.stylefeng.guns.modular.controller;

import cn.stylefeng.guns.base.auth.context.LoginContextHolder;
import cn.stylefeng.guns.base.consts.ConstantsContext;
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
    public ResponseData registeredUser(UserDto user){
        this.userService.addUser(user);
        this.mailService.sendMail(user.getEmail());
        if(ConstantsContext.getRegisteredReviewOpen()){
            autoReviewService.setUserRole(user.getAccount());
        }
        return SUCCESS_TIP;
    }
}
