package web;
import javax.mail.Session;

import com.sun.deploy.net.HttpResponse;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import dao.EmailConfirmation;
import dao.ProjectDao;
import model.User;
import dao.UserDao;
import sun.plugin2.message.Message;
import sun.plugin2.message.transport.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created by ASUS on 18-Sep-23.
 */
public class ShowUser extends HttpServlet {
    private UserDao userDao=new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action){

            case  "/login":
                checkLogin(request,response);
                break;
            case "/register":
                registerUser(request,response);
                break;
            case "/confirm":
                confirmUser(request,response);
                break;
            case "/list_all":
                getUserList(request,response);
              break;
            case "/insert":
                insertUser(request,response);
                break;
            case "/delete":
                deleteUser(request,response);
                break;
            case "/edit":
                editUser(request,response);
                break;
            case "/update":
                updateUser(request,response);
                break;
            default:


                break;

        }
        System.out.println(action);

    }


    private void registerUser(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");
        String confirmPassword= request.getParameter("confirmPassword");
        String userType = request.getParameter("userType");
        String address= request.getParameter("address");
        System.out.println(name+email+gender+ userType+address);
        String confirmationToken = UUID.randomUUID().toString();
        try {
            // Save the user data and confirmation token in the database
            if (password.equals(confirmPassword) && saveUserData(name,email,gender,password,userType,address,confirmationToken)) {
                // Send a confirmation email

                sendConfirmationEmail(email, confirmationToken);
                System.out.println("Email send ");
                response.sendRedirect("register_success.jsp"); // Redirect to a success page
            } else {
                // Handle database error or duplicate email address
                System.out.println("Email not send ");
                response.sendRedirect("register_error.jsp"); // Redirect to an error page
            }
        }
      catch (Exception e){
             response.sendRedirect("register.jsp");
      }

        User new_user = new User(name,email,gender,password,userType,address,confirmationToken);



    }

    private void confirmUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        System.out.println(token);
        try {
            if (token != null && !token.isEmpty()) {
                ProjectDao db = new ProjectDao();
                User user = db.getUserBytoken(token);
                if (user == null) {
                    response.sendRedirect("register_error.jsp");
                } else {
                    db.ConfirmUserByID(user.getId());
                    System.out.println(user.getId() + user.getName() + user.getEmail());
                    response.sendRedirect("login.jsp");
                }
            } else {
                // Handle the case where token is null or empty
                response.sendRedirect("register_error.jsp");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void checkLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String email = request.getParameter("email");
       String password = request.getParameter("password");
       boolean success = false;
       try{
           if(email!=null && password!=null){
               System.out.println(email+password);
               ProjectDao db = new ProjectDao();
               User user = db.getUserbyEmail(email);
               System.out.println(user.getId()+user.getName());
               if(user!=null && user.getPassword().equals(password)){
                   success=true;
               }
           }
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }
       if(success){
           response.sendRedirect("login_success.jsp");
       }
       else {
           request.getSession().setAttribute("message", "Try with email & password");

           response.sendRedirect("login.jsp");

       }
    }

    private boolean saveUserData(String name, String email, String gender, String password,
                                 String userType,String address,String confirmationToken) {
        // Implement database connection and insert user data with confirmation token
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establish a database connection (replace with your database URL, username, and password)
            ProjectDao db = new ProjectDao();
            connection=db.connect();
            // Insert user data into the users table
            String sql = "INSERT INTO users (name, email, gender, password,type,address,confirmation_token) VALUES (?, ?, ?, ?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, userType);
            preparedStatement.setString(6, address);
            preparedStatement.setString(7, confirmationToken);



            // Execute the SQL statement
            int rowsAffected = preparedStatement.executeUpdate();

            // If rowsAffected is greater than 0, the insertion was successful
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database error (e.g., duplicate email address)
            return false;
        } finally {
            // Close database resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendConfirmationEmail(String email, String confirmationToken) {
        EmailConfirmation emailObj = new EmailConfirmation();
        String confirmationLink = "http://localhost:8080/confirm?token=" + confirmationToken;
        String emailContent = "Please click the following link to confirm your account: \n" + confirmationLink;

        emailObj.sendEmail(email,"Confirmation Mail",emailContent);
    }

    private void insertUser(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");

        System.out.println(name+email+gender+country);

        User new_user = new User(name,email,gender,country);
        userDao.insert_intoUser(new_user);

        response.sendRedirect("list_all");

    }
    private  void getUserList(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        List<User> users = userDao.getUsers();
        request.setAttribute("studentList",users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userList.jsp");
        dispatcher.forward(request,response);
    }
    private void  deleteUser(HttpServletRequest request,HttpServletResponse
                             response) throws ServletException,IOException{
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        try{
            userDao.delete_user(studentId);

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        response.sendRedirect("list_all");
    }
    private void  editUser(HttpServletRequest request,HttpServletResponse
            response) throws ServletException,IOException{
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        User student = userDao.getuserById(studentId);

        request.setAttribute("student",student);
        RequestDispatcher dispatcher= request.getRequestDispatcher("editForm.jsp");
        dispatcher.forward(request,response);

    }
    private void updateUser(HttpServletRequest request,HttpServletResponse response)
            throws ServletException,IOException{
        int studentId = Integer.parseInt(request.getParameter("student_id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String country = request.getParameter("country");

        System.out.println(studentId+name+email+gender+country);

        User new_user = new User(studentId,name,email,gender,country);
        try{
            System.out.println(new_user.getId()+new_user.getName()+new_user.getCountry());

            userDao.update_user(new_user);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        response.sendRedirect("list_all");

    }
}
