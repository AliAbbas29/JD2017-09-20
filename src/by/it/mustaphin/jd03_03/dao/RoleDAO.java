package by.it.mustaphin.jd03_03.dao;

import by.it.mustaphin.jd03_03.bean.Role;
import by.it.mustaphin.jd03_03.connection.ConnectionCreator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstactDAO implements InterfaceDAO<Role> {

    private static RoleDAO roleDAO = null;

    public static RoleDAO getRoleDAO() {
        if (null == roleDAO) {
            synchronized (RoleDAO.class) {
                if (null == roleDAO) {
                    roleDAO = new RoleDAO();
                }
            }
        }
        return roleDAO;
    }

    @Override
    public boolean create(Role role) throws SQLException {
        int id_role = role.getId_role();
        String type = role.getType();
        role.setId_role(executeUpdate("INSERT INTO roles (id_role, type) VALUES ('" + id_role + "', '" + type + "');"));
        return (0 < role.getId_role());
    }

    @Override
    public boolean update(Role role) throws SQLException {
        return (1 == executeUpdate("UPDATE roles SET type='" + role.getType() + "' WHERE id_role='" + role.getId_role() + "';"));
    }

    @Override
    public boolean delete(Role role) throws SQLException {
        return (0 < executeUpdate("DELETE FROM roles WHERE id_role='" + role.getId_role() + "';"));
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return (0 < executeUpdate("DELETE FROM roles WHERE id_role='" + id + "';"));
    }

    @Override
    public Role read(Role role) throws SQLException {
        try (Connection con = ConnectionCreator.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM roles WHERE id_role='" + role.getId_role() + "';");
            if (rs.next()) {
                role = new Role(
                        rs.getInt("id_role"),
                        rs.getString("type"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return role;
    }

    @Override
    public Role read(int id) throws SQLException {
        Role role = null;
        try (Connection con = ConnectionCreator.getConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM roles WHERE id_role='" + id + "';");
            if (rs.next()) {
                role = new Role(
                        rs.getInt("id_role"),
                        rs.getString("type"));
            }
        } catch (SQLException e) {
            throw e;
        }
        return role;
    }

    @Override
    public List<Role> getAll() throws SQLException {
        return getAll("");
    }

    @Override
    public List<Role> getAll(String condition) throws SQLException {
        List<Role> roles = new ArrayList<>();
        try (Connection con = ConnectionCreator.getConnection(); Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery("SELECT * FROM roles" + condition + ";");
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("id_role"),
                        rs.getString("type"));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw e;
        }
        return roles;
    }
}
