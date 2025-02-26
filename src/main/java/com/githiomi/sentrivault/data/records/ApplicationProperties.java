package com.githiomi.sentrivault.data.records;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sentri.application")
public record ApplicationProperties(
        String title,
        String version,
        Boolean manual_db_populate,
        String schema

){}
