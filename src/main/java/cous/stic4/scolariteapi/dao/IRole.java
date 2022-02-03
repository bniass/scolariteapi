package cous.stic4.scolariteapi.dao;



import cous.stic4.scolariteapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRole extends JpaRepository<Role, Integer> {
    public Role findRoleByName(String name);
}
