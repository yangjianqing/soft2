package cn.lanqiao.system.mapper;

import java.util.List;
import cn.lanqiao.system.domain.FAppraise;

/**
 * 评价管理Mapper接口
 * 
 * @author lanqiao
 * @date 2024-05-21
 */
public interface FAppraiseMapper 
{
    /**
     * 查询评价管理
     * 
     * @param reviewid 评价管理主键
     * @return 评价管理
     */
    public FAppraise selectFAppraiseByReviewid(Long reviewid);

    /**
     * 查询评价管理列表
     * 
     * @param fAppraise 评价管理
     * @return 评价管理集合
     */
    public List<FAppraise> selectFAppraiseList(FAppraise fAppraise);

    /**
     * 新增评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    public int insertFAppraise(FAppraise fAppraise);

    /**
     * 修改评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    public int updateFAppraise(FAppraise fAppraise);

    /**
     * 删除评价管理
     * 
     * @param reviewid 评价管理主键
     * @return 结果
     */
    public int deleteFAppraiseByReviewid(Long reviewid);

    /**
     * 批量删除评价管理
     * 
     * @param reviewids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFAppraiseByReviewids(Long[] reviewids);

}
