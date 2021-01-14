public class User
{
    public static final String constUserAccessPacient = "Pacient";
    public static final String constUserAccessMedic = "Medic";

    private int index;
    private String username;
    private String password;
    private String access;

    public User()
    {
    }

    public User(int index, String username, String password, String access)
    {
        this.index = index;
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAccess()
    {
        return access;
    }

    public void setAccess(String access)
    {
        this.access = access;
    }
}
