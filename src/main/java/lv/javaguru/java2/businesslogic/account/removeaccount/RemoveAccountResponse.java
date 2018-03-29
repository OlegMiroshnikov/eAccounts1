package lv.javaguru.java2.businesslogic.account.removeaccount;

import lv.javaguru.java2.validators.Error;

import java.util.List;

public class RemoveAccountResponse {
    private boolean success;
    private List<Error> errors;

    public RemoveAccountResponse(boolean success, List<Error> errors) {
        this.success = success;
        this.errors = errors;
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
