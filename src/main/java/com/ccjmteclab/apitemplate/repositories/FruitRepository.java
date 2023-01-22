package com.ccjmteclab.apitemplate.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

import com.ccjmteclab.apitemplate.domain.Fruit;

/**
 * FruitRepository class
 * 
 * @author Jose Maciel <jose.s.maciel@outlook.com>
 * @version 1.0
 */
@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {
    
}
