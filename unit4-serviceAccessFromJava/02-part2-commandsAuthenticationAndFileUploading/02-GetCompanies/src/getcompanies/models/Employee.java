// Adrián Navarro Gabino

package getcompanies.models;

public class Employee {
    private String _id;
    private String nif;
    private String name;
    private int age;

    public Employee(String nif, String name, int age) {
        this.nif = nif;
        this.name = name;
        this.age = age;
    }

    public String get_id() {
        return _id;
    }

    public String getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "NIF: " + nif + ", Name: " + name + " (age -> " + age + ")";
    }
}
