package com.kometsalesprueba.Inventario.de.Flores.services.Implementation;

import com.kometsalesprueba.Inventario.de.Flores.model.Flower;
import com.kometsalesprueba.Inventario.de.Flores.repository.FlowerRepository;
import com.kometsalesprueba.Inventario.de.Flores.services.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private FlowerRepository flowerRepository;

    @Override
    public void saveFlower(Flower flower) {
        flowerRepository.save(flower);
    }

    @Override
    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }
}
