/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.gkislin.common.sql;


import com.github.gkislin.common.LoggerWrapper;

import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * @author gkislin
 */
public class DataSourceProvider {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(DataSourceProvider.class);

    interface DataSourceFactory {
        DataSource getDataSource();
    }

    private static class JndiDataSource {
        private static final DataSource dataSource;

        static {
            try {
                InitialContext ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/soa-ws");
            } catch (Exception ex) {
                throw LOGGER.getIllegalStateException("PostgreSQL initialization failed", ex);
            }

        }
    }

    static DataSourceFactory dataSourceFactory = new DataSourceFactory() {
        @Override
        public DataSource getDataSource() {
            return JndiDataSource.dataSource;
        }
    };

    static DataSource getDataSource() {
        return dataSourceFactory.getDataSource();
    }
}
