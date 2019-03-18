import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DogRepo implements Serializable {

    private List<Dog> list;

    public  DogRepo(){
        list = new ArrayList<>();
    }

    public DogRepo(String s, String s2) {
    }

    public void addDog(Dog d){
        this.list.add(d);
    }

    public List<Dog> getList(){
        return this.list;
    }

    public void removeDog(Dog d){
        this.list.remove(d);
    }
}
