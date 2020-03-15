/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.endpoint;

import br.com.uuid.model.Humano;
import br.com.uuid.repository.HumanoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefao
 */
@RestController
public class HumanoEndpoint {
    
    private HumanoRepository dao;
    @Autowired
    public HumanoEndpoint(HumanoRepository dao) {
        this.dao = dao;
    }
    
    @GetMapping("uuid")
    public ResponseEntity<?> save(){
        var h = dao.save(new Humano("jefao", 0));
        return ResponseEntity.ok(h);
    }
}
