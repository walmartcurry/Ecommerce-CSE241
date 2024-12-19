package Controllers;

import models.Person;

public class UserSession {
    private static Person currentUser;

    public static void setUser(Person user) {
        currentUser = user;
    }

    public static Person getUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }
}
