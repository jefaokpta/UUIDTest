/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.repository;

import br.com.uuid.model.Humano;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jefao
 */
public interface HumanoRepository extends CrudRepository<Humano, UUID>{

    public List<Humano> findByIdade(int i);
    
}
