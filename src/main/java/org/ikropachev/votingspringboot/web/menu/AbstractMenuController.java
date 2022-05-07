package org.ikropachev.votingspringboot.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Menu;
import org.ikropachev.votingspringboot.repository.MenuRepository;
import org.ikropachev.votingspringboot.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.ikropachev.votingspringboot.util.validation.ValidationUtil.checkNew;

@Slf4j
public class AbstractMenuController {
    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public Menu get(int id) {
        log.info("get menu with id {}", id);
        return menuRepository.getWithDishes(id);
    }

    public List<Menu> getAll() {
        log.info("get all menus");
        return menuRepository.findAll();
    }

    public List<Menu> getAllByDate(LocalDate date) {
        log.info("get all menus by date {}", date);
        return menuRepository.getAllByDate(date);
    }

    public Menu create(Menu menu, Integer restaurantId) {
        log.info("create menu {} for restaurant with id {}", menu, restaurantId);
        checkNew(menu);
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            return null;
        }
        if ((menu.getDate() == null) && !(menu.isNew())) {
            Menu previous = get(menu.getId(), restaurantId);
            menu.setDate(previous.getDate());
        }
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        return menuRepository.save(menu);
    }

    public void delete(Integer id, Integer restaurantId) {
        log.info("delete menu with id {} for restaurant with id {}", id, restaurantId);
        menuRepository.delete(id, restaurantId);
    }

    public void update(Menu menu, Integer restaurantId) {
        log.info("update menu {} for restaurant with id {}", menu, restaurantId);
        menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        menuRepository.save(menu);
    }

    public Menu get(int id, int restaurantId) {
        return menuRepository.findById(id)
                .filter(menu -> menu.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }
}
