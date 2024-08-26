package com.example.webfluxdemo.controller;

import com.example.webfluxdemo.models.Item;
import com.example.webfluxdemo.repository.ItemRepository;
import com.example.webfluxdemo.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    @Autowired
    private ItemService itemService;


    @GetMapping("/{id}")
    @Operation(summary = "Metodo get", description = "Obtiene un item por el id  ")
    @ApiResponse(responseCode = "200", description = "Data successfully received.")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    public Mono<Item> getItemById(@PathVariable Long id) {
        return itemService.getById(id);

    }

    @GetMapping("/all")
    @Operation(summary = "Metodo get", description = "Obtiene todos los items")
    @ApiResponse(responseCode = "200", description = "Data successfully received.")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    public Flux<Item> getAllItems() {
        return itemService.getAll();
    }

    @PostMapping
    @Operation(summary = "Metodo POST", description = "Crea un item en la bd ")
    @ApiResponse(responseCode = "200", description = "Data successfully received.")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    public Mono<Item> createItem(@RequestBody Item item) {
        return itemService.save(item);
    }

    @PutMapping()
    @Operation(summary = "Metodo PUT", description = "Actualiza un item por medio del id")
    @ApiResponse(responseCode = "200", description = "Data successfully received.")
    @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    public Mono<Item> updateItem(@RequestBody Item item) {
        return itemService.update(item);

    }

   @DeleteMapping("/{id}")
   @Operation(summary = "Metodo DELETE", description = "Elimina un item por medio del id")
   @ApiResponse(responseCode = "200", description = "Data successfully received.")
   @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    public Mono<ResponseEntity<Object>> deleteItem(@PathVariable Long id) {
/*        return itemService.getById(id)
                .flatMap(item -> itemService.deleteById(id)
                        .then(Mono.just(ResponseEntity.noContent().build())))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));*/
       logger.info("Deleting item with id: {}", id);
       return itemService.deleteById(id)
               .then(Mono.just(ResponseEntity.noContent().build()))
               .onErrorResume(e -> {
                   logger.error("Error deleting item with id: {}", id, e);
                   return Mono.just(ResponseEntity.notFound().build());
               });
    }
}

