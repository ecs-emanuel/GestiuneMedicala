import java.sql.Date;

public class Pacient
{
    public static final String constSexMasculin = "Masculin";
    public static final String constSexFeminin = "Feminin";

    private int index;
    private String nume;
    private String prenume;
    private Date nastere;
    private String sex;
    private String telefon;
    private String email;
    private String adresa;
    private User user;

    public Pacient()
    {
    }

    public Pacient(int index, String nume, String prenume, Date nastere, String sex, String telefon, String email, String adresa)
    {
        this.index = index;
        this.nume = nume;
        this.prenume = prenume;
        this.nastere = nastere;
        this.sex = sex;
        this.telefon = telefon;
        this.email = email;
        this.adresa = adresa;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getNume()
    {
        return nume;
    }

    public void setNume(String nume)
    {
        this.nume = nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public void setPrenume(String prenume)
    {
        this.prenume = prenume;
    }

    public Date getNastere()
    {
        return nastere;
    }

    public void setNastere(Date nastere)
    {
        this.nastere = nastere;
    }

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getTelefon()
    {
        return telefon;
    }

    public void setTelefon(String telefon)
    {
        this.telefon = telefon;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAdresa()
    {
        return adresa;
    }

    public void setAdresa(String adresa)
    {
        this.adresa = adresa;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}



