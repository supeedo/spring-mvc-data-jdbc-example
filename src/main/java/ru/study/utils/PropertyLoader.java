package ru.study.utils;

import ru.study.exceptions.ResourceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import static ru.study.exceptions.ResourceException.ErrorCode.FILE_READING_ERROR;

public class PropertyLoader {
    private final static String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
    private final static String appConfigPath = rootPath + "app.properties";

    public static Properties getProperty() {
        Properties appProps = new Properties();
        try (FileInputStream fis = new FileInputStream(appConfigPath)) {
            appProps.load(fis);
        } catch (IOException e) {
            throw new ResourceException("Property read error", e, FILE_READING_ERROR);
        }
        return appProps;
    }
}
