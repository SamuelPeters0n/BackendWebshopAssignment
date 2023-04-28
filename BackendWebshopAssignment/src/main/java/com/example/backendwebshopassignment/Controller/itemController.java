package com.example.backendwebshopassignment.Controller;

import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.ItemRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class itemController {

    private final ItemRepo itemRepo;

    public itemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @RequestMapping("/items")
    public List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    @RequestMapping("/items/{Id}")
    public Optional<Item> getItemByID(@PathVariable Long Id){
        return itemRepo.findById(Id);
    }

    @PostMapping("/addItem")
    public String addItem(@RequestBody Item item){
        itemRepo.save(item);
        return "Item \""+ item.getName() + "\" saved";
    }
}
