/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.uuid.endpoint;

import br.com.uuid.model.Pessoa;
import br.com.uuid.repository.PessoaRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jefao
 */
@RestController
@RequestMapping("/")
public class PessoaEndpoint {
    
    private PessoaRepository pessoaDAO;
    @Autowired
    public PessoaEndpoint(PessoaRepository pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }
    
    @GetMapping("encher")
    public ResponseEntity<?> test(){
        //var p = ;
        var start = System.currentTimeMillis();
        var pessoas = new ArrayList<Pessoa>();
        for (int i = 0; i < 10000; i++) {
            //pessoaDAO.save(new Pessoa("momoa", i));
            pessoas.add(new Pessoa("jefao", i));
        }
        pessoaDAO.saveAll(pessoas);
        var end = System.currentTimeMillis();
        System.out.println("TEMPO: " + (end - start));
        return ResponseEntity.ok(end - start);
    }
    
    @GetMapping
    public ResponseEntity<?> mostra(){
        var start = System.currentTimeMillis();
        var fulano = pessoaDAO.findById(5649L);
        var end = System.currentTimeMillis();
        fulano.get().setAge((int) (end - start));
        return ResponseEntity.ok(fulano.get());
    }

}
