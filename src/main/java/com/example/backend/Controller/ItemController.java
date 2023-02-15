package com.example.backend.Controller;

import com.example.backend.Model.ItemModel;
import com.example.backend.Repository.ItemRepository;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
@CrossOrigin
public class ItemController {

    final ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @SneakyThrows
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, value = "/")
    public String findItems() {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(repository.findAll());
    }

    @DeleteMapping("/{delete}")
    public HttpStatus deleteItem(@PathVariable Long delete) {
        repository.deleteById(delete);
        return HttpStatus.OK;
    }

    @PostMapping("/change")
    public HttpStatus createItem(@RequestBody ItemModel model) {
        repository.save(model);
        return HttpStatus.OK;
    }

    @PutMapping("/change/{id}")
    public ItemModel updateItem(@PathVariable Long id, @RequestBody ItemModel model) {
        ItemModel model1 = repository.findById(id).orElseThrow();
        model1.setName(model.getName());
        model1.setPrice(model.getPrice());
        repository.save(model1);
        return model1;
    }

    @GetMapping("/change/{id}")
    public ItemModel getItem(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

}
