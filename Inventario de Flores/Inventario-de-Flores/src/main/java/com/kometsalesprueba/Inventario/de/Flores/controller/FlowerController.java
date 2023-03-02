package com.kometsalesprueba.Inventario.de.Flores.controller;

import com.kometsalesprueba.Inventario.de.Flores.model.Flower;
import com.kometsalesprueba.Inventario.de.Flores.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class FlowerController {
    @Autowired
    private FlowerService flowerService;

    @PostMapping("/addFlowers")
    public String addFlowers(@RequestBody List<Flower> flowers) {
        Flower.flowers = flowers;
        flowers.forEach(flower -> flowerService.saveFlower(flower));
        return "The list has been saved";
    }

    @GetMapping("/getFlowers")
    public List<Map<String, Object>> getFlowers() {
        List<Flower> flowers = new ArrayList<>(Flower.getFlowers());
        flowers.forEach(flower -> {
            if(!flower.getName().contains("-kometsales")) {
                flower.setName(flower.getName().concat("-kometsales"));
            }
        });

        flowers.sort(Comparator.comparing(Flower::getName).reversed());

        List<Map<String, Object>> result = new ArrayList<>();
        flowers.forEach(flower -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", flower.getName());
            map.put("price", flower.getPrice());
            result.add(map);
        });

        return result;
    }

    @GetMapping("/getFlowersPrice")
    public List<Flower> getFlowersPrice() {
        return Flower.getFlowers().stream().map(flower -> {
            if(flower.getName().contains("-kometsales")) {
                String[] nameParts = flower.getName().split("-");
                flower.setName(nameParts[0]);
            }
            return flower;
        }).filter(flor -> flor.getPrice() > 20).collect(Collectors.toList());
    }

    @DeleteMapping("/deleteFlower/{id}")
    public String deleteFlower(@PathVariable Long id) {
        Optional<Flower> flowerFound = Flower.getFlowers().stream().filter(flower1 -> flower1.getId().equals(id)).findFirst();
        if(flowerFound.isPresent()) {
            flowerService.deleteFlower(id);
            Flower.getFlowers().remove(flowerFound.get());
        }
        return "Flower eliminated";
    }

    @GetMapping("/getFlowersName")
    public List<Flower> getFlowersName(@RequestParam String name) {
        return Flower.getFlowers().stream().map(flower -> {
            if (flower.getName().contains("-kometsales")) {
                String[] nameParts = flower.getName().split("-");
                flower.setName(nameParts[0]);
            }
            return flower;
        }).filter(flower -> flower.getName().equals(name)).collect(Collectors.toList());
    }
}