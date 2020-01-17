package co.rodnan.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT new PostDTO(p.title, p.description) FROM Post p WHERE p.id = ?1")
    PostDTO findDtoById(Long id);

}
