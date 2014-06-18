package com.github.gkislin.mail;

import javax.activation.DataSource;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class MailAttach {
    String name;
    DataSource dataSource;
    String description;

    public MailAttach(String name, DataSource dataSource, String description) {
        this.description = description;
        this.name = name;
        this.dataSource = dataSource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
