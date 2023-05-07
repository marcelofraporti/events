package Model;

import Controller.ConexaoFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import static java.time.temporal.TemporalQueries.zone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EventoDAO {

    private Connection con;

    public final String tabela = "events";
    
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("aeroporto");
    EntityManager manager = factory.createEntityManager();
    
    public EventoDAO(Connection con) {
        this.con = con;
    }

    public EventoDAO() {
    }

    public void inserir(Evento evento) {
        String sql = "INSERT INTO eventos "
                + "(name, description, event_date, created_at, updated_at, created_by, updated_by, can_update, can_delete) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, evento.getName());
            ps.setString(2, evento.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(evento.getEvent_date()));
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setInt(6, evento.getCreated_by());
            ps.setInt(7, evento.getUpdated_by());
            ps.setBoolean(8, evento.isCan_update());
            ps.setBoolean(9, evento.isCan_delete());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir evento", e);
        }
    }

    public void atualizar(Evento evento) {
        String sql = "UPDATE eventos SET "
                + "name=?, description=?, event_date=?, updated_at=?, updated_by=?, can_update=?, can_delete=? "
                + "WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, evento.getName());
            ps.setString(2, evento.getDescription());
            ps.setTimestamp(3, Timestamp.valueOf(evento.getEvent_date()));
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.setInt(5, evento.getUpdated_by());
            ps.setBoolean(6, evento.isCan_update());
            ps.setBoolean(7, evento.isCan_delete());
            ps.setInt(8, evento.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar evento", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM eventos WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir evento", e);
        }
    }

    public Evento buscarPorId(int id) {
        String sql = "SELECT * FROM eventos WHERE id=?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Evento evento = new Evento();
                    evento.setId(rs.getInt("id"));
                    evento.setName(rs.getString("name"));
                    evento.setDescription(rs.getString("description"));
                    evento.setEvent_date(rs.getTimestamp("event_date").toLocalDateTime());
                    evento.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                    evento.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
                    evento.setCreated_by(rs.getInt("created_by"));
                    evento.setUpdated_by(rs.getInt("updated_by"));
                    evento.setCan_update(rs.getBoolean("can_update"));
                    evento.setCan_delete(rs.getBoolean("can_delete"));
                    return evento;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar evento por id", e);
        }

        return null;
    }

    public ArrayList<Evento> consultar()
    {
        manager.getTransaction().begin();
        Query query =  manager.createQuery("SELECT e FROM " + tabela + " e ");
        ArrayList<Evento> evento = (ArrayList<Evento>) query.getResultList();
        manager.getTransaction().commit();
        return evento;
    }
    
    public List<Evento> buscarTodos() {
    Connection con = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    List<Evento> eventos = new ArrayList<>();

    try {
        con = ConexaoFactory.conectar();
        stmt = con.prepareStatement("SELECT * FROM eventos");
        rs = stmt.executeQuery();

        while (rs.next()) {
            Evento evento = new Evento();
            evento.setId(rs.getInt("id"));
            evento.setName(rs.getString("name"));
            evento.setDescription(rs.getString("description"));
            evento.setEvent_date(rs.getDate("event_date").toLocalDate().atTime(LocalTime.MIN));
            evento.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            evento.setUpdated_at(rs.getTimestamp("updated_at").toLocalDateTime());
            evento.setCreated_by(rs.getInt("created_by"));
            evento.setUpdated_by(rs.getInt("updated_by"));
            evento.setCan_update(rs.getBoolean("can_update"));
            evento.setCan_delete(rs.getBoolean("can_delete"));

            eventos.add(evento);
        }
    } catch (SQLException ex) {
        throw new RuntimeException("Erro ao buscar todos os eventos", ex);
    } 

    return eventos;
}
    }
    
    


       

