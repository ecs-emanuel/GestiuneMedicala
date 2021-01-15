package Repository;

import Entities.Pacient;
import Entities.Medic;
import Entities.FisaPacient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FisaPacientRepository
{
    public static boolean addFisaPacient(FisaPacient fisaPacient)
    {
        int indexPacient = fisaPacient.getPacient().getIndex();
        int indexMedic = fisaPacient.getMedic().getIndex();
        String diagnostic = fisaPacient.getDiagnostic();
        String tratament = fisaPacient.getTratament();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("INSERT INTO fisepacienti(pacient, medic, diagnostic, tratament)\n" +
                        "VALUES(" + indexPacient + ", " + indexMedic + ", '" + diagnostic + "', '" + tratament + "')"))
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

    public static boolean modificaFisaPacient(FisaPacient fisaPacient)
    {
        int indexFisa = fisaPacient.getIndex();
        String diagnostic = fisaPacient.getDiagnostic();
        String tratament = fisaPacient.getTratament();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("UPDATE fisepacienti SET \n" +
                        "diagnostic = '" + diagnostic + "',\n" +
                        "tratament = '" + tratament + "'\n" +
                        "WHERE id = " + indexFisa))
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

    public static List<FisaPacient> getLisaFisaPacientMedic(Pacient pacient, Medic medic)
    {
        int indexPacient = pacient.getIndex();
        int indexMedic = medic.getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT f.id, f.diagnostic, f.tratament\n" +
                        "FROM fisepacienti f\n" +
                        "WHERE f.pacient = " + indexPacient + " \n" +
                        "AND f.medic = " + indexMedic))
                {
                    List<FisaPacient> listaFisaPacient = new ArrayList<>();

                    while(rs.next())
                    {
                        FisaPacient fisaPacient = new FisaPacient();
                        fisaPacient.setIndex(rs.getInt(1));
                        fisaPacient.setPacient(pacient);
                        fisaPacient.setMedic(medic);
                        fisaPacient.setDiagnostic(rs.getString(2));
                        fisaPacient.setTratament(rs.getString(3));
                        listaFisaPacient.add(fisaPacient);
                    }
                    return listaFisaPacient;
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean isInformationComplete(FisaPacient fisaPacient)
    {
        int indexPacient = fisaPacient.getPacient().getIndex();
        int indexMedic = fisaPacient.getMedic().getIndex();
        String diagnostic = fisaPacient.getDiagnostic();
        String tratament = fisaPacient.getTratament();

        return indexPacient >= 1 && indexMedic >= 1 && !diagnostic.isEmpty() && !tratament.isEmpty();
    }
}
