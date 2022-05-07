package org.ikropachev.votingspringboot.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.ikropachev.votingspringboot.model.Restaurant;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Tag(name = "Restaurant Controller")
public interface RestaurantRepository extends BaseRepository<Restaurant>{
}
