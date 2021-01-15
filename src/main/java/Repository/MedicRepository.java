package Repository;

import Entities.Pacient;
import Entities.Medic;
import Entities.Specializare;
import Entities.Programare;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MedicRepository
{
    public static final int constMedicAddFail = -1;

    public static int addMedic(Medic medic)
    {
        String nume = medic.getNume();;
        String prenume = medic.getPrenume();
        int specializareIndex = medic.getSpecializare().getIndex();
        Date nastere = medic.getNastere();
        String sex = medic.getSex();
        String telefon = medic.getTelefon();
        String email = medic.getEmail();
        String adresa = medic.getAdresa();
        int userindex = medic.getUser().getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO medici(nume, prenume, specializare, nastere, sex, telefon, email, adresa, user)\n" +
                    "VALUES('" + nume + "', '" + prenume + "', " + specializareIndex + ", '" + nastere + "', '" + sex +
                    "', '" + telefon + "', '" + email + "', '" + adresa + "', " + userindex + ")", Statement.RETURN_GENERATED_KEYS))
            {
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if(rs.first())
                    {
                        int medicIndex = rs.getInt(1);
                        return medicIndex;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return constMedicAddFail;
    }

    public static List<Pacient> getListaPacienti(Medic medic)
    {
        int indexMedic = medic.getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT p.id, p.nume, p.prenume, p.nastere, p.sex, p.telefon, p.email, p.adresa FROM pacienti p \n" +
                        "JOIN fisepacienti f\n" +
                        "ON f.pacient = p.id \n" +
                        "JOIN medici m \n" +
                        "ON f.medic = m.id\n" +
                        "WHERE m.id = " + indexMedic + "\n" +
                        "GROUP BY p.id"))
                {
                    List<Pacient> listaPacienti = new ArrayList<>();

                    while(rs.next())
                    {
                        Pacient pacient = new Pacient();
                        pacient.setIndex(rs.getInt(1));
                        pacient.setNume(rs.getString(2));
                        pacient.setPrenume(rs.getString(3));
                        pacient.setNastere(rs.getDate(4));
                        pacient.setSex(rs.getString(5));
                        pacient.setTelefon(rs.getString(6));
                        pacient.setEmail(rs.getString(7));
                        pacient.setAdresa(rs.getString(8));

                        listaPacienti.add(pacient);
                    }
                    return listaPacienti;
                }
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Medic> getListaMedici()
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT m.id AS 'medic', m.nume, m.prenume, \n" +
                        "m.nastere, m.sex, m.telefon, m.email, m.adresa, s.id AS 'specializare', s.denumire\n" +
                        "FROM medici m \n" +
                        "JOIN specializari s \n" +
                        "ON m.specializare = s.id"))
                {
                    List<Medic> listaMedici = new ArrayList<>();

                    while(rs.next())
                    {
                        Medic medic = new Medic();
                        medic.setIndex(rs.getInt(1));
                        medic.setNume(rs.getString(2));
                        medic.setPrenume(rs.getString(3));
                        medic.setNastere(rs.getDate(4));
                        medic.setSex(rs.getString(5));
                        medic.setTelefon(rs.getString(6));
                        medic.setEmail(rs.getString(7));
                        medic.setAdresa(rs.getString(8));

                        Specializare specializare = new Specializare();
                        specializare.setIndex(rs.getInt(9));
                        specializare.setDenumire(rs.getString(10));

                        medic.setSpecializare(specializare);

                        listaMedici.add(medic);
                    }
                    return listaMedici;
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static List<Programare> getListaProgramariLibere(Medic medic, Date date)
    {
        List<Programare> listaProgramari = getListaProgramariInCurs(medic, date);
        List<Programare> listaProgramariLibere = new ArrayList<>();

        int programareDurataMins = 30;
        int ziMins = 60 * 24;

        int maxEntries = ziMins / programareDurataMins;

        for (int i = 0; i < maxEntries; i++)
        {
            LocalTime ora = LocalTime.of(0,0);
            ora = ora.plusMinutes(programareDurataMins * i);

            String sdate = date.toString() + " " + ora + ":00";
            Timestamp timestamp = Timestamp.valueOf(sdate);

            boolean busy = false;

            for (int j = 0; j < listaProgramari.size(); j++)
            {
                Programare programare = listaProgramari.get(j);
                Timestamp data = programare.getData();

                if (timestamp.compareTo(data) == 0)
                {
                    busy = true;
                    break;
                }
            }

            if(!busy)
            {
                Programare programareLibera = new Programare();
                programareLibera.setMedic(medic);
                programareLibera.setData(timestamp);
                programareLibera.setRealizata(false);

                listaProgramariLibere.add(programareLibera);
            }
        }
        return listaProgramariLibere;
    }

    public static List<Programare> getListaProgramariInCurs(Medic medic, Date date)
    {
        int indexMedic = medic.getIndex();
        String dayBegin = date + " " + "00:00:00";
        String dayEnd = date + " " + "23:59:59";

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT pr.id AS 'indexProgramare', pr.data, pr.realizata,\n" +
                        "pa.id AS 'indexPacient', pa.nume, pa.prenume, pa.nastere, pa.sex, pa.telefon, pa.email, pa.adresa\n" +
                        "FROM programari pr\n" +
                        "JOIN pacienti pa \n" +
                        "ON pr.pacient = pa.id\n" +
                        "WHERE medic = " + indexMedic + "\n" +
                        "AND data >= '" + dayBegin + "' AND data <= '" + dayEnd + "'\n" +
                        "ORDER BY data"))
                {
                    List<Programare> listaProgramari = new ArrayList<>();

                    while(rs.next())
                    {
                        Programare programare = new Programare();
                        programare.setIndex(rs.getInt(1));
                        programare.setData(rs.getTimestamp(2));
                        programare.setRealizata(rs.getBoolean(3));

                        Pacient pacient = new Pacient();
                        pacient.setIndex(rs.getInt(4));
                        pacient.setNume(rs.getString(5));
                        pacient.setPrenume(rs.getString(6));
                        pacient.setNastere(rs.getDate(7));
                        pacient.setSex(rs.getString(8));
                        pacient.setTelefon(rs.getString(9));
                        pacient.setEmail(rs.getString(10));
                        pacient.setAdresa(rs.getString(11));

                        programare.setMedic(medic);
                        programare.setPacient(pacient);
                        listaProgramari.add(programare);
                    }
                    return listaProgramari;
                }
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return null;
    }

    public static boolean isInformationComplete(Medic medic)
    {
        if(medic == null)
        {
            return false;
        }

        String nume = medic.getNume();
        String prenume = medic.getPrenume();
        int specializareIndex = medic.getSpecializare().getIndex();
        Date nastere = medic.getNastere();
        String sex = medic.getSex();
        String telefon = medic.getTelefon();
        String email = medic.getEmail();
        String adresa = medic.getAdresa();

        return !nume.isEmpty() && !prenume.isEmpty() && specializareIndex > 0 && nastere != null && !sex.isEmpty() &&
                !telefon.isEmpty() && !email.isEmpty() && !adresa.isEmpty();
    }
}
