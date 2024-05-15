package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 用户管理对象 f_users
 *
 * @author chuan
 * @date 2024-05-14
 */
public class FUsers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户id */
    @Excel(name = "用户id")
    private Long usersId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String usersName;

    /** 用户性别 */
    @Excel(name = "用户性别")
    private String usersSex;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String usersPhone;

    /** 用户密码 */
    @Excel(name = "用户密码")
    private String usersPassword;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String usersAvatar;

    /** 用户地址 */
    @Excel(name = "用户地址")
    private String userAddress;

    public void setUsersId(Long usersId)
    {
        this.usersId = usersId;
    }

    public Long getUsersId()
    {
        return usersId;
    }
    public void setUsersName(String usersName)
    {
        this.usersName = usersName;
    }

    public String getUsersName()
    {
        return usersName;
    }
    public void setUsersSex(String usersSex)
    {
        this.usersSex = usersSex;
    }

    public String getUsersSex()
    {
        return usersSex;
    }
    public void setUsersPhone(String usersPhone)
    {
        this.usersPhone = usersPhone;
    }

    public String getUsersPhone()
    {
        return usersPhone;
    }
    public void setUsersPassword(String usersPassword)
    {
        this.usersPassword = usersPassword;
    }

    public String getUsersPassword()
    {
        return usersPassword;
    }
    public void setUsersAvatar(String usersAvatar)
    {
        this.usersAvatar = usersAvatar;
    }

    public String getUsersAvatar()
    {
        return usersAvatar;
    }
    public void setUserAddress(String userAddress)
    {
        this.userAddress = userAddress;
    }

    public String getUserAddress()
    {
        return userAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("usersId", getUsersId())
                .append("usersName", getUsersName())
                .append("usersSex", getUsersSex())
                .append("usersPhone", getUsersPhone())
                .append("usersPassword", getUsersPassword())
                .append("usersAvatar", getUsersAvatar())
                .append("userAddress", getUserAddress())
                .append("createTime", getCreateTime())
                .toString();
    }
}
