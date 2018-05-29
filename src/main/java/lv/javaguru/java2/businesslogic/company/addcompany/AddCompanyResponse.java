package lv.javaguru.java2.businesslogic.company.addcompany;

import lv.javaguru.java2.validators.Error;

import java.util.List;

public class AddCompanyResponse {

    private Integer id;
    private boolean success;
    private List<Error> errors;

    public AddCompanyResponse(Integer id) {
        this.id = id;
        this.success = true;
        this.errors = null;
    }
    public AddCompanyResponse(List<Error> errors) {
        this.id = null;
        this.success = false;
        this.errors = errors;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}

