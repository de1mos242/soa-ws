package com.github.gkislin.common.config;

import com.github.gkislin.common.io.ReadableFile;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * User: gkislin
 * Date: 11.09.13
 */
public class RootConfig implements IConfig {
    private static final RootConfig INSTANCE = new RootConfig();
    private static final String APP_CONF = "application.conf";

    private final Config config;

    private RootConfig() {
        config = ConfigFactory.parseFile(ReadableFile.getResource(APP_CONF)).resolve();
    }

    public static RootConfig get() {
        return INSTANCE;
    }

    public static Config getConf() {
        return INSTANCE.config;
    }

    public Config getSubConfig(String path) {
        return getConf().getConfig(path);
    }

    public String getHost(String name) {
        return getSubConfig("host").getString(name);
    }
}
