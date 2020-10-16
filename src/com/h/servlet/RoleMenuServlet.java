package com.h.servlet;

import com.h.service.MenuService;
import com.h.service.RoleService;
import com.h.service.impl.MenuServiceImpl;
import com.h.service.impl.RoleServiceImpl;
import com.h.vo.Menu;
import com.h.vo.Role;
import com.h.vo.RoleMenu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class RoleMenuServlet extends HttpServlet {

    RoleService roleService = null;
    MenuService menuService = null;

    @Override
    public void init() throws ServletException {
        roleService = new RoleServiceImpl();
        menuService = new MenuServiceImpl();
    }

    @Override
    public void destroy() {
        roleService = null;
        menuService = null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        switch (type) {
            case "1" :  // 展示角色对应菜单
                queryRoleMenu(req, resp);
                break;
            case "2" :  // 保存
                saveRoleMenu(req, resp);
                break;
            default:    // 查询
                queryMenu(req, resp);
        }
    }

    public void saveRoleMenu(HttpServletRequest req, HttpServletResponse resp) {
        String[] menus = req.getParameterValues("roleMenu");
        String roleId = req.getParameter("roleId");
        // 删除角色对应的菜单信息
        try {
            menuService.deleteRoleMenuBy(roleId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (String menuId: menus) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(Integer.parseInt(roleId));
            roleMenu.setMenuId(Integer.parseInt(menuId));
            // 保存
            try {
                menuService.save(roleMenu);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        try {
            req.getRequestDispatcher("/roleMenuServlet?type=0").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryRoleMenu(HttpServletRequest req, HttpServletResponse resp) {
        String roleId = req.getParameter("roleId");
        try {
            List<Menu> menuList = menuService.queryMenusBy(roleId);
            req.setAttribute("menuList", menuList);
            req.setAttribute("roleId", roleId);
            req.getRequestDispatcher("/pages/roleMenu/editRoleMenu.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void queryMenu(HttpServletRequest req, HttpServletResponse resp) {
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
            req.getRequestDispatcher("/pages/roleMenu/roleMenuList.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
