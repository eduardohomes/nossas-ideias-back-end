package com.br.nossas.ideias.controller;

import com.br.nossas.ideias.model.Voto;
import com.br.nossas.ideias.repository.VotoRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
public class VotoController {

    private final VotoRepository votoRepository;
    private final UserController userController;
    private JSONArray jsonArrayVotos;

    public VotoController(VotoRepository votoRepository, UserController userController) {
        this.votoRepository = votoRepository;
        this.userController = userController;
        jsonArrayVotos = new JSONArray();
    }

    public void salvaVoto(Voto voto, Long idUsuario) {
        voto.setIdUser(idUsuario);
        votoRepository.save(voto);
    }

    public JSONArray listaVotos(Long idUsuario){

        int tamanho = votoRepository.listaVotos (idUsuario).size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", votoRepository.findAll().get(i).getId());
                jsonObject.put("id_ideia", votoRepository.findAll().get(i).getIdIdeia());
                jsonObject.put("voto", votoRepository.findAll().get(i).getVoto());

                jsonArrayVotos.add(jsonObject);
            }
        }
        return jsonArrayVotos;
    }

    public JSONObject validaVoto(Long idUsuario, Voto voto) {

        JSONObject jsonObject = new JSONObject();

        boolean valida;
        int buscaVoto = votoRepository.validaVoto(idUsuario,voto.getIdIdeia()).size();

        if (buscaVoto >= 1) {
            jsonObject.put("Votou", "Voce ja votou");
        } else {
            jsonObject.put("Votou", "Voto salvo");
            voto.setIdUser(idUsuario);
            votoRepository.save(voto);
        }
        return jsonObject;
    }

    public Long listaVotosPorIdeia(Long idIdeia) {

       return votoRepository.countByIdIdeia(idIdeia);
    }

    public JSONArray contaVoto(){
        JSONArray jsonArrayVoto = new JSONArray();
        int tamanho = votoRepository.contaVoto().size();

        if(tamanho > 0) {

            for (int i = 0; i < tamanho; i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", votoRepository.contaVoto().get(i).getId());
                jsonObject.put("voto",votoRepository.contaVoto().get(i).getVoto());
                //jsonObject.put("quantidade",votoRepository.contaVoto().get(i).getQuantidade());
                jsonArrayVoto.add(jsonObject);
            }
        }
        return jsonArrayVoto;
    }

}
