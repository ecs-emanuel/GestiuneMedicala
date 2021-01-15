package Entities;

public class FisaPacient
{
    private int index;
    private Pacient pacient;
    private Medic medic;
    private String diagnostic;
    private String tratament;

    public FisaPacient()
    {
    }

    public FisaPacient(int index, Pacient pacient, Medic medic, String diagnostic, String tratament)
    {
        this.index = index;
        this.pacient = pacient;
        this.medic = medic;
        this.diagnostic = diagnostic;
        this.tratament = tratament;
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

    public String getDiagnostic()
    {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic)
    {
        this.diagnostic = diagnostic;
    }

    public String getTratament()
    {
        return tratament;
    }

    public void setTratament(String tratament)
    {
        this.tratament = tratament;
    }
}
