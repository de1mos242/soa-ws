package com.github.gkislin.common.sql;

import org.apache.tomcat.jdbc.pool.DataSource;

/**
 * User: gkislin
 * Date: 26.09.13
 */
public class SqlTest {

    public static void initDb() {
        initDb("jdbc:postgresql://ubuntu64:5432/javaee", "user", "gfhjkm");
    }

    public static void initDb(String dbUrl, String dbUser, String dbPsw) {
        final DataSource dataSource = new DataSource();
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPsw);
        dataSource.setDriverClassName("org.postgresql.Driver");

        DataSourceProvider.dataSourceFactory = new DataSourceProvider.DataSourceFactory() {
            @Override
            public DataSource getDataSource() {
                return dataSource;
            }
        };
    }
}
