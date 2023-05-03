package com.example.backendwebshopassignment.Controller;

import com.example.backendwebshopassignment.models.Item;
import com.example.backendwebshopassignment.repository.ItemRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    private final ItemRepo itemRepo;

    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @RequestMapping("/items")
    public @ResponseBody List<Item> getAllItems(){
        return itemRepo.findAll();
    }

    @RequestMapping("/items/{Id}")
    public @ResponseBody Optional<Item> getItemByID(@PathVariable Long Id){
        return itemRepo.findById(Id);
    }

    @GetMapping("/addItem")
    public @ResponseBody String addItem(@RequestParam String name, @RequestParam Integer price){
        Item item = new Item(name, price);
        itemRepo.save(item);
        return "Item \""+ item.getName() + "\" saved";
    }

    @GetMapping("/addItem/form")
    public String addItemForm() {
        return "addItem";
    }

}
