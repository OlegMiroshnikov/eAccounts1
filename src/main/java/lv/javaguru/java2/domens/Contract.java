package lv.javaguru.java2.domens;

import java.time.LocalDate;

public class Contract {
    private Integer id;
    private Integer companyId;
    private Integer clientId;
    private String number;
    private LocalDate dateSign;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Integer dayToSendAccount;
    private Integer countDaysToSendReminder;
    private Integer status;

    public Contract() {
    }

    public Contract(Integer companyId, Integer clientId, String number, LocalDate dateSign, LocalDate dateBegin) {
        this.companyId = companyId;
        this.clientId = clientId;
        this.number = number;
        this.dateSign = dateSign;
        this.dateBegin = dateBegin;
    }

    public Contract(Integer companyId, Integer clientId, String number, LocalDate dateSign, LocalDate dateBegin, LocalDate dateEnd, Integer dayToSendAccount, Integer countDaysToSendReminder, Integer status) {
        this.companyId = companyId;
        this.clientId = clientId;
        this.number = number;
        this.dateSign = dateSign;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.dayToSendAccount = dayToSendAccount;
        this.countDaysToSendReminder = countDaysToSendReminder;
        this.status = status;
    }

    public Contract(Integer id, Integer companyId, Integer clientId, String number, LocalDate dateSign, LocalDate dateBegin, LocalDate dateEnd, Integer dayToSendAccount, Integer countDaysToSendReminder, Integer status) {
        this.id = id;
        this.companyId = companyId;
        this.clientId = clientId;
        this.number = number;
        this.dateSign = dateSign;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.dayToSendAccount = dayToSendAccount;
        this.countDaysToSendReminder = countDaysToSendReminder;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDateSign() {
        return dateSign;
    }

    public void setDateSign(LocalDate dateSign) {
        this.dateSign = dateSign;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getDayToSendAccount() {
        return dayToSendAccount;
    }

    public void setDayToSendAccount(Integer dayToSendAccount) {
        this.dayToSendAccount = dayToSendAccount;
    }

    public Integer getCountDaysToSendReminder() {
        return countDaysToSendReminder;
    }

    public void setCountDaysToSendReminder(Integer countDaysToSendReminder) {
        this.countDaysToSendReminder = countDaysToSendReminder;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id != null ? id.equals(contract.id) : contract.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", clientId=" + clientId +
                ", number='" + number + '\'' +
                ", dateSign=" + dateSign +
                ", dateBegin=" + dateBegin +
                ", dateEnd=" + dateEnd +
                ", dayToSendAccount=" + dayToSendAccount +
                ", countDaysToSendReminder=" + countDaysToSendReminder +
                ", status=" + status +
                '}';
    }
}

