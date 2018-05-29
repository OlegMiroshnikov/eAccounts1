package lv.javaguru.java2.businesslogic.account.addaccount;


import java.util.Date;

public class AddAccountRequest {

    private Long id;
    private Integer contractId;
    private String fileName;
    private Date dateSending;
    private Date dateViewing;
    private Date dateReminderSending;

    public AddAccountRequest(Integer contractId, String fileName) {
        this.contractId = contractId;
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getDateSending() {
        return dateSending;
    }

    public void setDateSending(Date dateSending) {
        this.dateSending = dateSending;
    }

    public Date getDateViewing() {
        return dateViewing;
    }

    public void setDateViewing(Date dateViewing) {
        this.dateViewing = dateViewing;
    }

    public Date getDateReminderSending() {
        return dateReminderSending;
    }

    public void setDateReminderSending(Date dateReminderSending) {
        this.dateReminderSending = dateReminderSending;
    }
}
