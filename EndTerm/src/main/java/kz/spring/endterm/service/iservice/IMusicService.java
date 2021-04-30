package kz.spring.endterm.service.iservice;

import kz.spring.endterm.entity.Music;

import java.util.List;

public interface IMusicService {
    List<Music> getAll();
    List<Music> findMusicByNameOrAuthor(String name, String author);
    Music getMusicById(Long id);
    Music createNew(Music music);
    Music update(Music music);
    Music updateMusicMin(Long musicId, Integer min);
    void deleteMusicById(Long id);
}
