package com.br.nossas.ideias.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Controller;

@Controller
public class DashboardController {

    private final IdeiaController ideiaController;
    private final UserController userController;
    private final JSONArray jsonArrayIdeias = new JSONArray();
    private final JSONObject jsonObjectDashboard = new JSONObject();

    public DashboardController(IdeiaController ideiaController, UserController userController) {
        this.ideiaController = ideiaController;
        this.userController = userController;
    }

    public JSONObject listaTodasIdeiasFavoritas(String token) {

        jsonArrayIdeias.appendElement(ideiaController.listaTodasdeias());

        jsonObjectDashboard.put("ideia",jsonArrayIdeias);

        /*String username = userController.getUserName(token);

        if(userController.isAdmin(token)) {
            jsonObjectDashboard.put("isUser", "S");
        } else {
            jsonObjectDashboard.put("isUser", "N");
        }

        jsonObjectDashboard.put("ideias",ideiaController.listaTodasdeias(pageable));

        if(favoritasController.listaTodasdeias(pageable, username).size() > 0) {

            for (int i = 0; i < ideiaController.listaTodasdeias(pageable).size(); i++) {

                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", ideiaController.listaTodasdeias(pageable).get(i).getId());
                jsonObject.put("nome", ideiaController.listaTodasdeias(pageable).get(i).getNome());
                jsonObject.put("descricao", ideiaController.listaTodasdeias(pageable).get(i).getDescricao());
                jsonObject.put("comentarioAvaliador", ideiaController.listaTodasdeias(pageable).get(i).getComentarioAvaliador());
                jsonObject.put("ativa", ideiaController.listaTodasdeias(pageable).get(i).getAtiva());
                jsonObject.put("situacao", ideiaController.listaTodasdeias(pageable).get(i).getSituacao());

                long id = ideiaController.listaTodasdeias(pageable).get(i).getId();

                int x = 0;
                String marcada = "";

                for (int j = x; j < favoritasController.listaTodasdeias(pageable,username).size(); j++) {
                    if(favoritasController.listaTodasdeias(pageable,username).get(j).getIdIdeia().equals(id)) {
                        marcada =  favoritasController.listaTodasdeias(pageable,username).get(j).getMarcada();
                    }
                }

                if (marcada.equals("N")) {
                    jsonObject.put("marcada", marcada);
                } else {
                    jsonObject.put("marcada", "S");
                }

                jsonArrayIdeias.add(jsonObject);
            }*/


        return jsonObjectDashboard;
    }

}
