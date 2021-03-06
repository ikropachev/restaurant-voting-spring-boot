package org.ikropachev.votingspringboot.web.vote;

import org.ikropachev.votingspringboot.model.Vote;
import org.ikropachev.votingspringboot.web.MatcherFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.ikropachev.votingspringboot.web.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static org.ikropachev.votingspringboot.web.user.UserTestData.ADMIN_ID;
import static org.ikropachev.votingspringboot.web.user.UserTestData.USER_ID;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingEqualsComparator(Vote.class);
    public static final int VOTE1_ID = 1;
    //https://stackoverflow.com/questions/8746084/string-to-localdate
    public static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static final Vote vote1 =
            new Vote(VOTE1_ID, USER_ID, RESTAURANT1_ID + 1, LocalDate.parse("2021-12-02", PATTERN));
    public static final Vote vote2 =
            new Vote(VOTE1_ID + 1, USER_ID, RESTAURANT1_ID + 2, LocalDate.parse("2021-12-03", PATTERN));
    public static final Vote vote3 =
            new Vote(VOTE1_ID + 2, USER_ID + 1, RESTAURANT1_ID + 1, LocalDate.parse("2021-11-16", PATTERN));
    public static final Vote vote4 =
            new Vote(VOTE1_ID + 3, USER_ID + 1, RESTAURANT1_ID + 3, LocalDate.parse("2021-11-17", PATTERN));
    public static final Vote vote5 =
            new Vote(VOTE1_ID + 4, USER_ID + 2, RESTAURANT1_ID + 2, LocalDate.parse("2021-11-17", PATTERN));
    public static final Vote vote6 =
            new Vote(VOTE1_ID + 5, USER_ID + 1, RESTAURANT1_ID + 1, LocalDate.now());
    public static final Vote vote7 =
            new Vote(VOTE1_ID + 6, USER_ID + 2, RESTAURANT1_ID + 2, LocalDate.now());

    //Votes must be sorted by date DESC
    public static final List<Vote> votes = List.of(vote6, vote7, vote2, vote1, vote4, vote5, vote3);

    public static Vote getNew() {
        return new Vote(null, USER_ID, RESTAURANT1_ID, LocalDate.now());
    }

    public static Vote getNewForAdmin() {
        return new Vote(null, ADMIN_ID, RESTAURANT1_ID, LocalDate.now());
    }
}
