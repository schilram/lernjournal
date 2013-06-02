package ch.zhaw.schilram.lernjournal.domain;

import java.io.Serializable;

/**
 * A simple POJO representing a Person.
 */
public class Person implements Serializable {

    private static final long serialVersionUID = -5527566248002296042L;

    private Integer id;
    private String firstName;
    private String lastName;
    private Integer money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

}
