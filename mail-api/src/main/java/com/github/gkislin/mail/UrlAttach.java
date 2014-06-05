package com.github.gkislin.mail;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class UrlAttach extends Attach {
    private String url;

    public UrlAttach() {
    }

    public UrlAttach(String name, String url) {
        super(name, null);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return url + ": " + super.toString();
    }
}
