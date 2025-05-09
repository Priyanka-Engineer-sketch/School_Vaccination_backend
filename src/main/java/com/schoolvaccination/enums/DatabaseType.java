package com.schoolvaccination.enums;

public enum DatabaseType {
    MONGO, JPA;

    public static DatabaseType fromString(String db) {
        if (db == null) return JPA; // default
        return switch (db.toLowerCase()) {
            case "mongo" -> MONGO;
            case "jpa" -> JPA;
            default -> throw new IllegalArgumentException("Unsupported db type: " + db);
        };
    }
}