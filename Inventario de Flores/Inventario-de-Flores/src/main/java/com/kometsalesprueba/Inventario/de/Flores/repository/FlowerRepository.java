package com.kometsalesprueba.Inventario.de.Flores.repository;

import com.kometsalesprueba.Inventario.de.Flores.model.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findByName(String name);
}
