package org.ikropachev.votingspringboot;

import javax.validation.groups.Default;

public class View {
    // Validate only form UI/REST
    public interface Web extends Default {}
}
