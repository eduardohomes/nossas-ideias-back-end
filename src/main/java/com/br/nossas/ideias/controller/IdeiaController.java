package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.model.Ideia;
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

    public JSONArray listarFavoritas(){

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

    public JSONArray listaUltimasIdeias(){

        int tamanho = ideiaRepository.findByUltimasIdeias().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByUltimasIdeias().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByUltimasIdeias().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByUltimasIdeias().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByUltimasIdeias().get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaRepository.findByUltimasIdeias().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByUltimasIdeias().get(i).getSituacao());

                long id = ideiaRepository.findByUltimasIdeias().get(i).getId();

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

    public JSONArray listaEmAlta(){

        int tamanho = ideiaRepository.findByEmAlta().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByEmAlta().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByEmAlta().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByEmAlta().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByEmAlta().get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaRepository.findByEmAlta().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByEmAlta().get(i).getSituacao());

                long id = ideiaRepository.findByEmAlta().get(i).getId();

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

    public JSONArray listaBuscaAssunto(String nome){

        int tamanho = ideiaRepository.findByIdeia(nome).size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByIdeia(nome).get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByIdeia(nome).get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByIdeia(nome).get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByIdeia(nome).get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaRepository.findByIdeia(nome).get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByIdeia(nome).get(i).getSituacao());

                long id = ideiaRepository.findByIdeia(nome).get(i).getId();

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

    public void salvaIdeia(Ideia ideia, Long idUsuario) {

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
