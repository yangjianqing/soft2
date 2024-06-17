package cn.lanqiao.system.service;

import java.util.List;
import cn.lanqiao.system.domain.Fadvertisement;

/**
 * 广告Service接口
 *
 * @author lanqiao
 * @date 2024-06-17
 */
public interface FadvertisementService {

    List<Fadvertisement> selectFadvertisementList();
}
