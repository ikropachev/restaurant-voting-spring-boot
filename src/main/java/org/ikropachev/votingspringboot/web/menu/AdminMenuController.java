package org.ikropachev.votingspringboot.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import static org.ikropachev.votingspringboot.web.restaurant.AbstractRestaurantController.RESTAURANT1_ID_STR;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Admin menu controller", description = "Operations for menus from admin")
public class AdminMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/admin/restaurants";

    @Override
    @GetMapping("/menus")
    @Operation(summary = "View a list of all menus")
    public List<Menu> getAll() {
        log.info("getAll");
        return super.getAll();
    }

    @Override
    @GetMapping(value = "/menus/by-date")
    @Operation(summary = "View a list of all menus")
    public List<Menu> getAllByDate(@Nullable @RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       @Parameter(description = "null for current date", example = DATE_STR, required = false) LocalDate date) {
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
    @Operation(summary = "Delete a menu by restaurant id and menu id")
    public void delete(@PathVariable @Parameter(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @PathVariable @Parameter(example = MENU1_ID_STR, required = true) Integer menuId) {
        log.info("delete menu with id {} for restaurant with id {}", menuId, restaurantId);
        super.delete(menuId, restaurantId);
    }

    @PostMapping(value = "/{restaurantId}/menus", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a menu")
    public ResponseEntity<Menu> createWithLocation(@Validated(View.Web.class) @RequestBody
                                                       @Parameter(description = "\"restaurant\" field in request body may absent, " +
                                                               "it doesn't use in request.")Menu menu,
                                                   @PathVariable @Parameter(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId) {
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
    @Operation(summary = "Update a menu for a restaurant for a certain date")
    public void update(@Validated(View.Web.class) @RequestBody Menu menu,
                       @PathVariable @Parameter(example = RESTAURANT1_ID_STR, required = true) Integer restaurantId,
                       @PathVariable @Parameter(example = MENU1_ID_STR, required = true) Integer menuId) {
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
