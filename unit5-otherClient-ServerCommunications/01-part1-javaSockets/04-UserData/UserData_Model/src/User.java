// Adrián Navarro Gabino

import java.io.Serializable;
import java.time.LocalDate;

public class User implements Serializable {
    String login;
    String password;
    LocalDate registrationDate;

    public User()
    {
        login = "";
        password = "";
        registrationDate = LocalDate.now();
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public LocalDate getRegistrationDate()
    {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate)
    {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString()
    {
        return "login=" + login + ", password=" + password +
                ", registrationDate=" + registrationDate;
    }
}
