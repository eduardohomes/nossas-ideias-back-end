package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.model.Ideia;
import com.br.nossas.ideias.model.User;
import com.br.nossas.ideias.repository.CategoriaRepository;
import com.br.nossas.ideias.repository.FavoritaRepository;
import com.br.nossas.ideias.repository.IdeiaRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class IdeiaController {

    private final AutenticarController autenticarController;
    private final IdeiaRepository ideiaRepository;
    private final FavoritaRepository favoritaRepository;
    private final CategoriaRepository categoriaRepository;
    private final UserController userController;
    private final JSONArray jsonArrayTodasIdeias = new JSONArray();
    private final JSONArray jsonArrayUltimasIdeiasCadastradas = new JSONArray();
    private final JSONArray jsonArrayMaisVotadas = new JSONArray();

    public IdeiaController(AutenticarController autenticarController, IdeiaRepository ideiaRepository, FavoritaRepository favoritaRepository, CategoriaRepository categoriaRepository, UserController userController) {
        this.autenticarController = autenticarController;
        this.ideiaRepository = ideiaRepository;
        this.favoritaRepository = favoritaRepository;
        this.categoriaRepository = categoriaRepository;
        this.userController = userController;
    }

    public JSONArray listarTodasIdeias(){

            int tamanho = ideiaRepository.findAll().size();

            if(tamanho > 0) {

                for (int i = 0; i < tamanho; i++) {

                    JSONObject jsonObject = new JSONObject();

                    jsonObject.put("id", ideiaRepository.findAll().get(i).getId());
                    jsonObject.put("nome", ideiaRepository.findAll().get(i).getNome());
                    jsonObject.put("descricao", ideiaRepository.findAll().get(i).getDescricao());
                    jsonObject.put("comentarioAvaliador", ideiaRepository.findAll().get(i).getComentarioAvaliador());
                    jsonObject.put("ativa", ideiaRepository.findAll().get(i).getAtiva());
                    jsonObject.put("situacao", ideiaRepository.findAll().get(i).getSituacao());

                    long id = ideiaRepository.findAll().get(i).getId();

                    int x = 0;
                    String marcada = "";

                    int tamanhoFavorita = favoritaRepository.findAll().size();

                    for (int j = x; j < tamanhoFavorita; j++) {
                        if(favoritaRepository.findAll().get(j).getIdIdeia().equals(id)) {
                            marcada =  favoritaRepository.findAll().get(j).getMarcada();
                            if (marcada.equals("N")) {
                                jsonObject.put("marcada", marcada);
                            } else {
                                jsonObject.put("marcada", "S");
                            }
                        }
                    }
                    jsonArrayTodasIdeias.add(jsonObject);
                }
            }
        return jsonArrayTodasIdeias;
    }

    public JSONArray ultimasCadastradas(){

        int tamanho = ideiaRepository.findByUltimasCadastradas().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByUltimasCadastradas().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByUltimasCadastradas().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByUltimasCadastradas().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByUltimasCadastradas().get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaRepository.findByUltimasCadastradas().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByUltimasCadastradas().get(i).getSituacao());

                long id = ideiaRepository.findByUltimasCadastradas().get(i).getId();

                int x = 0;
                String marcada = "";

                int iFavorita = favoritaRepository.findAll().size();

                for (int j = x; j < iFavorita; j++) {
                    if(favoritaRepository.findAll().get(j).getIdIdeia().equals(id)) {
                        marcada =  favoritaRepository.findAll().get(j).getMarcada();
                        if (marcada.equals("N")) {
                            jsonObject.put("marcada", marcada);
                        } else {
                            jsonObject.put("marcada", "S");
                        }
                    }
                }
                jsonArrayUltimasIdeiasCadastradas.add(jsonObject);
            }
        }
        return jsonArrayUltimasIdeiasCadastradas;
    }

    public JSONArray maisVotadas(){

        int tamanho = ideiaRepository.findByMaisVotadas().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByMaisVotadas().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByMaisVotadas().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByMaisVotadas().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByMaisVotadas().get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaRepository.findByMaisVotadas().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByMaisVotadas().get(i).getSituacao());

                long id = ideiaRepository.findByMaisVotadas().get(i).getId();

                int x = 0;
                String marcada = "";

                int iFavorita = favoritaRepository.findAll().size();

                for (int j = x; j < iFavorita; j++) {
                    if(favoritaRepository.findAll().get(j).getIdIdeia().equals(id)) {
                        marcada =  favoritaRepository.findAll().get(j).getMarcada();
                        if (marcada.equals("N")) {
                            jsonObject.put("marcada", marcada);
                        } else {
                            jsonObject.put("marcada", "S");
                        }
                    }
                }jsonArrayMaisVotadas.add(jsonObject);
            }
        }
        return jsonArrayMaisVotadas;
    }

    public Optional<Ideia> listaPorId(Long id){

        Optional<Ideia> ideia = ideiaRepository.findById(id);

        if(ideia == null) {
            throw new ResourceNotFoundExcepction("Ideia not found for Id " + id);
        }
        return ideia;
    }

    public void salvaIdeia(Ideia ideia, Long idUsuario, boolean isAdmin) {

        ideiaRepository.save(ideia);
        Favorita favorita = new Favorita();
        Long idUltimaIdeia = ideiaRepository.ultimaIdeiaCadastrada();

        favorita.setIdIdeia(idUltimaIdeia);
        favorita.setMarcada("N");
        favorita.setIdUser(idUsuario);
        favoritaRepository.save(favorita);
    }

    public void atualizarIdeia(Ideia ideia, Long idUsuario) {

        if (ideia.getComentarioAvaliador() != null) {
            throw new ResourceNotFoundExcepction("Comentario Avaliador nao preenchido");
        } else {
            ideiaRepository.save(ideia);
        }
    }

}
