package Repository;

import Entities.User;
import Entities.Pacient;
import Entities.Medic;

import java.sql.*;

public class UserRepository
{
    public static final int constUserAddFail = -1;

    public static int addUser(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String access = user.getAccess();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO credentiale(username, password, access)\n" +
                    "VALUES('" + username + "', '" + password + "', '" + access + "')", Statement.RETURN_GENERATED_KEYS))
            {
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if(rs.first())
                    {
                        int userIndex = rs.getInt(1);
                        return userIndex;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return constUserAddFail;
    }

    public static void removeUser(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String access = user.getAccess();
        int userIndex = user.getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                stmt.executeQuery("DELETE FROM credentiale WHERE id = " + userIndex);
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public static boolean isValidUsername(User user)
    {
        String username = user.getUsername();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM credentiale WHERE username = '" + username + "'"))
                {
                    return !rs.first();
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean isInformationComplete(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        String access = user.getAccess();

        return !username.isEmpty() && !password.isEmpty() && !access.isEmpty();
    }

    public static User userLogin(User user)
    {
        String username = user.getUsername();
        String password = user.getPassword();

        if(username.isEmpty() || password.isEmpty())
        {
            return null;
        }

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM credentiale WHERE username = '" + username + "' AND password = '" + password + "'"))
                {
                    if(rs.first())
                    {
                        user.setIndex(rs.getInt(1));
                        user.setPassword("");
                        user.setAccess(rs.getString(4));
                        return user;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Pacient getPacient(User user)
    {
        int userIndex = user.getIndex();
        Pacient pacient = new Pacient();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM pacienti WHERE user = " + userIndex))
                {
                    if(rs.first())
                    {
                        pacient.setIndex(rs.getInt(1));
                        pacient.setNume(rs.getString(2));
                        pacient.setPrenume(rs.getString(3));
                        pacient.setNastere(rs.getDate(4));
                        pacient.setSex(rs.getString(5));
                        pacient.setTelefon(rs.getString(6));
                        pacient.setEmail(rs.getString(7));
                        pacient.setAdresa(rs.getString(8));
                        pacient.setUser(user);
                        return pacient;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Medic getMedic(User user)
    {
        int userIndex = user.getIndex();
        Medic medic = new Medic();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM medici WHERE user = " + userIndex))
                {
                    if(rs.first())
                    {
                        medic.setIndex(rs.getInt(1));
                        medic.setNume(rs.getString(2));
                        medic.setPrenume(rs.getString(3));
                        medic.setSpecializare(SpecializareRepository.getSpecializare(rs.getInt(4)));
                        medic.setNastere(rs.getDate(5));
                        medic.setSex(rs.getString(6));
                        medic.setTelefon(rs.getString(7));
                        medic.setEmail(rs.getString(8));
                        medic.setAdresa(rs.getString(9));
                        medic.setUser(user);
                        return medic;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }
}
