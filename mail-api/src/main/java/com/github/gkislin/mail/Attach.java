package com.github.gkislin.mail;

import java.io.Serializable;

/**
 * User: gkislin
 * Date: 30.01.14
 */
public class Attach implements Serializable{
    protected String name;
    protected String description;

    public Attach() {
    }

    public Attach(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description == null ? name : description + " <" + name + ">";
    }
}
