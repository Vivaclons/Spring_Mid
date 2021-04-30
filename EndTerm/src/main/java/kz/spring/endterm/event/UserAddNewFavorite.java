package kz.spring.endterm.event;

import kz.spring.endterm.entity.Music;
import kz.spring.endterm.entity.User;
import org.springframework.context.ApplicationEvent;

import java.awt.print.Book;

public class UserAddNewFavorite extends ApplicationEvent {
    private User user;
    private Music music;

    public UserAddNewFavorite(Object source, User user, Music music) {
        super(source);
        this.user = user;
        this.music = music;
    }

    public User getUser() {
        return user;
    }

    public Music getMusic() {
        return music;
    }
}