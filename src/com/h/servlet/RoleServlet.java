package com.h.servlet;

import com.h.service.RoleService;
import com.h.service.impl.RoleServiceImpl;
import com.h.vo.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 角色servlet
 */
public class RoleServlet extends HttpServlet {

    RoleService roleService;

    @Override
    public void init() throws ServletException {
        roleService = new RoleServiceImpl();
    }

    @Override
    public void destroy() {
        roleService = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "1": // 保存
                addRole(req, resp);
                break;
            case "2": // 编辑
                editRole(req, resp);
                break;
            case "3": // 删除
                deleteRole(req, resp);
                break;
            default: // 查询
                queryRoles(req, resp);
        }
    }

    public void queryRoles(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Role role = new Role();
            role.setRoleName(req.getParameter("rName"));
            String pageNum = req.getParameter("pageNum");
            String changeNum = req.getParameter("changeNum");
            // pl每页显示的记录行数， pn当前页码，cn（上一页、下一页、查询），tn总的记录数
            int pl = 10, pn = 1, cn = 0, tn = roleService.queryRoleCountBy(role);
            // tp总的页数
            int tp = tn / 10 + (tn % 10 == 0 ? 0 : 1);
            // 当根据查询条件查询结果为空时，总页数默认为1页
            if (tp == 0) {
                tp = 1;
            }
            if (pageNum != null && !"".equals(pageNum)) {
                pn = Integer.parseInt(pageNum);
            }
            if (changeNum != null && !"".equals(changeNum)) {
                cn = Integer.parseInt(changeNum);
            }

            if (!(pn == 1 && cn == -1) && !(pn == tp && cn == 1)) {
                pn = pn + cn;
            }

            if (pn > tp) {
                pn = tp;
            }

            List<Role> list = roleService.queryRolesBy(role, pn, pl);
            req.setAttribute("role", role);
            req.setAttribute("pageNum", pn);
            req.setAttribute("totalPageNum", tp);
            req.setAttribute("totalNum", tn);
            req.setAttribute("roleList", list);
            req.getRequestDispatcher("/pages/role/roleList.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Role role = new Role();
            role.setRoleName(req.getParameter("roleName"));
            String roleId = req.getParameter("roleId");
            if (roleId != null && !"".equals(roleId)) {
                role.setRoleId(Integer.parseInt(roleId));
            }
            String flag = req.getParameter("flag");
            if ("1".equals(flag)) {
                roleService.edit(role);
            } else {
                roleService.add(role);
            }
            req.getRequestDispatcher("/roleServlet?type=0").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String roleId = req.getParameter("roleId");
            Role role = roleService.queryRoleBy(roleId);
            req.setAttribute("role", role);
            req.setAttribute("flag", "1");
            req.getRequestDispatcher("/pages/role/addRole.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteRole(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String roleId = req.getParameter("roleId");
            if (roleId != null && !"".equals(roleId)) {
                roleService.deleteRoleBy(roleId);
            }
            req.getRequestDispatcher("/roleServlet?type=0").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
