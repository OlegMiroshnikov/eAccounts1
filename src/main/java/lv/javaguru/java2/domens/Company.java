package lv.javaguru.java2.domens;

public class Company {
    private Integer id;
    private String regNr;
    private String name;
    private String address;
    private String eMail;
    private String pathFromAccounts;
    private String pathToAccounts;

    public Company() {
    }

    public Company(String regNr, String name, String address, String eMail, String pathFromAccounts, String pathToAccounts) {
        this.regNr = regNr;
        this.name = name;
        this.address = address;
        this.eMail = eMail;
        this.pathFromAccounts = pathFromAccounts;
        this.pathToAccounts = pathToAccounts;
    }

    public Company(Integer id, String regNr, String name, String address, String eMail, String pathFromAccounts, String pathToAccounts) {
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

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id != null ? id.equals(company.id) : company.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "company{" +
                "id=" + id +
                ", regNr='" + regNr + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                ", pathFromAccounts='" + pathFromAccounts + '\'' +
                ", pathToAccounts='" + pathToAccounts + '\'' +
                '}';
    }
}
