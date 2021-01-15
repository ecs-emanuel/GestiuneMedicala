package Entities;

import java.sql.Date;

public class Medic
{
    public static final int constSexMasculin = 0;
    public static final int constSexFeminin = 1;

    private int index;
    private String nume;
    private String prenume;
    private Specializare specializare;
    private Date nastere;
    private String sex;
    private String telefon;
    private String email;
    private String adresa;
    private User user;

    public Medic()
    {
    }

    public Medic(int index, String nume, String prenume, Specializare specializare, Date nastere, String sex, String telefon, String email, String adresa, User user)
    {
        this.index = index;
        this.nume = nume;
        this.prenume = prenume;
        this.specializare = specializare;
        this.nastere = nastere;
        this.sex = sex;
        this.telefon = telefon;
        this.email = email;
        this.adresa = adresa;
        this.user = user;
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

    public Specializare getSpecializare()
    {
        return specializare;
    }

    public void setSpecializare(Specializare specializare)
    {
        this.specializare = specializare;
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
