import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecializareRepository
{
    public static Specializare getSpecializare(int specializareIndex)
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM specializari WHERE id = " + specializareIndex))
                {
                    if (rs.first())
                    {
                        Specializare specializare = new Specializare();
                        specializare.setIndex(specializareIndex);
                        specializare.setDenumire(rs.getString(2));
                        return specializare;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Specializare> getListaSpecializari()
    {
        List<Specializare> listaSpecializari = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM specializari"))
                {
                    while (rs.next())
                    {
                        Specializare specializare = new Specializare();
                        specializare.setIndex(rs.getInt(1));
                        specializare.setDenumire(rs.getString(2));
                        listaSpecializari.add(specializare);
                    }
                    return listaSpecializari;
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }
}
