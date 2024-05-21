package cn.lanqiao.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FAppraiseMapper;
import cn.lanqiao.system.domain.FAppraise;
import cn.lanqiao.system.service.IFAppraiseService;

/**
 * 评价管理Service业务层处理
 * 
 * @author lanqiao
 * @date 2024-05-21
 */
@Service
public class FAppraiseServiceImpl implements IFAppraiseService 
{
    @Autowired
    private FAppraiseMapper fAppraiseMapper;

    /**
     * 查询评价管理
     * 
     * @param reviewid 评价管理主键
     * @return 评价管理
     */
    @Override
    public FAppraise selectFAppraiseByReviewid(Long reviewid)
    {
        return fAppraiseMapper.selectFAppraiseByReviewid(reviewid);
    }

    /**
     * 查询评价管理列表
     * 
     * @param fAppraise 评价管理
     * @return 评价管理
     */
    @Override
    public List<FAppraise> selectFAppraiseList(FAppraise fAppraise)
    {
        return fAppraiseMapper.selectFAppraiseList(fAppraise);
    }

    /**
     * 新增评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    @Override
    public int insertFAppraise(FAppraise fAppraise)
    {
        return fAppraiseMapper.insertFAppraise(fAppraise);
    }

    /**
     * 修改评价管理
     * 
     * @param fAppraise 评价管理
     * @return 结果
     */
    @Override
    public int updateFAppraise(FAppraise fAppraise)
    {
        return fAppraiseMapper.updateFAppraise(fAppraise);
    }

    /**
     * 批量删除评价管理
     * 
     * @param reviewids 需要删除的评价管理主键
     * @return 结果
     */
    @Override
    public int deleteFAppraiseByReviewids(Long[] reviewids)
    {
        return fAppraiseMapper.deleteFAppraiseByReviewids(reviewids);
    }

    /**
     * 删除评价管理信息
     * 
     * @param reviewid 评价管理主键
     * @return 结果
     */
    @Override
    public int deleteFAppraiseByReviewid(Long reviewid)
    {
        return fAppraiseMapper.deleteFAppraiseByReviewid(reviewid);
    }
}
