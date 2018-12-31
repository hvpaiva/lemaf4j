package br.ufla.lemaf.ti.lemaf4j.helpers;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "UUID_PARENT")
public class UUIDParentEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "UUID", nullable = true)
    private UUID uuid;

    public UUIDParentEntity() {
        super();
    }

    public UUIDParentEntity(long id) {
        super();
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getUUID() {
        return uuid;
    }

    public void setUUID(UUID uuid) {
        this.uuid = uuid;
    }

}