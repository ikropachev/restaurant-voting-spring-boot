package org.ikropachev.votingspringboot.web.user;

import org.ikropachev.votingspringboot.model.Role;
import org.ikropachev.votingspringboot.model.User;
import org.ikropachev.votingspringboot.util.JsonUtil;
import org.ikropachev.votingspringboot.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER =
            MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int ADMIN_ID = 1;
    public static final int USER_ID = 2;
    public static final int NOT_FOUND = 100;
    public static final String ADMIN_MAIL = "admin@gmail.com";
    public static final String USER_MAIL = "user@gmail.com";

    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);
    public static final User user = new User(USER_ID, "User", USER_MAIL, "password", Role.USER);
    public static final User user2 =
            new User(USER_ID + 1, "second_user", "second@gmail.com", "second_pass", Role.USER);
    public static final User user3 =
            new User(USER_ID + 2, "third_user", "third@gmail.com", "third_pass", Role.USER);

    public static final List<User> users = List.of(admin, user, user2, user3);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(),
                Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(),
                Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
