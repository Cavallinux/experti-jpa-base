package cl.experti.persistence.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GeneratedIdNamedEntity extends GeneratedIdEntity {
    @Column(name = "name", unique = true, length = 64, nullable = false)
    private String name = null;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return this.getName();
    }
}