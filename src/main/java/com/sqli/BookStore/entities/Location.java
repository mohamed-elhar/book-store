package com.sqli.BookStore.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Location {

	@Id
    private String department;

    private String level;

    private String side;
    
    public Location() {
	}
    

    public Location(String department, String level, String side) {
        this.department = department;
        this.level = level;
        this.side = side;
    }

    public String getDepartment() {
        return department;
    }

    public String getLevel() {
        return level;
    }

    public String getSide() {
        return side;
    }
}
