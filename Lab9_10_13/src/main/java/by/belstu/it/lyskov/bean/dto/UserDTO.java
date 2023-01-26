package by.belstu.it.lyskov.bean.dto;

import by.belstu.it.lyskov.bean.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private int id;

    private String name;

    private String roleName;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String roleName) {
        this.id = id;
        this.name = name;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public static UserDTO convert(User user) {
        return new UserDTO(user.getId(), user.getName(),
                user.getRole() != null ? user.getRole().getName() : null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id && Objects.equals(name, userDTO.name) && Objects.equals(roleName, userDTO.roleName) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, roleName);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
