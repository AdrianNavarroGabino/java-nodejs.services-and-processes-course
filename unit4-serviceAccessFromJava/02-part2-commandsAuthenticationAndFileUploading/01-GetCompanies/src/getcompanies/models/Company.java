// Adrián Navarro Gabino

package getcompanies.models;

import java.util.List;

public class Company {

    private String _id;
    private String cif;
    private String name;
    private String address;
    private List<Employee> employees;

    public Company()
    {

    }

    public Company(String _id, String cif, String name, String address, List<Employee> employees) {
        this._id = _id;
        this.cif = cif;
        this.name = name;
        this.address = address;
        this.employees = employees;
    }

    public String get_id() {
        return _id;
    }

    public String getCif() {
        return cif;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "CIF: " + cif + ", Name: " + name + " (" + address + ")";
    }
}
