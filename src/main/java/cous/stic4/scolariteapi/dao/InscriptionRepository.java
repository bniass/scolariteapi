package cous.stic4.scolariteapi.dao;


import cous.stic4.scolariteapi.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription,Long> {
    @Query("select i from Inscription i join i.classe c where c.id=:classe_id AND i.annee_academic=:annee")
    public List<Inscription> findEtudiantParClasseEtAnnee(long classe_id, String annee);

}
