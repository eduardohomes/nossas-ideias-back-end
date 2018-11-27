package com.br.nossas.ideias.endpoint;

import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.repository.FavoritaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/favoritas")
public class FavoritaEndpoint {

    private final FavoritaRepository favoritaDAO;

    public FavoritaEndpoint(FavoritaRepository favoritaDAO) {
        this.favoritaDAO = favoritaDAO;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll(Pageable pageable) {
        return new ResponseEntity<>(favoritaDAO.findAll(pageable),HttpStatus.OK);
    }

    @PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Favorita favorita) {
        favoritaDAO.save(favorita);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}