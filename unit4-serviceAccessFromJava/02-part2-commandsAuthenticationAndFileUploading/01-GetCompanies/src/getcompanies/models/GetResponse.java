// Adrián Navarro Gabino

package getcompanies.models;

public class GetResponse {
    boolean ok;
    String error;
    Company company;

    public boolean isOk() {
        return ok;
    }

    public String getError() {
        return error;
    }

    public Company getCompany() {
        return company;
    }
}
