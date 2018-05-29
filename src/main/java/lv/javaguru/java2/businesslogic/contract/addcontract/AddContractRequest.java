package lv.javaguru.java2.businesslogic.contract.addcontract;

import java.util.Date;

public class AddContractRequest {

    private Integer companyId;
    private Integer clientId;
    private String number;
    private Date dateSign;
    private Date dateBegin;
    private Date dateEnd;
    private Integer dayToSendAccount;
    private Integer countDaysToSendReminder;
    private Integer status;

    public AddContractRequest(Integer companyId, Integer clientId, String number, Date dateSign, Date dateBegin, Date dateEnd, Integer dayToSendAccount, Integer countDaysToSendReminder, Integer status) {
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

    public Date getDateSign() {
        return dateSign;
    }

    public void setDateSign(Date dateSign) {
        this.dateSign = dateSign;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
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
}
