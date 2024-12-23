package Controllers;

import models.Supplier;

public class SuppSession {
    private static Supplier currentSupp;

    public static void setUser(Supplier user) {
        currentSupp = user;
    }

    public static Supplier getUser() {
        return currentSupp;
    }

    public static void clearSession() {
        currentSupp = null;
    }
}
