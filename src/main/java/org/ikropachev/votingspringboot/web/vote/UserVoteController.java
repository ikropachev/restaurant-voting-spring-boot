package org.ikropachev.votingspringboot.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.ikropachev.votingspringboot.web.restaurant.AbstractRestaurantController.RESTAURANT1_ID_STR;

@RestController
@RequestMapping(value = UserVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "User vote controller", description = "Operations for votes from user")
public class UserVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/user/votes";

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create/update a vote for a restaurant")
    public Vote createWithLocation(@RequestParam(value = "restaurant-id")
                                   @Parameter(example = RESTAURANT1_ID_STR, required = true) int restaurantId) {
        return super.save(restaurantId);
    }
}
