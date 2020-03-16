/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.endpoint;

import br.com.uuid.model.Pessoa;
import br.com.uuid.repository.PessoaRepository;
import java.util.ArrayList;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefao
 */
@RestController
@RequestMapping("/auto")
public class PessoaEndpoint {
    
    private PessoaRepository dao;
    @Autowired
    public PessoaEndpoint(PessoaRepository dao) {
        this.dao = dao;
    }
    
    @GetMapping("poe10k")
    public ResponseEntity<?> poe10k(){
        var start = System.currentTimeMillis();
        var pessoas = new ArrayList<Pessoa>();
        for (int i = 0; i < 10000; i++) {
           // pessoas.add(new Pessoa("jefao", i));
           dao.save(new Pessoa("momoa", i));
        }
        //dao.saveAll(pessoas);
        var end = System.currentTimeMillis();
        System.out.println("TEMPO: " + (end - start));
        return ResponseEntity.ok(end - start);
    }
    
    @GetMapping
    public ResponseEntity<?> mostra(){
        var start = System.currentTimeMillis();
        var fulano = dao.findById(1221L);
        fulano.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("NADA POR AQUI"));
        var end = System.currentTimeMillis();
        
        if(fulano.isPresent()){
            fulano.get().setAge((int) (end - start));
            return ResponseEntity.ok(fulano.get());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("poe1")
    public ResponseEntity<?> poe1(){
        var start = System.currentTimeMillis();
        dao.save(new Pessoa("momoa", 34));
        var end = System.currentTimeMillis();
        return new ResponseEntity(end - start, HttpStatus.CREATED);
    }
}
