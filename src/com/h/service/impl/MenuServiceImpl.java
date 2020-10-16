package com.h.service.impl;

import com.h.dao.MenuDao;
import com.h.dao.impl.MenuDaoImpl;
import com.h.service.MenuService;
import com.h.vo.Menu;
import com.h.vo.RoleMenu;

import java.sql.SQLException;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    MenuDao menuDao = new MenuDaoImpl();

    @Override
    public List<Menu> queryMenusBy(Menu menu, int pageNum, int lineNum) throws SQLException {
        return menuDao.queryMenusBy(menu, pageNum, lineNum);
    }

    @Override
    public int queryMenuCountBy(Menu menu) throws SQLException {
        return menuDao.queryMenuCountBy(menu);
    }

    @Override
    public Menu queryMenuBy(String menuId) throws SQLException {
        return menuDao.queryMenuBy(menuId);
    }

    @Override
    public void add(Menu menu) throws SQLException {
        menuDao.add(menu);
    }

    @Override
    public void edit(Menu menu) throws SQLException {
        menuDao.edit(menu);
    }

    @Override
    public void deleteMenuBy(String menuId) throws SQLException {
        menuDao.deleteMenuBy(menuId);
    }

    @Override
    public List<Menu> queryMenusBy(String roleId) throws SQLException {
        return menuDao.queryMenusBy(roleId);
    }

    @Override
    public void save(RoleMenu roleMenu) throws SQLException {
        menuDao.save(roleMenu);
    }

    @Override
    public void deleteRoleMenuBy(String roleId) throws SQLException {
        menuDao.deleteRoleMenuBy(roleId);
    }
}
