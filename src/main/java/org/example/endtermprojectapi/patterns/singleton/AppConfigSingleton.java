package org.example.endtermprojectapi.patterns.singleton;

public class AppConfigSingleton {
    private static AppConfigSingleton instance;

    private final String appName = "Endterm Student Registration API";

    private AppConfigSingleton() {}

    public static synchronized AppConfigSingleton getInstance() {
        if (instance == null) instance = new AppConfigSingleton();
        return instance;
    }

    public String getAppName() { return appName; }
}
