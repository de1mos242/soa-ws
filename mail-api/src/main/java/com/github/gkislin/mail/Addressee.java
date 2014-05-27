package com.github.gkislin.mail;

/**
 * User: gkislin
 * Date: 09.09.13
 */
public class Addressee {
    private String name;
    private String email;

    // JAXB needed
    public Addressee() {
    }

    protected Addressee(String email) {
        this.email = email;
    }

    public Addressee(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name == null ? email : name + " <" + email + '>';    }
}
