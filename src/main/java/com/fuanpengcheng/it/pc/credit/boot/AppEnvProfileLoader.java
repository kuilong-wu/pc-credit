package com.fuanpengcheng.it.pc.credit.boot;

import com.fuanpengcheng.it.pc.credit.consts.PropertiesConsts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author: wukuilong
 * @version: 1.0
 * @created: 2018-03-07
 * @description:
 */
public class AppEnvProfileLoader {
    //必须在spring boot启动之前执行
    public static boolean initialize() {
        return loadEnvProperties();
    }

    private static String env = "local";

    private static Properties loadPropertiesFromClasspath(String env, String fileName) throws IOException {
        Properties prop = new Properties();

        InputStream applicationFile = AppEnvProfileLoader.class.getClassLoader().getResourceAsStream(
                PropertiesConsts.PROFILE_PREFIX + File.separator + env + File.separator + fileName);
        prop.load(applicationFile);
        return prop;
    }

    private static boolean loadEnvProperties() {
        env = System.getProperty(PropertiesConsts.APP_ENV_PROFILE_KEY);
        if (env == null) {
            System.out.println("Application env args not exist, set to default: local.");
            env = PropertiesConsts.LOCAL_PROFILE;
        }
        Properties prop = new Properties();
        System.out.println("Loading properties from ENV:" + env + "...");
        try {
            Properties applicationProp = loadPropertiesFromClasspath(env,
                    PropertiesConsts.SPRING_BOOT_PROPERTIES_FILE);
            prop.putAll(applicationProp);

            Properties customProp = loadPropertiesFromClasspath(env, PropertiesConsts.CUSTOME_PROPERTIES_FILE);
            prop.putAll(customProp);

            copyPropertiesToSystem(prop);
            System.out.println("Load properties from " + env + " success.\n");
            return true;
        } catch (Exception e) {
            System.out.println("Read properties file error, load properties from " + env + " failed.");
            e.printStackTrace();
            return false;
        }
    }

    private static void copyPropertiesToSystem(Properties properties) {

        for (Object key : properties.keySet()) {
            String keyStr = (String) key;
            String sysProperty = System.getProperty(keyStr);
            if (sysProperty == null || "".equals(sysProperty)) {
                String property = properties.getProperty((String) key);
                System.setProperty((String) key, property);
            }
        }
    }

    public static boolean isLocalEnv() {
        return PropertiesConsts.LOCAL_PROFILE.equals(getEnv());
    }

    public static boolean isOnlineEnv() {
        return PropertiesConsts.ONLINE_PROFILE.equals(getEnv());
    }

    public static String getEnv() {
        return env;
    }

    public static boolean isOnlineDB() {
        return PropertiesConsts.ONLINE_ENV.equals(getDBEnv());
    }

    public static String getDBEnv() {
        return System.getProperty("db.env");
    }

    public static String getDBUrl() {
        return System.getProperty("db.url");
    }

    public static String getDBDriver() {
        return System.getProperty("db.driver");
    }

    public static String getDBUsername() {
        return System.getProperty("db.username");
    }

    public static String getDBPassword() {
        return System.getProperty("db.password");
    }


}
