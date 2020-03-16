/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.endpoint;

import br.com.uuid.model.Humano;
import br.com.uuid.repository.HumanoRepository;
import java.util.ArrayList;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefao
 */
@RestController()
@RequestMapping("/uuid")
public class HumanoEndpoint {
    
    private HumanoRepository dao;
    @Autowired
    public HumanoEndpoint(HumanoRepository dao) {
        this.dao = dao;
    }
    
    @GetMapping("poe1")
    public ResponseEntity<?> poe1(){
        var start = System.currentTimeMillis();
        dao.save(new Humano("momoa", 34));
        var end = System.currentTimeMillis();
        return new ResponseEntity(end - start, HttpStatus.CREATED);
    }
    
    @GetMapping("poe10k")
    public ResponseEntity<?> poe10k(){
        var start = System.currentTimeMillis();
        var pessoas = new ArrayList<Humano>();
        for (int i = 0; i < 10000; i++) {
           pessoas.add(new Humano("jefao", i));
           //dao.save(new Humano("momoa", i));
        }
        dao.saveAll(pessoas);
        var end = System.currentTimeMillis();
        System.out.println("TEMPO: " + (end - start));
        return ResponseEntity.ok(end - start);
    }
    
    @GetMapping("{idade}")
    public ResponseEntity<?> mostra(@PathVariable("idade") int idade){
        var start = System.currentTimeMillis();
        var fulanos = dao.findByIdade(idade);
        var end = System.currentTimeMillis();
        
        if(!fulanos.isEmpty()){
            System.out.println("TEMPO: " + (end - start));
            //fulano.get().setAge((int) (end - start));
            fulanos.forEach(f -> System.out.println(f.getId()));
            return ResponseEntity.ok(fulanos);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("id/{id}")
    public ResponseEntity<?> mostra(@PathVariable("id") String id){
        var start = System.currentTimeMillis();
        System.out.println("UUID: " + id);
        var fulano = dao.findById(UUID.fromString(id));
        fulano.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("NADA POR AQUI"));
        var end = System.currentTimeMillis();
        
        if(fulano.isPresent()){
            System.out.println("TEMPO: " + (end - start));
            //fulano.get().setAge((int) (end - start));
            return ResponseEntity.ok(fulano.get());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
