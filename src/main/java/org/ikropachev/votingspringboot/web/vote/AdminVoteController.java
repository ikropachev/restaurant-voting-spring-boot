package org.ikropachev.votingspringboot.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Vote;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.ikropachev.votingspringboot.util.CheckTimeUtil.checkTime;
import static org.ikropachev.votingspringboot.web.SecurityUtil.authId;

@RestController
@RequestMapping(value = AdminVoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AdminVoteController extends AbstractVoteController {
    static final String REST_URL = "/api/admin/votes";

    @GetMapping
    public List<Vote> getAll() {
        log.info("getAll");
        return voteRepository.findAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Vote createWithLocation(@RequestParam(value = "restaurant-id") int restaurantId) {
        log.info("create/update vote from user with id {}", authId());
        checkTime(LocalTime.now());
        Vote previous = voteRepository.getByUserIdAndDate(authId(), LocalDate.now());
        previous.setUserId(authId());
        previous.setRestaurantId(restaurantId);
        previous.setDate(LocalDate.now());
        return voteRepository.save(previous);
    }
}
