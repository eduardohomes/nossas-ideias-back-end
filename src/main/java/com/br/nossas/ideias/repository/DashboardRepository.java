package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.Dashboard;
import com.br.nossas.ideias.model.Ideia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Long> {

    @Query(value = "SELECT i.*,f.marcada FROM ideia i join favorita f on i.id = f.id_ideia where username = ?", nativeQuery = true)
    List<Dashboard> findByIdIdeiaFavorito(Long idUsuario);

}
