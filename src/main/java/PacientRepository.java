import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacientRepository
{
    public static final int constPacientAddFail = -1;

    public static int addPacient(Pacient pacient)
    {
        String nume = pacient.getNume();;
        String prenume = pacient.getPrenume();
        Date nastere = pacient.getNastere();
        String sex = pacient.getSex();
        String telefon = pacient.getTelefon();
        String email = pacient.getEmail();
        String adresa = pacient.getAdresa();
        int userindex = pacient.getUser().getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO pacienti(nume, prenume, nastere, sex, telefon, email, adresa, user)\n" +
                    "VALUES('" + nume + "', '" + prenume + "', '" + nastere + "', '" + sex + "', '" + telefon + "', '" + email +
                    "', '" + adresa + "', " + userindex + ")", Statement.RETURN_GENERATED_KEYS))
            {
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys())
                {
                    if(rs.first())
                    {
                        int pacientIndex = rs.getInt(1);
                        return pacientIndex;
                    }
                }
            }
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
        return constPacientAddFail;
    }

    public static List<Pacient> getListaPacienti()
    {
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM pacienti"))
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

    public static List<FisaPacient> getLisaFisaPacient(Pacient pacient)
    {
        int indexPacient = pacient.getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT fp.id AS 'fisa', fp.diagnostic, fp.tratament, \n" +
                        "m.id AS 'medic', m.nume, m.prenume, m.nastere, \n" +
                        "m.sex, m.telefon, m.email, m.adresa,  \n" +
                        "s.id AS 'specializare', s.denumire  \n" +
                        "FROM fisepacienti fp\n" +
                        "JOIN medici m\n" +
                        "ON fp.medic = m.id\n" +
                        "JOIN specializari s \n" +
                        "ON m.specializare = s.id \n" +
                        "WHERE fp.pacient =" + indexPacient))
                {
                    List<FisaPacient> listaFisaPacient = new ArrayList<>();

                    while(rs.next())
                    {
                        FisaPacient fisaPacient = new FisaPacient();
                        fisaPacient.setIndex(rs.getInt(1));
                        fisaPacient.setPacient(pacient);
                        fisaPacient.setDiagnostic(rs.getString(2));
                        fisaPacient.setTratament(rs.getString(3));

                        Medic medic = new Medic();
                        medic.setIndex(rs.getInt(4));
                        medic.setNume(rs.getString(5));
                        medic.setPrenume(rs.getString(6));
                        medic.setNastere(rs.getDate(7));
                        medic.setSex(rs.getString(8));
                        medic.setTelefon(rs.getString(9));
                        medic.setEmail(rs.getString(10));
                        medic.setAdresa(rs.getString(11));

                        Specializare specializare = new Specializare();
                        specializare.setIndex(rs.getInt(12));
                        specializare.setDenumire(rs.getString(13));

                        medic.setSpecializare(specializare);
                        fisaPacient.setMedic(medic);

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

    public static List<Programare> getListaProgramariInCurs(Pacient pacient)
    {
        int indexPacient = pacient.getIndex();

        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/cabinetmedical", "root", null))
        {
            try (Statement stmt = conn.createStatement())
            {
                try (ResultSet rs = stmt.executeQuery("SELECT p.id AS 'programare', \n" +
                        " p.data, p.realizata, m.id, m.nume, m.prenume, m.nastere, m.sex, \n" +
                        "m.telefon, m.email, m.adresa, s.id AS 'specializare', s.denumire\n" +
                        "FROM programari p \n" +
                        "JOIN medici m \n" +
                        "ON p.medic = m.id \n" +
                        "JOIN specializari s \n" +
                        "ON m.specializare = s.id \n" +
                        "WHERE p.pacient = 1 =" + indexPacient + "\n" +
                        "AND data >= CURRENT_TIMESTAMP"))
                {
                    List<Programare> listaProgramari = new ArrayList<>();

                    while(rs.next())
                    {
                        Programare programare = new Programare();
                        programare.setIndex(rs.getInt(1));
                        programare.setPacient(pacient);
                        programare.setData(rs.getTimestamp(2));
                        programare.setRealizata(rs.getBoolean(3));

                        Medic medic = new Medic();
                        medic.setIndex(rs.getInt(4));
                        medic.setNume(rs.getString(5));
                        medic.setPrenume(rs.getString(6));
                        medic.setNastere(rs.getDate(7));
                        medic.setSex(rs.getString(8));
                        medic.setTelefon(rs.getString(9));
                        medic.setEmail(rs.getString(10));
                        medic.setAdresa(rs.getString(11));

                        Specializare specializare = new Specializare();
                        specializare.setIndex(rs.getInt(12));
                        specializare.setDenumire(rs.getString(13));

                        medic.setSpecializare(specializare);
                        programare.setMedic(medic);

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

    public static boolean isInformationComplete(Pacient pacient)
    {
        if(pacient == null)
        {
            return false;
        }

        String nume = pacient.getNume();
        String prenume = pacient.getPrenume();
        Date nastere = pacient.getNastere();
        String sex = pacient.getSex();
        String telefon = pacient.getTelefon();
        String email = pacient.getEmail();
        String adresa = pacient.getAdresa();

        return !nume.isEmpty() && !prenume.isEmpty() && nastere != null && !sex.isEmpty() &&
                !telefon.isEmpty() && !email.isEmpty() && !adresa.isEmpty();
    }
}
