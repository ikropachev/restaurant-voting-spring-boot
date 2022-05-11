package org.ikropachev.votingspringboot.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "menu", uniqueConstraints = {@UniqueConstraint(name = "menu_restaurant_id_created_on_idx", columnNames = {"restaurant_id", "created_on"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu extends BaseEntity {
    private static final String DISH_LIST_STR = "[\n{\n\"id\": \"null\",\n\"name\": \"dish1\",\n\"price\": 10\n},\n" +
            "    {\n\"id\": \"null\",\n\"name\": \"dish2\",\n\"price\": 20\n}\n]";

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "created_on", columnDefinition = "date default now()")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "null")
    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    @Schema(example = DISH_LIST_STR)
    private List<Dish> dishes;

    public Menu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dishes) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        this.dishes = dishes;
    }

    //Constructor for tests with ignoring fields
    public Menu(Integer id, LocalDate date) {
        super(id);
        this.restaurant = null;
        this.date = date;
        this.dishes = null;
    }
}
