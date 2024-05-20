package cn.lanqiao.system.service.impl;

import java.util.*;

import cn.lanqiao.system.domain.FMembers;
import cn.lanqiao.system.domain.FUsers;
import cn.lanqiao.system.mapper.FUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.lanqiao.system.mapper.FSalesMapper;
import cn.lanqiao.system.domain.FSales;
import cn.lanqiao.system.service.IFSalesService;

/**
 * 销售记录管理Service业务层处理
 * 
 * @author chuan
 * @date 2024-05-16
 */
@Service
public class FSalesServiceImpl implements IFSalesService 
{
    @Autowired
    private FSalesMapper fSalesMapper;

    @Autowired
    private FUsersMapper fUsersMapper;
    /**
     * 查询销售记录管理
     * 
     * @param salesId 销售记录管理主键
     * @return 销售记录管理
     */
    @Override
    public FSales selectFSalesBySalesId(Long salesId)
    {
        return fSalesMapper.selectFSalesBySalesId(salesId);
    }

    /**
     * 查询销售记录管理列表
     * 
     * @param fSales 销售记录管理
     * @return 销售记录管理
     */
    @Override
    public List<FSales> selectFSalesList(FSales fSales)
    {
        List<FSales> fSales1 = fSalesMapper.selectFSalesList(fSales);
        Set<Long> usersIds = new HashSet<>();

        for (FSales fa : fSales1) {
            if(fa.getUsersId() != null) {
                usersIds.add(fa.getUsersId());
            }
        }

        List<FUsers> UsersList = new ArrayList<>();
        if(!usersIds.isEmpty()) {
            UsersList = fUsersMapper.selectUsersListCIds(new ArrayList<>(usersIds));
        }

        Map<Long, FUsers> busersMap = new HashMap<>();
        for (FUsers f : UsersList) {
            busersMap.put(f.getUsersId(), f);
        }

        for (FSales fa : fSales1) {
            if (fa.getUsersId() != null) {
                FUsers fUsers = busersMap.get(fa.getUsersId());
                if (fUsers != null) {
                    fa.setUsersName(fUsers.getUsersName());
                }
            }
        }
        return fSales1;
    }

    /**
     * 新增销售记录管理
     * 
     * @param fSales 销售记录管理
     * @return 结果
     */
    @Override
    public int insertFSales(FSales fSales)
    {
        return fSalesMapper.insertFSales(fSales);
    }

    /**
     * 修改销售记录管理
     * 
     * @param fSales 销售记录管理
     * @return 结果
     */
    @Override
    public int updateFSales(FSales fSales)
    {
        return fSalesMapper.updateFSales(fSales);
    }

    /**
     * 批量删除销售记录管理
     * 
     * @param salesIds 需要删除的销售记录管理主键
     * @return 结果
     */
    @Override
    public int deleteFSalesBySalesIds(Long[] salesIds)
    {
        return fSalesMapper.deleteFSalesBySalesIds(salesIds);
    }

    /**
     * 删除销售记录管理信息
     * 
     * @param salesId 销售记录管理主键
     * @return 结果
     */
    @Override
    public int deleteFSalesBySalesId(Long salesId)
    {
        return fSalesMapper.deleteFSalesBySalesId(salesId);
    }
}
