package lv.javaguru.java2.businesslogic.account.removeaccount;

public class RemoveAccountRequest {

    private Long id;

    public RemoveAccountRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
