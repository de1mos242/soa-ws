package com.github.gkislin.mail;

import javax.xml.bind.annotation.*;

/**
 * User: gkislin
 * Date: 09.09.13
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Addressee {

    @XmlValue
    private String name;

    @XmlAttribute
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addressee addressee = (Addressee) o;

        if (!email.equals(addressee.email)) return false;
        if (name != null ? !name.equals(addressee.name) : addressee.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + email.hashCode();
        return result;
    }
}
