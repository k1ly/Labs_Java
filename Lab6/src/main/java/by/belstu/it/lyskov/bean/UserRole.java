package by.belstu.it.lyskov.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserRole implements Serializable {
    public static final String GUEST = "USER_ROLE_GUEST";
    public static final String CLIENT = "USER_ROLE_CLIENT";
    public static final String ADMIN = "USER_ROLE_ADMIN";

    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    public UserRole() {
    }

    public UserRole(int id) {
        this.id = id;
    }

    public UserRole(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return id == userRole.id && Objects.equals(name, userRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}