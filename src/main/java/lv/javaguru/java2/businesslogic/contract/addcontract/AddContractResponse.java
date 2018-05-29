package lv.javaguru.java2.businesslogic.contract.addcontract;

import lv.javaguru.java2.validators.Error;

import java.util.List;

public class AddContractResponse {

    private Integer id;
    private boolean success;
    private List<Error> errors;

    public AddContractResponse(Integer id, boolean success, List<Error> errors) {
        this.id = id;
        this.success = success;
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
