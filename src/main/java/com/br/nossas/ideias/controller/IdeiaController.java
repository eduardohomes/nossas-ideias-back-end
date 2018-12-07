package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.error.ResourceNotFoundExcepction;
import com.br.nossas.ideias.model.Favorita;
import com.br.nossas.ideias.model.Ideia;
import com.br.nossas.ideias.repository.*;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class IdeiaController {

    private final IdeiaRepository ideiaRepository;
    private final FavoritaRepository favoritaRepository;
    private final CategoriaRepository categoriaRepository;
    private final VotoRepository votoRepository;
    private final ComentarioRepository comentarioRepository;

    public IdeiaController(IdeiaRepository ideiaRepository, FavoritaRepository favoritaRepository, CategoriaRepository categoriaRepository, VotoRepository votoRepository1, ComentarioRepository comentarioRepository) {
        this.ideiaRepository = ideiaRepository;
        this.favoritaRepository = favoritaRepository;
        this.categoriaRepository = categoriaRepository;
        this.votoRepository = votoRepository1;
        this.comentarioRepository = comentarioRepository;
    }

    public JSONArray listarFavoritas(Long idUser){
        JSONArray jsonArrayFavoritas = new JSONArray();

        int tamanho = ideiaRepository.findByFavoritas(idUser).size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByFavoritas(idUser).get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByFavoritas(idUser).get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByFavoritas(idUser).get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByFavoritas(idUser).get(i).getComentario_Avaliador());
                jsonObject.put("ativa", ideiaRepository.findByFavoritas(idUser).get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByFavoritas(idUser).get(i).getSituacao());

                long idIdeia = ideiaRepository.findByFavoritas(idUser).get(i).getId();

                long tamanhoVotoSim = votoRepository.countByIdIdeiaAndVoto(idIdeia, "S");
                long tamanhoVotoNao = votoRepository.countByIdIdeiaAndVoto(idIdeia, "N");
                long tamanhoComentario = comentarioRepository.countByIdIdeia(idIdeia);

                if(tamanhoVotoSim > 0 ) {
                    jsonObject.put("quantidadeSim", tamanhoVotoSim);
                } else {
                    jsonObject.put("quantidadeSim", 0);
                }
                if(tamanhoVotoNao > 0 ) {
                    jsonObject.put("quantidadeNao", tamanhoVotoNao);
                } else {
                    jsonObject.put("quantidadeNao", 0);
                }
                if(tamanhoComentario > 0 ) {
                    jsonObject.put("quantidadeComentario", tamanhoComentario);
                } else {
                    jsonObject.put("quantidadeComentario", 0);
                }

                jsonArrayFavoritas.add(jsonObject);
            }
        }

        return jsonArrayFavoritas;
    }

    public JSONArray listaPendenteAvaliacao(){
        JSONArray jsonArrayPendenteAvaliacao = new JSONArray();
        int tamanho = ideiaRepository.findByPendenteAvaliacao().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByPendenteAvaliacao().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByPendenteAvaliacao().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByPendenteAvaliacao().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByPendenteAvaliacao().get(i).getComentario_Avaliador());
                jsonObject.put("ativa", ideiaRepository.findByPendenteAvaliacao().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByPendenteAvaliacao().get(i).getSituacao());

                jsonArrayPendenteAvaliacao.add(jsonObject);
            }
        }
        return jsonArrayPendenteAvaliacao;
    }

    public JSONArray listaUltimasIdeias(){
        JSONArray jsonArrayUltimasIdeias = new JSONArray();

        int tamanho = ideiaRepository.findByUltimasIdeias().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByUltimasIdeias().get(i).getId());
                jsonObject.put("ativa", ideiaRepository.findByUltimasIdeias().get(i).getAtiva());
                jsonObject.put("comentario_avaliador",ideiaRepository.findByUltimasIdeias().get(i).getComentario_Avaliador());
                jsonObject.put("descricao",ideiaRepository.findByUltimasIdeias().get(i).getDescricao());
                jsonObject.put("nome", ideiaRepository.findByUltimasIdeias().get(i).getNome());
                jsonObject.put("situacao", ideiaRepository.findByUltimasIdeias().get(i).getSituacao());
                jsonObject.put("id_categoria", ideiaRepository.findByUltimasIdeias().get(i).getIdCategoria());

                Long idIdeia = ideiaRepository.findByUltimasIdeias().get(i).getId();
                long tamanhoVotoSim = votoRepository.countByIdIdeiaAndVoto(idIdeia, "S");
                long tamanhoVotoNao = votoRepository.countByIdIdeiaAndVoto(idIdeia, "N");
                long tamanhoComentario = comentarioRepository.countByIdIdeia(idIdeia);

                if(tamanhoVotoSim > 0 ) {
                    jsonObject.put("quantidadeSim", tamanhoVotoSim);
                } else {
                    jsonObject.put("quantidadeSim", 0);
                }
                if(tamanhoVotoNao > 0 ) {
                    jsonObject.put("quantidadeNao", tamanhoVotoNao);
                } else {
                    jsonObject.put("quantidadeNao", 0);
                }
                if(tamanhoComentario > 0 ) {
                    jsonObject.put("quantidadeComentario", tamanhoComentario);
                } else {
                    jsonObject.put("quantidadeComentario", 0);
                }

                jsonArrayUltimasIdeias.add(jsonObject);
            }

        }

        return jsonArrayUltimasIdeias;
    }

    public JSONArray listaEmAlta(){
        JSONArray jsonArrayMaisVotadas = new JSONArray();
        int tamanho = ideiaRepository.findByEmAlta().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByEmAlta().get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByEmAlta().get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByEmAlta().get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByEmAlta().get(i).getComentario_Avaliador());
                jsonObject.put("ativa", ideiaRepository.findByEmAlta().get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByEmAlta().get(i).getSituacao());

                long idIdeia = ideiaRepository.findByEmAlta().get(i).getId();
                long tamanhoVotoSim = votoRepository.countByIdIdeiaAndVoto(idIdeia, "S");
                long tamanhoVotoNao = votoRepository.countByIdIdeiaAndVoto(idIdeia, "N");
                long tamanhoComentario = comentarioRepository.countByIdIdeia(idIdeia);

                if(tamanhoVotoSim > 0 ) {
                    jsonObject.put("quantidadeSim", tamanhoVotoSim);
                } else {
                    jsonObject.put("quantidadeSim", 0);
                }
                if(tamanhoVotoNao > 0 ) {
                    jsonObject.put("quantidadeNao", tamanhoVotoNao);
                } else {
                    jsonObject.put("quantidadeNao", 0);
                }
                if(tamanhoComentario > 0 ) {
                    jsonObject.put("quantidadeComentario", tamanhoComentario);
                } else {
                    jsonObject.put("quantidadeComentario", 0);
                }

                int x = 0;
                String marcada = "";

                int iFavorita = favoritaRepository.findAll().size();

                for (int j = x; j < iFavorita; j++) {
                    if(favoritaRepository.findAll().get(j).getIdIdeia().equals(idIdeia)) {
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

    public JSONArray listaBuscaAssunto(String nome, Long idCategoria){
        JSONArray jsonArrayPorAssunto = new JSONArray();
        int tamanho = ideiaRepository.buscaPorNomeCategoria(idCategoria,nome).size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("nome", ideiaRepository.buscaPorNomeCategoria(idCategoria,nome).get(i).getNome());
                jsonObject.put("situacao", ideiaRepository.buscaPorNomeCategoria(idCategoria,nome).get(i).getSituacao());
                jsonObject.put("descricao", ideiaRepository.buscaPorNomeCategoria(idCategoria,nome).get(i).getDescricao());

                jsonArrayPorAssunto.add(jsonObject);
            }
        }
        return jsonArrayPorAssunto;
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

    public void atualizarIdeia(Ideia ideia) {
        ideiaRepository.save(ideia);
    }

    public JSONArray listaPorCategoria(){
        JSONArray jsonArrayPorCategorias = new JSONArray();
        int tamanho = categoriaRepository.listaPorCategoria().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                Object[] obj = (Object[]) categoriaRepository.listaPorCategoria().get(i);

                jsonObject.put("id", obj[0]);
                jsonObject.put("nome", obj[1]);
                jsonObject.put("quantidade", obj[2]);
                jsonArrayPorCategorias.add(jsonObject);
            }
        }
        return jsonArrayPorCategorias;
    }

    public JSONArray buscaPorNome(String nome){
        JSONArray jsonArrayPorAssunto = new JSONArray();
        int tamanho = ideiaRepository.findByNomeContaining(nome).size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaRepository.findByNomeContaining(nome).get(i).getId());
                jsonObject.put("nome", ideiaRepository.findByNomeContaining(nome).get(i).getNome());
                jsonObject.put("descricao", ideiaRepository.findByNomeContaining(nome).get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaRepository.findByNomeContaining(nome).get(i).getComentario_Avaliador());
                jsonObject.put("ativa", ideiaRepository.findByNomeContaining(nome).get(i).getAtiva());
                jsonObject.put("situacao", ideiaRepository.findByNomeContaining(nome).get(i).getSituacao());

                jsonArrayPorAssunto.add(jsonObject);
            }
        }
        return jsonArrayPorAssunto;
    }
}
