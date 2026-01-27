package com.java.Finsales.CRM.API.domain.repository;

import com.java.Finsales.CRM.API.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    boolean existByDocumento(String documento);
}
