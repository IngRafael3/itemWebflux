package com.example.webfluxdemo.repository;

import com.example.webfluxdemo.models.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {
}