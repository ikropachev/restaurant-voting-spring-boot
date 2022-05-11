package org.ikropachev.votingspringboot.repository;

import org.ikropachev.votingspringboot.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaMenuRepository {

    @Autowired
    private final MenuRepository menuRepository;

    private final DishRepository dishRepository;

    public DataJpaMenuRepository(MenuRepository menuRepository, DishRepository dishRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
    }

    public boolean delete(int id, int restaurantId) {
        return menuRepository.delete(id, restaurantId) != 0;
    }

    public boolean delete(int restaurantId) {
        List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);
        for(Menu menu : menus){
            dishRepository.delete(menu.getId());
        }
        return menuRepository.delete(restaurantId) != 0;
    }
}
