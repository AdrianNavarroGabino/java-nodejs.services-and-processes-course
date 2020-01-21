package getcompanies.models;

public class EmployeeResponse {

    private boolean ok;
    private String id;
    private String error;

    public boolean isOk() {
        return ok;
    }

    public String getId() {
        return id;
    }

    public String getError() {
        return error;
    }
}
