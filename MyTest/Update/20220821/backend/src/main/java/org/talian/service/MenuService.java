package org.talian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.talian.bean.Menu;
import org.talian.common.HrUtils;
import org.talian.mapper.MenuMapper;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
    MenuMapper menuMapper;


    /**
     * 这里作者又是私自改了，书中没有任何提醒，github中也没任何提醒.
     * 下面这行代码github中是注释掉了的，
     * 如果不注释掉，在添加了其他的新Mapper、新service的情况下，登入会失败，同时报错。
     * 恶心的点就在此。
     * @return
     */
//    @Cacheable(key = "#root.methodName")
    public List<Menu> getAllMenu(){
        return menuMapper.getAllMenu();
    }






    public List<Menu> getMenusByHrId() {
        return menuMapper.getMenusByHrId(HrUtils.getCurrentHr().getId());
    }

    public List<Menu> menuTree() {
        return menuMapper.menuTree();
    }

    public List<Long> getMenusByRid(Long rid) {
        return menuMapper.getMenusByRid(rid);
    }
}



