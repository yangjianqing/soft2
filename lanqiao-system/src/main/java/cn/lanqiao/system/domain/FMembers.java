package cn.lanqiao.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.lanqiao.common.annotation.Excel;
import cn.lanqiao.common.core.domain.BaseEntity;

/**
 * 会员管理对象 f_members
 *
 * @author chuan
 * @date 2024-05-15
 */
public class FMembers extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员id */
    @Excel(name = "会员id")
    private Long memberId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private Long usersId;

    /** 用户姓名 */
    @Excel(name = "用户姓名")
    private String usersName;

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    /** 会员级别 */
    @Excel(name = "会员级别")
    private Long memberLevel;

    /** 会员积分 */
    @Excel(name = "会员积分")
    private Long memberIntegration;

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    public Long getMemberId()
    {
        return memberId;
    }
    public void setUsersId(Long usersId)
    {
        this.usersId = usersId;
    }

    public Long getUsersId()
    {
        return usersId;
    }
    public void setMemberLevel(Long memberLevel)
    {
        this.memberLevel = memberLevel;
    }

    public Long getMemberLevel()
    {
        return memberLevel;
    }
    public void setMemberIntegration(Long memberIntegration)
    {
        this.memberIntegration = memberIntegration;
    }

    public Long getMemberIntegration()
    {
        return memberIntegration;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("memberId", getMemberId())
                .append("usersId", getUsersId())
                .append("memberLevel", getMemberLevel())
                .append("memberIntegration", getMemberIntegration())
                .toString();
    }
}
