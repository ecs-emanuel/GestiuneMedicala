public class Specializare
{
    private int index;
    private String denumire;

    public Specializare()
    {
    }

    public Specializare(String denumire)
    {
        this.denumire = denumire;
    }

    public Specializare(int index, String denumire)
    {
        this.index = index;
        this.denumire = denumire;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index= index;
    }

    public String getDenumire()
    {
        return denumire;
    }

    public void setDenumire(String denumire)
    {
        this.denumire = denumire;
    }
}
