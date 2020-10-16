package com.h.servlet;

import com.h.service.MenuService;
import com.h.service.impl.MenuServiceImpl;
import com.h.vo.Menu;
import com.h.vo.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MenuServlet extends HttpServlet {
    MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = new MenuServiceImpl();
    }

    @Override
    public void destroy() {
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
            case "1": // 保存
                addMenu(req, resp);
                break;
            case "2": // 编辑
                editMenu(req, resp);
                break;
            case "3": // 删除
                deleteMenu(req, resp);
                break;
            default: // 查询
                queryMenus(req, resp);
        }
    }

    public void queryMenus(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Menu menu = new Menu();
            menu.setMenuName(req.getParameter("mName"));
            String pageNum = req.getParameter("pageNum");
            String changeNum = req.getParameter("changeNum");
            // pl每页显示的记录行数， pn当前页码，cn（上一页、下一页、查询），tn总的记录数
            int pl = 10, pn = 1, cn = 0, tn = menuService.queryMenuCountBy(menu);
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

            List<Menu> list = menuService.queryMenusBy(menu, pn, pl);
            req.setAttribute("menu", menu);
            req.setAttribute("pageNum", pn);
            req.setAttribute("totalPageNum", tp);
            req.setAttribute("totalNum", tn);
            req.setAttribute("menuList", list);
            req.getRequestDispatcher("/pages/menu/menuList.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addMenu(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Menu menu = new Menu();
            menu.setMenuName(req.getParameter("menuName"));
            menu.setMenuPath(req.getParameter("menuPath"));
            String menuId = req.getParameter("menuId");
            if (menuId != null && !"".equals(menuId)) {
                menu.setMenuId(Integer.parseInt(menuId));
            }
            String flag = req.getParameter("flag");
            if ("1".equals(flag)) {
                menuService.edit(menu);
            } else {
                menuService.add(menu);
            }
            req.getRequestDispatcher("/menuServlet?type=0").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editMenu(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String menuId = req.getParameter("menuId");
            Menu menu = menuService.queryMenuBy(menuId);
            req.setAttribute("menu", menu);
            req.setAttribute("flag", "1");
            req.getRequestDispatcher("/pages/menu/addMenu.jsp").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenu(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String menuId = req.getParameter("menuId");
            if (menuId != null && !"".equals(menuId)) {
                menuService.deleteMenuBy(menuId);
            }
            req.getRequestDispatcher("/menuServlet?type=0").forward(req, resp);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
