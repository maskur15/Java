package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 17-Sep-23.
 */

public class UserDao {
    private final String url = "jdbc:postgresql://localhost/studentmanagement";
    private final String user = "postgres";
    private final String password = "postgres";

    /**
     * Connect to the PostgreSQL database
     *
     * @return a Connection object
     */
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

    //sql for query
    String get_all_sql = "select * from student";

    String insert_SQL = "INSERT INTO student(name,email,gender,country) "
            + "VALUES(?,?,?,?)";

    String update_SQL = "UPDATE student "
            + "SET name = ? , email= ?, gender=?, country=? "
            + "WHERE id = ?";
    String delete_sql = "DELETE FROM STUDENT WHERE ID = ?";
    String userbyId_sql = "select * from student where id = ?";
    public long insert_intoUser(User user){
        long id =0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insert_SQL,
                     Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getGender());
            pstmt.setString(4,user.getCountry());
            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id ;
    }
    public long update_user(User user){
        int affectedrows = 0;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(update_SQL)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2,user.getEmail());
            pstmt.setString(3,user.getGender());
            pstmt.setString(4,user.getCountry());
            pstmt.setInt(5,user.getId());

            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;

    }
    public long delete_user(int id){
        int affectedrows = 0;
        try{
            Connection conn = connect();
            PreparedStatement pst = conn.prepareStatement(delete_sql);
            pst.setInt(1,id);
            affectedrows= pst.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return affectedrows;
    }
    public List<User> getUsers(){
        List<User> users = new ArrayList<>();
        try{
            Connection con = connect();
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(get_all_sql);
            while(rs.next()){
                int    id = rs.getInt("id");
                String name =rs.getString("name");
                String email =rs.getString("email");
                String gender = rs.getString("gender");
                String country = rs.getString("country");

                User user = new User(id,name,email,gender,country);
                users.add(user);
            }
            rs.close();
            stmt.close();
            con.close();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return users;
    }
    public User  getuserById(int id){
        User user = null;
        try {
            Connection con = connect();
            PreparedStatement pst = con.prepareStatement(userbyId_sql);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                String  name = rs.getString("name");
                String  email = rs.getString("email");
                String  gender = rs.getString("gender");
                String  country = rs.getString("country");
                System.out.println(name+email+gender+country);
                user = new User(id, name,email,gender,country);


            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static void main(String[] args) {
        UserDao db = new UserDao();
//        db.connect();
       User user1 = new User("Avvas bair","hasoi39@gmail.com","Male","BDM");
      long id = db.insert_intoUser(user1);
        //user1.setId(2);
        //db.update_user(user1,"Haansa339@gmail.com");
        //db.delete_user(2);
        //db.getUsers();
        db.getuserById(3);
        db.insert_intoUser(user1);

    }

}


