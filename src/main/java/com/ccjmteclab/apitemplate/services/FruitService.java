package com.ccjmteclab.apitemplate.services;

import javax.enterprise.context.ApplicationScoped;

import com.ccjmteclab.apitemplate.domain.Fruit;
import com.ccjmteclab.apitemplate.repositories.FruitRepository;

import javax.transaction.Transactional;

import java.util.List;

import io.quarkus.panache.common.Sort;

/**
 * FruitService class
 * 
 * @author Jose Maciel <jose.s.maciel@outlook.com>
 * @version 1.0
 */
@ApplicationScoped
public class FruitService {


    private FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    /** 
     * @return List<Fruit>
     */
    public List<Fruit> findAll() {
        return fruitRepository.listAll(Sort.by("name"));
    }

    /** 
     * @param id
     * @return Fruit
     */
    public Fruit findById(Long id) {
        Fruit entity = fruitRepository.findById(id);
        return entity;
    }

    /** 
     * @param id
     * @return Fruit
     */
    @Transactional
    public Fruit delete(Long id) {
        Fruit entity = fruitRepository.findById(id);
        fruitRepository.delete(entity);
        return entity;
    }

    /** 
     * @param fruit
     * @return Fruit
     */
    @Transactional
    public Fruit save(Fruit fruit) {
        fruitRepository.persist(fruit);
        return fruit;
    }

    /** 
     * @param id
     * @param fruit
     * @return Fruit
     */
    @Transactional
    public Fruit update(Long id, Fruit fruit) {
        Fruit entity = fruitRepository.findById(id);
        entity.setName(fruit.name);
        fruitRepository.persist(entity);
        return entity;
    }
}
