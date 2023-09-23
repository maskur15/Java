package web;

import dao.UserDao;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 18-Sep-23.
 */
@WebServlet(name = "/user")
public class UserServlet extends HttpServlet {
    private static final long SerialVersionUid = 1L;

    private UserDao userDao;

    public UserServlet() {

        this.userDao= new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        switch (action){
            case "/home":
                showHome(request,response);
                break;
            case "/insert":
                insertUser(request,response);
                break;
            case "/delete":
                delete_user(request,response);
                break;
            case "/edit":
                showEditForm(request,response);
                break;
            case "/update":
                updateUser(request,response);
                break;
            default:
                getListofUser(request,response);

                break;

        }
    }

    private  void showHome(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        List<User> userList=userDao.getUsers();
        request.setAttribute("userList",userList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request,response);
    }
    private void insertUser(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        User new_user = new User(name,email,gender,country);
        userDao.insert_intoUser(new_user);
        response.sendRedirect("list");
    }
    private void delete_user(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        userDao.delete_user(id);
        response.sendRedirect("list");
    }
    private void showEditForm(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        User existing_user = userDao.getuserById(id);
        request.setAttribute("user",existing_user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userform.jsp");
        dispatcher.forward(request,response);
    }
    private void updateUser(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");
        User new_user = new User(id,name,email,gender,country);

        userDao.update_user(new_user);
        response.sendRedirect("list");
    }
    private void getListofUser(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        List <User> users = userDao.getUsers();
        request.setAttribute("userList",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request,response);
    }
}
