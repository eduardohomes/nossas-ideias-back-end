package com.br.nossas.ideias.view;


import com.br.nossas.ideias.model.Voto;
import com.br.nossas.ideias.repository.VotoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/votos")
public class VotoEndpoint {

    private final VotoRepository votoDAO;

    public VotoEndpoint(VotoRepository votoDAO) {
        this.votoDAO = votoDAO;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(votoDAO.findAll(),HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Voto voto) {
        votoDAO.save(voto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}