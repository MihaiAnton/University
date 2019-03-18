package Domain;



public class Credentials implements IdEntity<Integer> {

    private int userId;
    private String password;

    public Credentials(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Integer getId() {
        return userId;
    }

    @Override
    public void setId(Integer integer) {
        this.userId = integer;
    }
}
