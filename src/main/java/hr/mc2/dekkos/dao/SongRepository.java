package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SongRepository extends CrudRepository<Song, String> {
    Optional<Song> findByIsCurrentlyPlaying(Boolean flag);
}
