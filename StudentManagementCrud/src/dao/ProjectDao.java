package dao;

import model.User;

import java.sql.*;

/**
 * Created by ASUS on 22-Sep-23.
 */
public class ProjectDao {
    private final String url = "jdbc:postgresql://localhost/studentmanagement";
    private final String user = "postgres";
    private final String password = "postgres";
    String insert_SQL = "INSERT INTO users(name,email,gender,address,password,type,confirmation_token)"
            + "VALUES(?,?,?,?,?,?,?)";

    public Connection connect() {
        Connection conn = null;
        try {
            //Registering the jdbc driver
            Class.forName("org.postgresql.Driver");//this is use to load the postgres driver when the server is on

            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }


    // Check if the email is available (not already registered)
    public boolean isEmailAvailable(String email) {
        try (Connection connection = connect()) {
            String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, email);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count == 0; // If count is 0, the email is available
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Error occurred or email is not available
    }
    public User getUserbyEmail(String email){
        User user = null;

        try{
            String sql = "select * from users where email = ?";
            Connection con = connect();
            PreparedStatement pst  = con.prepareStatement(sql);
            pst.setString(1,email);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                long    id = rs.getLong("user_id");
                String name =rs.getString("name");
                String gender = rs.getString("gender");
                String password = rs.getString("password");
                String usertype = rs.getString("type");
                String address = rs.getString("address");
                String token= rs.getString("confirmation_token");
                boolean isConfrimed = rs.getBoolean("is_confirmed");
                System.out.println(id+name+email+gender+password);

                user = new User((int)id,name,email,gender,address,password,usertype,token,isConfrimed);

            }
            rs.close();
            pst.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return user;
    }

    public User getUserBytoken(String token){
        User user = null;

        try{
            String sql = "select * from users where confirmation_token = ?";
            Connection con = connect();
            PreparedStatement pst  = con.prepareStatement(sql);
            pst.setString(1,token);

            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                long    id = rs.getLong("user_id");
                String name =rs.getString("name");
                String email =rs.getString("email");
                String gender = rs.getString("gender");
                String password = rs.getString("password");
                String usertype = rs.getString("type");
                String address = rs.getString("address");
                boolean isConfrimed = rs.getBoolean("is_confirmed");
                System.out.println(id+name+email+gender+password);

                user = new User((int)id,name,email,gender,address,password,usertype,token,isConfrimed);

            }
            rs.close();
            pst.close();
            con.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return user;
    }
    public void  ConfirmUserByID(int id){
        int affectedrows=0;
        try{
            String sql = "update users "+"set is_confirmed=? "+ "where user_id = ? ";
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setBoolean(1,true);
            pstmt.setLong(2,id);

            affectedrows = pstmt.executeUpdate();
            conn.close();
            pstmt.close();

        }
        catch (Exception e){
            System.out.println(e);
        }


        System.out.println(affectedrows);
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


    public static void main(String[] args) {
        ProjectDao db= new ProjectDao() ;
//        db.saveUserData("maskur","email@gmail.com","Male",
//                "sfjlsajf","Teacher","Rangpur","kljfjaf");

        //db.getUserBytoken("de3daec4-360f-4ee0-8561-5cb271ab71c0");
        User user = db.getUserbyEmail("thschowdhury2020@gmail.com");
        System.out.println(user.getName()+user.getPassword());
    }
}
