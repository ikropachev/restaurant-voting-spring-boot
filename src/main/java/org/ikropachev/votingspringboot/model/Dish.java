package org.ikropachev.votingspringboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ikropachev.votingspringboot.HasId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "menu_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity implements HasId, Serializable {

    //@NotNull
    @Column(name = "price", nullable = false)
    @Schema(example = "10")
    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonBackReference
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Menu menu;

    public Dish(Integer id, String name, int price) {
        super(id, name);
        this.price = price;
        this.menu = null;
    }
}
