package lv.javaguru.java2.businesslogic.client.updateclient;

public class UpdateClientRequest {
    private Integer id;
    private String personalCode;
    private String name;
    private String address;
    private String eMail;

    public UpdateClientRequest(Integer id, String personalCode, String name, String address, String eMail) {
        this.id = id;
        this.personalCode = personalCode;
        this.name = name;
        this.address = address;
        this.eMail = eMail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

}
