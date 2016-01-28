package fr.arlefebvre.leagueoflegends.repository;

import fr.arlefebvre.leagueoflegends.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Arthur on 27/01/2016.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
