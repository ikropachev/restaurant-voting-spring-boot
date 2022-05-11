package org.ikropachev.votingspringboot.web.menu;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Menu;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = UserMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "User menu controller", description = "Operations for menus from user")
public class UserMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/user/menus";

    @GetMapping
    @Operation(summary = "View a list of all menus for current date")
    public List<Menu> getAllForToday() {
        LocalDate date = LocalDate.now();
        log.info("get all menus for date {}", date);
        return super.getAllByDate(date);
    }

    @GetMapping("/{id}")
    @Operation(summary = "View a menu with dishes")
    public Menu get(@PathVariable @Parameter(example = TODAY_MENU_ID_STR, required = true) int id) {
        log.info("get menu with id {}", id);
        return super.get(id);
    }
}
