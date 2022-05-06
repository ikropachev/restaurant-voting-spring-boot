package org.ikropachev.votingspringboot;

public interface HasIdAndEmail extends HasId {
    String getEmail();
}