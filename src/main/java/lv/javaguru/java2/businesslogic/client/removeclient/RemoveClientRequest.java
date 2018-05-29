package lv.javaguru.java2.businesslogic.client.removeclient;

public class RemoveClientRequest {

    private Integer id;

    public RemoveClientRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}