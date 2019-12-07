package cn.stylefeng.guns.modular.mapper;

import cn.stylefeng.guns.sys.modular.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 */

public interface IsAutoReviewMapper extends BaseMapper<User> {

    int setRoles(@Param("account") String account);
}
