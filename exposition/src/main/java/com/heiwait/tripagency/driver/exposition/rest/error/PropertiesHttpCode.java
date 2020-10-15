package com.heiwait.tripagency.driver.exposition.rest.error;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class PropertiesHttpCode {

    Properties props;

    private PropertiesHttpCode() throws IOException {
        Resource resource = new ClassPathResource("error.properties");
        props = PropertiesLoaderUtils.loadProperties(resource);
    }

    public int getHttpCodeFromErrorCode(String errorCode) {
        return Integer.parseInt(String.valueOf(props.get(errorCode)));
    }
}
