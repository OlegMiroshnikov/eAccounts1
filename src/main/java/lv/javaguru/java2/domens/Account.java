package lv.javaguru.java2.domens;

import java.time.LocalDate;

public class Account {
    private Long id;
    private Integer contractId;
    private String fileName;
    private LocalDate dateSending;
    private LocalDate dateViewing;
    private LocalDate dateReminderSending;

    public Account() {
    }

    public Account(Integer contractId, String fileName) {
        this.contractId = contractId;
        this.fileName = fileName;
    }

    public Account(Long id, Integer contractId, String fileName) {
        this.id = id;
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

    public LocalDate getDateSending() {
        return dateSending;
    }

    public void setDateSending(LocalDate dateSending) {
        this.dateSending = dateSending;
    }

    public LocalDate getDateViewing() {
        return dateViewing;
    }

    public void setDateViewing(LocalDate dateViewing) {
        this.dateViewing = dateViewing;
    }

    public LocalDate getDateReminderSending() {
        return dateReminderSending;
    }

    public void setDateReminderSending(LocalDate dateReminderSending) {
        this.dateReminderSending = dateReminderSending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id != null ? id.equals(account.id) : account.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", fileName='" + fileName + '\'' +
                ", dateSending=" + dateSending +
                ", dateViewing=" + dateViewing +
                ", dateReminderSending=" + dateReminderSending +
                '}';
    }
}
