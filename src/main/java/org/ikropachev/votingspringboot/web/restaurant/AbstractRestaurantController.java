package org.ikropachev.votingspringboot.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Restaurant;
import org.ikropachev.votingspringboot.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Slf4j
public class AbstractRestaurantController {
    public static final String RESTAURANT1_ID_STR = "1";

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(restaurantRepository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        restaurantRepository.deleteExisted(id);
    }
}
