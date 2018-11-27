package com.br.nossas.ideias.endpoint;

import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.model.Ideia;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.DashboardRepository;
import com.br.nossas.ideias.repository.FavoritaRepository;
import com.br.nossas.ideias.repository.IdeiaRepository;
import com.br.nossas.ideias.repository.UserRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

import java.util.List;

import org.springframework.data.domain.Pageable;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dashboard")
public class DasboardEndpoint {

    private final DashboardRepository dashboardRepositoryDAO;
    private final IdeiaRepository ideiaDAO;
    private final FavoritaRepository favoritaDAO;
    private final UserRepository userDAO;

    public DasboardEndpoint(DashboardRepository dashboardRepositoryDAO, IdeiaRepository ideiaDAO, UserRepository userDAO, FavoritaRepository favoritaDAO) {
        this.dashboardRepositoryDAO = dashboardRepositoryDAO;
        this.ideiaDAO = ideiaDAO;
        this.userDAO = userDAO;
        this.favoritaDAO = favoritaDAO;
    }

    @GetMapping
    public ResponseEntity<?> listAll(@RequestHeader HttpHeaders httpHeaders, Pageable pageable) {

        Map<String,String> headerMap=httpHeaders.toSingleValueMap();

        String token = headerMap.get("token").toString();

        User user = userDAO.findByPassword(token);

	    boolean admin = userDAO.findByPassword(token).isAdmin();

		if(admin) {
			return new ResponseEntity<>(ideiaDAO.findBySituacao(),HttpStatus.OK);
		} else {

		}

        JSONArray jsonArray = new JSONArray();

        List<Ideia> ideiaList = ideiaDAO.findAll();

        List<Ideia> ideiaListUltimas = ideiaDAO.findByUltimasCadastradas();

        List<Ideia> ideiaListMaisVotadas = ideiaDAO.findByMaisVotadas();

        List<Favorita> favoritaList = favoritaDAO.getFindByIdUsername(user.getUsername());

        if(favoritaList.size() > 0) {

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

}
