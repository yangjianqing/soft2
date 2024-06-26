package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 用户管理对象 f_users
 *
 * @author chuan
 * @date 2024-05-20
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

    /** 会员级别 */
    @Excel(name = "会员级别")
    private Long memberGrade;

    /** 会员积分 */
    @Excel(name = "会员积分")
    private BigDecimal memberTotal;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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

    public void setMemberGrade(Long memberGrade)
    {
        this.memberGrade = memberGrade;
    }

    public Long getMemberGrade()
    {
        return memberGrade;
    }
    public void setMemberTotal(BigDecimal memberTotal)
    {
        this.memberTotal = memberTotal;
    }

    public BigDecimal getMemberTotal()
    {
        return memberTotal;
    }

    public FUsers() {
    }

    public FUsers(String usersName, String usersSex, String usersPhone, Long memberGrade, BigDecimal memberTotal) {
        this.usersName = usersName;
        this.usersSex = usersSex;
        this.usersPhone = usersPhone;
        this.memberGrade = memberGrade;
        this.memberTotal = memberTotal;
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
                .append("memberGrade", getMemberGrade())
                .append("memberTotal", getMemberTotal())
                .append("createTime", getCreateTime())
                .toString();
    }
}
