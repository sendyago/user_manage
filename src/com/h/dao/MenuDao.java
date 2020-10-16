package com.h.dao;

import com.h.vo.Menu;
import com.h.vo.RoleMenu;

import java.sql.SQLException;
import java.util.List;

public interface MenuDao {

    List<Menu> queryMenusBy(Menu menu, int pageNum, int lineNum) throws SQLException;

    int queryMenuCountBy(Menu menu) throws SQLException;

    Menu queryMenuBy(String menuId) throws SQLException;

    void add(Menu menu) throws SQLException;

    void edit(Menu menu) throws SQLException;

    void deleteMenuBy(String menuId) throws SQLException;

    List<Menu> queryMenusBy(String roleId) throws SQLException;

    void save(RoleMenu roleMenu) throws SQLException;

    void deleteRoleMenuBy(String roleId) throws SQLException;

}
