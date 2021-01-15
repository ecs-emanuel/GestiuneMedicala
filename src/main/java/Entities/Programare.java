package Entities;

import java.sql.Timestamp;

public class Programare
{
    private int index;
    private Pacient pacient;
    private Medic medic;
    private Timestamp data;
    private boolean realizata;

    public Programare()
    {
    }

    public Programare(int index, Pacient pacient, Medic medic, Timestamp data, boolean realizata)
    {
        this.index = index;
        this.pacient = pacient;
        this.medic = medic;
        this.data = data;
        this.realizata = realizata;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public Pacient getPacient()
    {
        return pacient;
    }

    public void setPacient(Pacient pacient)
    {
        this.pacient = pacient;
    }

    public Medic getMedic()
    {
        return medic;
    }

    public void setMedic(Medic medic)
    {
        this.medic = medic;
    }

    public Timestamp getData()
    {
        return data;
    }

    public void setData(Timestamp data)
    {
        this.data = data;
    }

    public boolean isRealizata()
    {
        return realizata;
    }

    public void setRealizata(boolean realizat)
    {
        this.realizata = realizat;
    }
}