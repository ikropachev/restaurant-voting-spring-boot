package org.ikropachev.votingspringboot.web.menu;

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
public class UserMenuController extends AbstractMenuController {
    static final String REST_URL = "/api/user/menus";

    @GetMapping
    public List<Menu> getAllForToday() {
        LocalDate date = LocalDate.now();
        log.info("get all menus for date {}", date);
        return super.getAllByDate(date);
    }

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        log.info("get menu with id {}", id);
        return super.get(id);
    }
}