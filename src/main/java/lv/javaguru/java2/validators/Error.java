package lv.javaguru.java2.validators;

public class Error {
    private String field;
    private String errorMessage;

    public Error(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "Error{" +
                "field='" + field + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}


