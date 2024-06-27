package cn.lanqiao.system.domain.argument;


import cn.lanqiao.common.annotation.Excel;

public class LoginVo {
    /** 用户电话 */
    @Excel(name = "用户电话")
    private String usersPhone;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String usersPassword;

    /** uuid */
    @Excel(name = "uuid")
    private String  uuid;

    /** 验证码 */
    @Excel(name = "验证码")
    private String code;

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public String getUsersPassword() {
        return usersPassword;
    }

    public void setUsersPassword(String usersPassword) {
        this.usersPassword = usersPassword;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
