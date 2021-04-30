package kz.spring.endterm.service.iservice;

import kz.spring.endterm.entity.Music;
import kz.spring.endterm.entity.User;

import java.util.List;

public interface IUserService {
    List<User> getAll();
    User createNew(User user);
    User updateUsername(Long id, String username);
    User addMusicToFavorites(Long userId, Music music);
    User deleteMusicFromFavorites(Long userId, Music music);
}
