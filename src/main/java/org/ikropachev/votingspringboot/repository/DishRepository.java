package org.ikropachev.votingspringboot.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.ikropachev.votingspringboot.model.Dish;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.menu.id=:id")
    int delete(@Param("id") int id);
}
