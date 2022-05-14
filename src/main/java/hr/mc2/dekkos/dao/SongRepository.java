package hr.mc2.dekkos.dao;

import hr.mc2.dekkos.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, String> {
}
