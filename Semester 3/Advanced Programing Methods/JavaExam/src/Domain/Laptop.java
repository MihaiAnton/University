package Domain;

import java.util.Objects;

public class Laptop implements IdEntity<String> {

    private String id, prod, model;
    private float price;
    private String type;

    @Override
    public String toString() {
        return "Laptop{" +
                "id='" + id + '\'' +
                ", prod='" + prod + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                '}';
    }

    public Laptop(String id, String prod, String model, float price, String type) {
        this.id = id;
        this.prod = prod;
        this.model = model;
        this.price = price;
        this.type = type;
    }



    @Override
    public int hashCode() {
        return Objects.hash(prod);
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
