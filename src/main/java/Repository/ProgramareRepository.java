package Repository;

import Entities.Programare;

import java.sql.*;

public class ProgramareRepository
{
    public static final int CONST_ADDPROGRAMARE_FAIL = -1;

    public static int adaugaProgramare(Programare programare)
    {
        int pacientIndex = programare.getPacient().getIndex();
        int medicIndex = programare.getMedic().getIndex();
        Timestamp data = programare.getData();
        boolean realizata = programare.isRealizata();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO\n" +
                    "programari(pacient, medic, data, realizata) VALUES \n" +
                    "(" + pacientIndex + ", " + medicIndex + ", '" + data + "', " + realizata +")", Statement.RETURN_GENERATED_KEYS))
            {
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if(rs.first())
                    {
                        int programareIndex = rs.getInt(1);
                        return programareIndex;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return CONST_ADDPROGRAMARE_FAIL;
    }


    public static boolean modificaProgramare(Programare programare)
    {
        int indexProgramare = programare.getIndex();
        Timestamp data = programare.getData();
        boolean realizata = programare.isRealizata();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("UPDATE programari SET \n" +
                        "data = '" + data + "',\n" +
                        "realizata = " + realizata + "\n" +
                        "WHERE id = " + indexProgramare))
                {
                    return true;
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean isInformationComplete(Programare programare)
    {
        if (programare == null)
        {
            return false;
        }

        int indexPacient = programare.getPacient().getIndex();
        int indexMedic = programare.getMedic().getIndex();
        Timestamp data = programare.getData();
        boolean realizata = programare.isRealizata();

        return indexPacient > 0 && indexMedic > 0 && data != null && !realizata;
    }
}
