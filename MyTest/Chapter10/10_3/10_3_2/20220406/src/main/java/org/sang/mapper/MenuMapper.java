package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.sang.model.Menu;

import java.util.List;


@Mapper
public interface MenuMapper {
    List<Menu> getAllMenus();
}
