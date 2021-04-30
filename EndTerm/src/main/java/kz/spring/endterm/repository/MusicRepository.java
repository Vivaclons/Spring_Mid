package kz.spring.endterm.repository;

import kz.spring.endterm.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    Music getMusicById(Long id);
    List<Music> getMusicsByNameContainsOrAuthorContains(String name, String author);
}
