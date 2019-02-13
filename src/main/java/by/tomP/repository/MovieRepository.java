package by.tomP.repository;


import by.tomP.entity.MoviesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MoviesData, String> {
}
