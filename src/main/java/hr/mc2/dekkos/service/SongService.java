package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.SongRepository;
import hr.mc2.dekkos.model.Song;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    public Optional<Song> getCurrentSong() {
        return songRepository.findByIsCurrentlyPlaying(true);
    }

    public void playSong(Song nextSong) {
        var currentSongOptional = songRepository.findByIsCurrentlyPlaying(true);

        if (currentSongOptional.isPresent()) {
            var currentSong = currentSongOptional.get();
            currentSong.setIsPlayed(true);
            currentSong.setIsCurrentlyPlaying(false);
            songRepository.save(currentSong);
        }

        SongBroadcaster.broadcast(nextSong);

        nextSong.setIsPlayed(true);
        nextSong.setIsCurrentlyPlaying(true);
        songRepository.save(nextSong);
    }


}
