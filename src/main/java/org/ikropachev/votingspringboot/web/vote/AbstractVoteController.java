package org.ikropachev.votingspringboot.web.vote;

import lombok.extern.slf4j.Slf4j;
import org.ikropachev.votingspringboot.model.Vote;
import org.ikropachev.votingspringboot.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.ikropachev.votingspringboot.util.CheckTimeUtil.checkTime;

@Slf4j
public class AbstractVoteController {

    @Autowired
    protected VoteRepository voteRepository;

    //For tests from API
    public List<Vote> getAll() {
        log.info("get all votes");
        return voteRepository.findAll();
    }
}
