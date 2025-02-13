package com.githiomi.sentrivault.data.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sentri.application")
public record Properties(
        String title,
        String version,
        Boolean manual_db_populate,
        String schema

){}
