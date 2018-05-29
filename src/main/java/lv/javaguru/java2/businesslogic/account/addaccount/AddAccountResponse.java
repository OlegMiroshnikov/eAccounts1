package lv.javaguru.java2.businesslogic.account.addaccount;

import lv.javaguru.java2.validators.Error;

import java.util.List;

public class AddAccountResponse {

    private Long id;
    private boolean success;
    private List<Error> errors;

    public AddAccountResponse(Long id) {
        this.id = id;
        this.success = true;
        this.errors = null;
    }

    public AddAccountResponse(List<Error> errors) {
        this.id = null;
        this.success = false;
        this.errors = errors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
