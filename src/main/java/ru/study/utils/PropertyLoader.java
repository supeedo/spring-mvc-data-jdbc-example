package ru.study.utils;

import ru.study.exceptions.ResourceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static ru.study.exceptions.ResourceException.ErrorCode.FILE_READING_ERROR;

public class PropertyLoader {
    static String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    static String appConfigPath = rootPath + "app.properties";

    public static Properties getProperty() {
        Properties appProps = new Properties();
        try {
            FileInputStream fis = new FileInputStream(appConfigPath);
            appProps.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new ResourceException("Property read error", e, FILE_READING_ERROR);
        }
        return appProps;
    }
}
