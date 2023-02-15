package com.example.backend.Repository;

import com.example.backend.Model.ItemModel;
import org.springframework.data.repository.CrudRepository;


public interface ItemRepository extends CrudRepository<ItemModel,Long> {
}
