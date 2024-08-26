package com.example.webfluxdemo.service.impl;

import com.example.webfluxdemo.handlers.NoFoundItem;
import com.example.webfluxdemo.models.Item;
import com.example.webfluxdemo.repository.ItemRepository;
import com.example.webfluxdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Mono<Item> getById(Long id) {
        return itemRepository.findById(id).switchIfEmpty(Mono.error(new NoFoundItem("No se encontro item con id: "+id)));
    }

    @Override
    public Flux<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public Mono<Item> save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Mono<Void> deleteById(Long id) {
        return   itemRepository.deleteById(id);


    }

    @Override
    public Mono<Item> update(Item item) {
        return getById(item.getId()).flatMap( existItem ->{
            existItem.setName(item.getName());
            return itemRepository.save(existItem);
        });
    }
}
