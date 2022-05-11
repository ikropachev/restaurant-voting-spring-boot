package org.ikropachev.votingspringboot.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.ikropachev.votingspringboot.model.Menu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
@Tag(name = "Menu Controller")
public interface MenuRepository extends BaseRepository<Menu> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Menu m WHERE m.id=:id AND m.restaurant.id=:restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Transactional
    @Query("DELETE FROM Menu m WHERE m.restaurant.id=:restaurantId")
    int delete(@Param("restaurantId") int restaurantId);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.date=:date ORDER BY m.restaurant.name")
    List<Menu> getAllByDate(@Param("date") LocalDate date);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m WHERE m.id=?1")
    Menu getWithDishes(int id);

    @EntityGraph(attributePaths = {"dishes"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM Menu m")
    List<Menu> findAll();

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id=:restaurantId")
    List<Menu> findAllByRestaurantId(@Param("restaurantId") int restaurantId);
}
