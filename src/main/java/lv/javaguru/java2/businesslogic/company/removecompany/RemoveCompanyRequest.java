package lv.javaguru.java2.businesslogic.company.removecompany;

public class RemoveCompanyRequest {

    private Integer id;

    public RemoveCompanyRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
