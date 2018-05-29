package lv.javaguru.java2.businesslogic.contract.removecontract;

public class RemoveContractRequest {

    private Integer id;

    public RemoveContractRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
