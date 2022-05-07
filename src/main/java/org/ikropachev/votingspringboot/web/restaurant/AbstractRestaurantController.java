package org.ikropachev.votingspringboot.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Restaurant;
import org.ikropachev.votingspringboot.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@Slf4j
public class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteExisted(id);
    }
}
