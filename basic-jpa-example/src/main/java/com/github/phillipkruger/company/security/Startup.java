package com.github.phillipkruger.company.security;

import io.quarkus.runtime.StartupEvent;
import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import javax.transaction.Transactional;

@Singleton
public class Startup {
    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        // reset and load all test users
        User.deleteAll();
        User.add("dilbert", "dilbert", "employee");
        User.add("wally", "wally", "employee");
        User.add("alice", "alice", "employee");
        User.add("dogbert", "dogbert", "employee");
        User.add("catbert", "catbert", "employee");
        User.add("asok", "asok", "employee");
        User.add("ted", "ted", "employee");
        User.add("boss", "boss", "boss");
    }
}
