package cous.stic4.scolariteapi.dao;

import cous.stic4.scolariteapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUtilisateur extends JpaRepository<User, Integer> {
    public User findByUsername(String login);
    public User findByEmail(String email);
}
