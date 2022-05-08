package org.ikropachev.votingspringboot.util;

import org.ikropachev.votingspringboot.error.LateVoteException;

import java.time.LocalTime;

public class CheckTimeUtil {
    public static final LocalTime END_OF_CHANGE = LocalTime.of(11, 00);

    public static void checkTime(LocalTime currentTime) {
        if (currentTime.isAfter(END_OF_CHANGE)) {
            throw new LateVoteException("Too late for change vote.");
        }
    }
}
