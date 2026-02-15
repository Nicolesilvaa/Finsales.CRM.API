package com.java.Finsales.CRM.API.domain.repository;

import com.java.Finsales.CRM.API.domain.model.Oportunidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OportunidadeRepository extends JpaRepository<Oportunidade,Long> {
}
