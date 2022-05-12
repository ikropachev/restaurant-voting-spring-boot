package org.ikropachev.votingspringboot.repository;

import org.ikropachev.votingspringboot.model.Dish;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {
}
