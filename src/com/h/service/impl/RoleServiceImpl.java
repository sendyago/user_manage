package com.h.service.impl;

import com.h.dao.RoleDao;
import com.h.dao.impl.RoleDaoImpl;
import com.h.service.RoleService;
import com.h.vo.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService {

    RoleDao roleDao = new RoleDaoImpl();

    @Override
    public List<Role> queryRolesBy(Role role, int pageNum, int lineNum) throws SQLException {
        return roleDao.queryRolesBy(role, pageNum, lineNum);
    }

    @Override
    public int queryRoleCountBy(Role role) throws SQLException {
        return roleDao.queryRoleCountBy(role);
    }

    @Override
    public Role queryRoleBy(String roleId) throws SQLException {
        return roleDao.queryRoleBy(roleId);
    }

    @Override
    public void add(Role role) throws SQLException {
        roleDao.add(role);
    }

    @Override
    public void edit(Role role) throws SQLException {
        roleDao.edit(role);
    }

    @Override
    public void deleteRoleBy(String roleId) throws SQLException {
        roleDao.deleteRoleBy(roleId);
    }
}
