package com.h.dao;

import com.h.vo.Role;

import java.sql.SQLException;
import java.util.List;

public interface RoleDao {

    List<Role> queryRolesBy(Role role, int pageNum, int lineNum) throws SQLException;

    int queryRoleCountBy(Role role) throws SQLException;

    Role queryRoleBy(String roleId) throws SQLException;

    void add(Role role) throws SQLException;

    void edit(Role role) throws SQLException;

    void deleteRoleBy(String roleId) throws SQLException;
}
