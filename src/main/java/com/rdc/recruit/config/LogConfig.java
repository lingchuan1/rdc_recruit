package com.rdc.recruit.config;

import com.rdc.recruit.controller.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    public static final Logger logger = LoggerFactory.getLogger("ipRequest");
}
