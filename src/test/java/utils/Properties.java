package utils;

public class Properties {
    private static final String DEFAULT_BASE_ADMIN_URL = "http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/";

    public static String getBaseAdminUrl() {
        return System.getProperty(EnvironmentVariables.BASE_ADMIN_URL.toString(), DEFAULT_BASE_ADMIN_URL);
    }
}

/**
 * All parameters that are passed to automation project
 */
enum EnvironmentVariables {
    BASE_ADMIN_URL("env.admin.url");

    private String value;
    EnvironmentVariables(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
