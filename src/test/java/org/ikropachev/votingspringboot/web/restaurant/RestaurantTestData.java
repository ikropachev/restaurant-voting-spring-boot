package org.ikropachev.votingspringboot.web.restaurant;

import org.ikropachev.votingspringboot.model.Restaurant;
import org.ikropachev.votingspringboot.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER =
            MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int NOT_FOUND = 100;
    //public static final String RESTAURANT2_NAME = "Burgers";

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "BarZero");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT1_ID + 1, "Burgers");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT1_ID + 2, "HappyShaverma");
    public static final Restaurant restaurant4 = new Restaurant(RESTAURANT1_ID + 3, "Suluguni");

    //Restaurants must be sorted by name
    public static final List<Restaurant> restaurants = List.of(restaurant1, restaurant2, restaurant3, restaurant4);

    public static Restaurant getNew() {
        return new Restaurant(null, "New_Test_Restaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "Updated_Test_Restaurant");
    }
}
