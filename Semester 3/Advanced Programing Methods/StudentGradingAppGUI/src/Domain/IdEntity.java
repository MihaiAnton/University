package Domain;

public interface IdEntity<ID> {

    /**
     *  Id based interface
     *  @param <ID> defines the derived class unique identification key
     */
    void setId(ID id);
    ID getId();
}
