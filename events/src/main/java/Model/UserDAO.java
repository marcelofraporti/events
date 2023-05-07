package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UserDAO {
    private Connection conn;
    
    public final String tabela = "User";
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("events");
    EntityManager manager = factory.createEntityManager();
    
    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public UserDAO() {
        }
    
    public boolean registrar(User user){
        manager.getTransaction().begin();
        manager.persist(user);
        manager.getTransaction().commit();
        return true;
    }
    

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, first_name, last_name, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirst_name());
        statement.setString(4, user.getLast_name());
        statement.setString(5, user.getEmail());
        statement.executeUpdate();
        statement.close();
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        User user = null;
        if (result.next()) {
            user = new User(
                result.getInt("id"),
                result.getString("username"),
                result.getString("password"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("email")
            );
        }

        result.close();
        statement.close();
        return user;
    }

    public List<User> getUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet result = statement.executeQuery();

        User user = null;
        if (result.next()) {
            user = new User(
                result.getInt("id"),
                result.getString("username"),
                result.getString("password"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("email")
            );
        }

        result.close();
        statement.close();
        return (List<User>) user;
    }

    public List<User> getAllUsers() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        List<User> users = new ArrayList<>();
        while (result.next()) {
            User user = new User(
                result.getInt("id"),
                result.getString("username"),
                result.getString("password"),
                result.getString("first_name"),
                result.getString("last_name"),
                result.getString("email")
            );
            users.add(user);
        }

        result.close();
        statement.close();
        return users;
    }
    
    public ArrayList<User> consultar()
    {
        manager.getTransaction().begin();
        Query query =  manager.createQuery("SELECT e FROM " + tabela + " e ");
        ArrayList<User> user = (ArrayList<User>) query.getResultList();
        manager.getTransaction().commit();
        return user;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ? WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getFirst_name());
        statement.setString(4, user.getLast_name());
        statement.setString(5, user.getEmail());
        statement.setInt(6, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
        statement.close();
    }


}