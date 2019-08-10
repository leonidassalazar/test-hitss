package com.test.hitss.claro.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.test.hitss.claro.model.Mobile;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface MobileRepository extends JpaRepository<Mobile, Long> {
}
