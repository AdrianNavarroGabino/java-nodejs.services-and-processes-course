// Adrián Navarro Gabino

package getcompanies.models;

public class CompanyResponse {
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
