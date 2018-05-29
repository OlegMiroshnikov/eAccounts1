package lv.javaguru.java2.businesslogic.client.addclient;

public class AddClientRequest {

    private String personalCode;
    private String name;
    private String address;
    private String eMail;

    public AddClientRequest(String personalCode, String name, String address, String eMail) {
        this.personalCode = personalCode;
        this.name = name;
        this.address = address;
        this.eMail = eMail;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
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
}
