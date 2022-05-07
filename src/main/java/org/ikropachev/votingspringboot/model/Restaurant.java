package org.ikropachev.votingspringboot.model;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.ikropachev.votingspringboot.HasId;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "restaurants")
@Getter
@Setter
//@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant extends NamedEntity implements HasId, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public Restaurant(Restaurant r) {
        this(r.id, r.name);
    }
}
