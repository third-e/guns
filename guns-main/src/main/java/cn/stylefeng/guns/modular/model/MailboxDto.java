package cn.stylefeng.guns.modular.model;

import lombok.Data;

/**
 * 邮箱传输bean
 */
@Data
public class MailboxDto {
    private Long mailId; //邮箱编号
    private String name; //发送者姓名
    private String mailTitle; //邮箱标题
    private String content; //邮箱内容
    private String email; //接收者邮箱
    private String emailFrom; //发送者邮箱
}
