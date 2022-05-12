package org.ikropachev.votingspringboot.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Vote;
import org.ikropachev.votingspringboot.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.ikropachev.votingspringboot.util.CheckTimeUtil.checkTime;
import static org.ikropachev.votingspringboot.web.SecurityUtil.authId;

@Slf4j
public class AbstractVoteController {


    @Autowired
    protected VoteRepository voteRepository;

    public Vote save(int restaurantId) {
        log.info("create/update vote from user with id {}", authId());
        checkTime();
        Vote previous = voteRepository.getByUserIdAndDate(authId(), LocalDate.now());
        if (previous == null) {
            return voteRepository.save(new Vote(authId(), restaurantId, LocalDate.now()));
        } else {
            previous.setRestaurantId(restaurantId);
            return voteRepository.save(previous);
        }
    }
}
