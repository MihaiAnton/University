package Domain;

import java.sql.Date;

public class Sale implements IdEntity<Integer> {

    private Integer id;
    private Long clientId;
    private String laptopId;
    private String date;

    public Sale(Integer id, Long clientId, String laptopId, String date) {
        this.id = id;
        this.clientId = clientId;
        this.laptopId = laptopId;
        this.date = date;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getLaptopId() {
        return laptopId;
    }

    public void setLaptopId(String laptopId) {
        this.laptopId = laptopId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public void setId(Integer integer) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return this.id;
    }
}
