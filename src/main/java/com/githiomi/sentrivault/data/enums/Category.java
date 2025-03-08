package com.githiomi.sentrivault.data.enums;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * Author: dangit
 * Project: SentriVault
 * GitHub: <a href="https://github.com/danielgithiomi">danielgithiomi</a>
 * Version: 1.0.0
 * Created: 10, Jan 2025
 **/

@Getter
public enum Category {

    SPORTS("Sports"),
    TECHNOLOGY("Technology"),
    LIFESTYLE("Lifestyle"),
    HEALTH("Health"),
    ENTERTAINMENT("Entertainment"),
    EDUCATION("Education"),
    TRAVEL("Travel"),
    FINANCE("Finance"),
    FOOD("Food"),
    POLITICS("Politics");

    private final String category;

    Category(String category) {
        this.category = category;
    }

}
