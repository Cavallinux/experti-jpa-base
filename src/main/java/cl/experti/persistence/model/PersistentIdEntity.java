package cl.experti.persistence.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PersistentIdEntity extends IdEntity {

    private static final long serialVersionUID = 2202015144287538980L;
    @Id
    @Column(name = "id")
    private Long id = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
