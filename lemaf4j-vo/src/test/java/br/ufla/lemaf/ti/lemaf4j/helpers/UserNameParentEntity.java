package br.ufla.lemaf.ti.lemaf4j.helpers;

import br.ufla.lemaf.ti.lemaf4j.vo.UserName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "USER_NAME_PARENT")
public class UserNameParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "USERNAME", nullable = true)
    private UserName userName;

    public UserNameParentEntity() {
        super();
    }

    public UserNameParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserName getUserName() {
        return userName;
    }

    public void setUserName(UserName userName) {
        this.userName = userName;
    }

}