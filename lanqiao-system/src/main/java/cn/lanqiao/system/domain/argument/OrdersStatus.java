package cn.lanqiao.system.domain.argument;

import cn.lanqiao.common.annotation.Excel;
import java.util.List;

public class OrdersStatus {
    /** 用户电话号码 */
    @Excel(name = "用户电话号码")
    private String usersPhone;

    /** 商品条码集合 */
    @Excel(name = "商品条码集合")
    private List<String> partListId;

    public String getUsersPhone() {
        return usersPhone;
    }

    public void setUsersPhone(String usersPhone) {
        this.usersPhone = usersPhone;
    }

    public List<String> getPartListId() {
        return partListId;
    }

    public void setPartListId(List<String> partListId) {
        this.partListId = partListId;
    }
}