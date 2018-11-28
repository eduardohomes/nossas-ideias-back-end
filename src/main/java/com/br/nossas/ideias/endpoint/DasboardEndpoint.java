package com.br.nossas.ideias.endpoint;

import com.br.nossas.ideias.controller.DashboardController;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/dashboard")
public class DasboardEndpoint {

    private  final DashboardController dashboardController;
    private static final JSONArray jsonArrayIdeias = new JSONArray();
    private static final JSONObject jsonObjectDashboard = new JSONObject();

    public DasboardEndpoint(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @GetMapping
    public ResponseEntity<?> listAll(@RequestHeader HttpHeaders httpHeaders) {

        Map<String,String> headerMap=httpHeaders.toSingleValueMap();

        String token = headerMap.get("token");

        jsonObjectDashboard.put("ideias", dashboardController.listaTodasIdeiasFavoritas(token));

        return new ResponseEntity<>(jsonObjectDashboard, HttpStatus.OK);
    }
}
