package com.ccjmteclab.apitemplate.domain;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Fruit class
 * 
 * @author Jose Maciel <jose.s.maciel@outlook.com>
 * @version 1.0
 */
@Entity
@Cacheable
public class Fruit {

    @Id
    @GeneratedValue
    public Long id;
    
    @Column(length = 40, unique = true)
    public String name;

    @Column(length = 100)
    public String description;

    public Fruit() {
    }

    public Fruit(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /** 
     * @return String
     */
    public String getName() {
        return name;
    }

    /** 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /** 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /** 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Fruit [id=" + id + ", name=" + name + ", description=" + description + "]";
    }

}
