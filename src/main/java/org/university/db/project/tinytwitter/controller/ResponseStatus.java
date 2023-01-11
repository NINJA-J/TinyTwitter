package org.university.db.project.tinytwitter.controller;

public enum ResponseStatus {
    SUCCESS(0),
    ERROR(1),
    MISSING_PARAM(2);

    public int code;

    ResponseStatus(int code) {
        this.code = code;
    }
}
