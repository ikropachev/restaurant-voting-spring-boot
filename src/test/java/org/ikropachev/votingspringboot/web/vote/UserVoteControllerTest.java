package org.ikropachev.votingspringboot.web.vote;

import org.ikropachev.votingspringboot.model.Vote;
import org.ikropachev.votingspringboot.repository.VoteRepository;
import org.ikropachev.votingspringboot.util.CheckTimeUtil;
import org.ikropachev.votingspringboot.util.JsonUtil;
import org.ikropachev.votingspringboot.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.ikropachev.votingspringboot.util.CheckTimeUtil.*;
import static org.ikropachev.votingspringboot.web.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static org.ikropachev.votingspringboot.web.user.UserTestData.USER_MAIL;
import static org.ikropachev.votingspringboot.web.vote.VoteTestData.VOTE_MATCHER;
import static org.ikropachev.votingspringboot.web.vote.VoteTestData.getNewForAdmin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Check time in CheckTimeUtil before test
public class UserVoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserVoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createBeforeEndOfVotingTime() throws Exception {
        CheckTimeUtil.mode = MODE_BEFORE_END_OF_CHANGE;
        Vote newVote = getNewForAdmin();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + "?restaurant-id=" + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isOk());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getById(newId), newVote);
        CheckTimeUtil.mode = MODE_DEFAULT;
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createAfterEndOfVotingTime() throws Exception {
        CheckTimeUtil.mode = MODE_AFTER_END_OF_CHANGE;
        Vote newVote = getNewForAdmin();
        perform(MockMvcRequestBuilders.post(REST_URL + "?restaurant-id=" + RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)))
                .andExpect(status().isNotAcceptable());
        CheckTimeUtil.mode = MODE_DEFAULT;
    }
}
