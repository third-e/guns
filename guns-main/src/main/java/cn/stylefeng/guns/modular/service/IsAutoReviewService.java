package cn.stylefeng.guns.modular.service;


import cn.stylefeng.guns.modular.mapper.IsAutoReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IsAutoReviewService {

    @Resource
    private IsAutoReviewMapper isAutoReviewMapper;

    public int setUserRole(String account) {
        return isAutoReviewMapper.setRoles(account);
    }
}
