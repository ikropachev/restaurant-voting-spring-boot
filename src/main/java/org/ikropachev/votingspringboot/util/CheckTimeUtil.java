package org.ikropachev.votingspringboot.util;

import org.ikropachev.votingspringboot.error.LateVoteException;
import org.ikropachev.votingspringboot.error.ModeException;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class CheckTimeUtil {
    public static final LocalTime END_OF_CHANGE = LocalTime.of(11, 00);
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_BEFORE_END_OF_CHANGE = 1;
    public static final int MODE_AFTER_END_OF_CHANGE = 2;

    public static int mode = MODE_DEFAULT;

    public static void checkTime() {
        switch (mode) {
            case 0:
                checkTime(LocalTime.now());
                break;
            case 1:
                checkTime(END_OF_CHANGE.minus(1, ChronoUnit.MILLIS));
                break;
            case 2:
                checkTime(END_OF_CHANGE.plus(1, ChronoUnit.MILLIS));
                break;
            default:
                throw new ModeException("Unexpected mode: " + mode);
        }
    }

    public static void checkTime(LocalTime currentTime) {
        if (currentTime.isAfter(END_OF_CHANGE)) {
            throw new LateVoteException("Too late for change vote.");
        }
    }
}
