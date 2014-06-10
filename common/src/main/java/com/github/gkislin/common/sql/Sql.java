package com.github.gkislin.common.sql;

import com.github.gkislin.common.ExceptionType;
import com.github.gkislin.common.LoggerWrapper;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;

/**
 * User: gkislin
 * Date: 25.09.13
 */
public class Sql {
    private static final LoggerWrapper LOGGER = LoggerWrapper.get(Sql.class);

    //  This class is thread safe
    private static final QueryRunner QUERY_RUNNER = new QueryRunner(DataSourceProvider.getDataSource());

    public static int update(final String updateSql, final Object... params) {
        try {
            return QUERY_RUNNER.update(updateSql, params);
        } catch (SQLException e) {
            throw LOGGER.getStateException(ExceptionType.DATA_BASE, e);
        }
    }

    public static <T> T query(final String sql, final ResultSetHandler<T> rsh, final Object... params) {
        try {
            return QUERY_RUNNER.query(sql, rsh, params);
        } catch (SQLException e) {
            throw LOGGER.getStateException(ExceptionType.DATA_BASE, e);
        }
    }
}
