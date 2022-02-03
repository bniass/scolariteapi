package cous.stic4.scolariteapi.dao;


import cous.stic4.scolariteapi.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {

}
