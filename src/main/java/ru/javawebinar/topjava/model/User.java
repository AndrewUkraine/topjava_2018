package ru.javawebinar.topjava.model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {

    AtomicInteger id;

    public AtomicInteger getId() {
        return id;
    }

    public void setId(AtomicInteger id) {
        this.id = id;
    }



}
