package hibernate.bean;

import entity.Verificate;
import util.StringUtil;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/27.
 */
@Entity
public class User implements Verificate, Serializable {
    private int id;
    private String name;
    private String password;
    private String innetAddress;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 256)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 6)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "innetAddress", nullable = true, length = 256)
    public String getInnetAddress() {
        return innetAddress;
    }

    public void setInnetAddress(String innetAddress) {
        this.innetAddress = innetAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (innetAddress != null ? !innetAddress.equals(user.innetAddress) : user.innetAddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (innetAddress != null ? innetAddress.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "name:" + name + "\n password:" + password;
    }

    @Override
    public boolean verificate() {
        boolean useful = true;
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password)) {
            useful = false;
        }
        return useful;
    }
}
