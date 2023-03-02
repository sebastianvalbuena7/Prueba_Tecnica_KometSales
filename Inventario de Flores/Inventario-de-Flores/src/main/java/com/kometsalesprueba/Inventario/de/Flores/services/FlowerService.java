package com.kometsalesprueba.Inventario.de.Flores.services;

import com.kometsalesprueba.Inventario.de.Flores.model.Flower;

import java.util.List;

public interface FlowerService {
    void saveFlower(Flower flower);
    void deleteFlower(Long id);
}
