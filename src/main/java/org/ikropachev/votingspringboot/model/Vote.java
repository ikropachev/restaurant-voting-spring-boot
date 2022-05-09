package org.ikropachev.votingspringboot.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.ikropachev.votingspringboot.HasId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(name = "vote_user_id_created_on_idx", columnNames = {"user_id", "created_on"})})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vote extends BaseEntity implements HasId, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    //@NotNull
    @Column(name = "user_id", nullable = false)
    private int userId;

    //@NotNull
    @Column(name = "restaurant_id", nullable = false)
    private int restaurantId;

    @NotNull
    @Column(name = "created_on", nullable = false, columnDefinition = "date default now()")
    private LocalDate date;

    public Vote(Vote v) {
        this(v.id, v.userId, v.restaurantId, v.date);
    }

    public Vote(Integer id, int userId, int restaurantId, LocalDate date) {
        super(id);
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
    }

    public Vote(int userId, int restaurantId, LocalDate date) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.date = date;
    }
}
