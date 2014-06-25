package cl.experti.persistence.model;

import java.util.Objects;

public abstract class IdEntity extends BaseBean {
    
    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public int hashCode() {
        int result = 0;
        if (this.getId() != null) {
            result = this.getId().hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (o != null && getClass().isAssignableFrom(o.getClass())) {
            IdEntity entity = (IdEntity) o;
            result = Objects.equals(this.getId(), entity.getId());
        }
        return result;
    }
}
