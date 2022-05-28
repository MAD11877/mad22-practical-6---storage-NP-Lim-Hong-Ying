package sg.edu.np.practical6;

public class User {
    private String name;
    private String description;
    private int id;
    private boolean followed;

    public User() {

    }

    public User(String n, String d, int i, boolean f) {
        setName(n);
        setDescription(d);
        setId(i);
        setFollowed(f);
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String s) {
        description = s;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int i) {
        id = i;
    }

    public int getId() {
        return id;
    }

    public void setFollowed(boolean b) {
        followed = b;
    }

    public boolean getFollowed() {
        return followed;
    }
}