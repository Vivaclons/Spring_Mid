package kz.spring.endterm.service;

import kz.spring.endterm.entity.Music;
import kz.spring.endterm.repository.MusicRepository;
import kz.spring.endterm.service.iservice.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService implements IMusicService {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public List<Music> getAll() {
        return musicRepository.findAll();
    }

    @Override
    public List<Music> findMusicByNameOrAuthor(String name, String author) {
        return musicRepository.getMusicsByNameContainsOrAuthorContains(name, author);
    }

    @Override
    public Music getMusicById(Long id) {
        return musicRepository.getMusicById(id);
    }

    @Override
    public Music createNew(Music music) {
        return musicRepository.saveAndFlush(music);
    }

    @Override
    public Music update(Music music) {
        return musicRepository.saveAndFlush(music);
    }

    @Override
    public Music updateMusicMin(Long bookId, Integer min) {
        Music music = musicRepository.getOne(bookId);
        music.setMin(min);
        return musicRepository.saveAndFlush(music);
    }

    @Override
    public void deleteMusicById(Long id) {
        musicRepository.deleteById(id);
    }
}
