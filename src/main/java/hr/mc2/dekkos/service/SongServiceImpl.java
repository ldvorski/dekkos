package hr.mc2.dekkos.service;

import hr.mc2.dekkos.dao.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService{
    @Autowired
    SongRepository songRepository;
}
