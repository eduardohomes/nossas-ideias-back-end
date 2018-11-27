package com.br.nossas.ideias.endpoint;


import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.model.Ideia;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.DashboardRepository;
import com.br.nossas.ideias.repository.FavoritaRepository;
import com.br.nossas.ideias.repository.IdeiaRepository;
import com.br.nossas.ideias.repository.UserRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/ideias")
public class IdeiaEndpoint {

    private final IdeiaRepository ideiaDAO;
    private final FavoritaRepository favoritaDAO;
    private final UserRepository userDAO;

	public IdeiaEndpoint(IdeiaRepository ideiaDAO, FavoritaRepository favoritaDAO, UserRepository userDAO) {
        this.ideiaDAO = ideiaDAO;
        this.favoritaDAO = favoritaDAO;
        this.userDAO = userDAO;        
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> listAll(Pageable pageable) {    	
    	return new ResponseEntity<>(ideiaDAO.findAll(pageable),HttpStatus.OK);    	
    }

    @PostMapping()
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> findByUsername(@Valid @RequestBody User user) {

        JSONArray jsonArray = new JSONArray();

        List<Ideia> ideiaList = ideiaDAO.findAll();

        List<Favorita> favoritaList = favoritaDAO.getFindByIdUsername(user.getUsername());

        if(favoritaList != null) {

            for (int i = 0; i < ideiaList.size(); i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaList.get(i).getId());
                jsonObject.put("nome", ideiaList.get(i).getNome());
                jsonObject.put("descricao", ideiaList.get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaList.get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaList.get(i).getAtiva());
                jsonObject.put("situacao", ideiaList.get(i).getSituacao());

                long id = ideiaList.get(i).getId();

                int x = 0;
                String marcada = "";

                for (int j = x; j < favoritaList.size(); j++) {
                    if(favoritaList.get(j).getIdIdeia().equals(id)) {
                        marcada =  favoritaList.get(j).getMarcada();
                    }
                }

                if (marcada.equals("N")) {
                    jsonObject.put("marcada", marcada);
                } else {
                	jsonObject.put("marcada", "S");
                }

                jsonArray.add(jsonObject);
            }
        }

        return new ResponseEntity<>(jsonArray, HttpStatus.OK);
    }
    
    @CrossOrigin
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getIdeiaById(@PathVariable("id") Long id) {
        Optional<Ideia> ideia = ideiaDAO.findById(id);
        if(ideia == null) {
            throw new ResourceNotFoundExcepction("Ideia not found for Id " + id);
        }
        return new ResponseEntity<>(ideia,HttpStatus.OK);
    }

    /*@PostMapping
    @CrossOrigin
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> save(@Valid @RequestBody Ideia ideia) {
        ideiaDAO.save(ideia);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/

    @PutMapping
    @CrossOrigin
    public ResponseEntity<?> update(@RequestHeader("Token") String token, @RequestBody Ideia ideia) {    	    	
        
    	ideiaDAO.save(ideia);
        Favorita favorita = new Favorita();
        User user = userDAO.findByPassword(token);
        
        ///favorita.setIdIdeia(ideiaDAO.getMaxId());
        favorita.setIdUser(user.getId());
        favorita.setMarcada("N");
        favoritaDAO.save(favorita);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
