package lv.javaguru.java2.domens;

public class Client {
    Integer id;
    String personalCode;
    String name;
    String address;
    String eMail;

    public Client() {
    }

    public Client(String personalCode, String name, String address, String eMail) {
        this.personalCode = personalCode;
        this.name = name;
        this.address = address;
        this.eMail = eMail;
    }

    public Client(Integer id, String personalCode, String name, String address, String eMail) {
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

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", personalCode='" + personalCode + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", eMail='" + eMail + '\'' +
                '}';
    }
}
