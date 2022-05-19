package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.SongRepository;
import hr.mc2.dekkos.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SongService {
    final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }


}
