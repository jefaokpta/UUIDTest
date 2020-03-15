/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.repository;

import br.com.uuid.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jefao
 */
public interface PessoaRepository extends CrudRepository<Pessoa, Long>{
    
}
