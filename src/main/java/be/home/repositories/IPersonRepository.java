package be.home.repositories;

import be.home.domain.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Personne, String> {
}
