package lv.javaguru.java2.businesslogic.company.updatecompany;

public class UpdateCompanyRequest {
    private Integer id;
    private String regNr;
    private String name;
    private String address;
    private String eMail;
    private String pathFromAccounts;
    private String pathToAccounts;

    public UpdateCompanyRequest(Integer id, String regNr, String name, String address, String eMail, String pathFromAccounts, String pathToAccounts) {
        this.id = id;
        this.regNr = regNr;
        this.name = name;
        this.address = address;
        this.eMail = eMail;
        this.pathFromAccounts = pathFromAccounts;
        this.pathToAccounts = pathToAccounts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegNr() {
        return regNr;
    }

    public void setRegNr(String regNr) {
        this.regNr = regNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPathFromAccounts() {
        return pathFromAccounts;
    }

    public void setPathFromAccounts(String pathFromAccounts) {
        this.pathFromAccounts = pathFromAccounts;
    }

    public String getPathToAccounts() {
        return pathToAccounts;
    }

    public void setPathToAccounts(String pathToAccounts) {
        this.pathToAccounts = pathToAccounts;
    }
}
