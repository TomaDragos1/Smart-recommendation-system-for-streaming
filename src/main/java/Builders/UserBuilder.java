package Builders;

import Homework.Stream;
import Homework.User;

import java.util.ArrayList;

public class UserBuilder {
    private User user = new User();

    public UserBuilder withId(Integer id) {
        user.setId(id);
        return this;
    }

    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder withListenedStreams(ArrayList <Stream> listenedStreams) {
        user.setListenedStreams(listenedStreams);
        return this;
    }

    public User build() {
        return user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
