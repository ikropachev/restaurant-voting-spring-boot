package org.ikropachev.votingspringboot.web.menu;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.View;
import org.ikropachev.votingspringboot.model.Dish;
import org.ikropachev.votingspringboot.model.Menu;
import org.ikropachev.votingspringboot.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/admin/restaurants";

    @Override
    @GetMapping("/menus")
    public List<Menu> getAll() {
        log.info("getAll");
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/menus/by-date")
    public List<Menu> getAllByDate(@Nullable @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get all menus by date {}", date);
        if (date == null) {
            date = LocalDate.now();
            log.info("set date {}", date);
        }
        return super.getAllByDate(date);
    }

    @Override
    @DeleteMapping("/{restaurantId}/menus/{menuId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer restaurantId,
                       @PathVariable Integer menuId) {
        log.info("delete menu with id {} for restaurant with id {}", menuId, restaurantId);
        super.delete(menuId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Validated(View.Web.class) @RequestBody Menu menu,
                                                   @PathVariable Integer restaurantId) {
        log.info("create {} for restaurant {}", menu, restaurantId);

        //Fix bug with lost dishes if dish_id not null (dishes always new)
        List<Dish> dishes = menu.getDishes();
        if (dishes != null) {
            dishes.forEach(dish -> dish.setId(null));
        }
        if (menu.getDate() == null) {
            menu.setDate(LocalDate.now());
            log.info("set date {} for menu", menu.getDate());
        }
        Menu created = super.create(menu, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/menus/{menuId}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{restaurantId}/menus/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Validated(View.Web.class) @RequestBody Menu menu,
                       @PathVariable Integer restaurantId,
                       @PathVariable Integer menuId) {
        log.info("update menu {} for restaurant {}", menu, restaurantId);
        menu.setId(menuId);

        //Fix bug with lost dishes if dish_id not null (dishes always new, previous will delete)
        List<Dish> dishes = menu.getDishes();
        if (dishes != null) {
            dishes.forEach(dish -> dish.setId(null));
        }
        super.update(menu, restaurantId);
    }
}
