package org.ikropachev.votingspringboot.web.vote;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.ikropachev.votingspringboot.util.CheckTimeUtil.checkTime;
import static org.ikropachev.votingspringboot.web.SecurityUtil.authId;
import static org.ikropachev.votingspringboot.web.restaurant.AbstractRestaurantController.RESTAURANT1_ID_STR;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Tag(name = "Admin vote controller", description = "Operations for votes from admin")
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/admin/votes";

    @GetMapping
    @Operation(summary = "View a list of all votes")
    public List<Vote> getAll() {
        log.info("getAll");
        return voteRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create/update a vote for a restaurant")
    public Vote createWithLocation(@RequestParam(value = "restaurant-id")
                                       @Parameter(example = RESTAURANT1_ID_STR, required = true) int restaurantId) {
        return super.save(restaurantId);
    }
}
