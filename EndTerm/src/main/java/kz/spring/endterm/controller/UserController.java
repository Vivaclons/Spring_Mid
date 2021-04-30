package kz.spring.endterm.controller;

import kz.spring.endterm.entity.Music;
import kz.spring.endterm.entity.User;
import kz.spring.endterm.service.iservice.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService iUserService;

    // GET
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(iUserService.getAll());
    }

    // POST
    @PostMapping("/create")
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        return ResponseEntity.ok(iUserService.createNew(user));
    }

    // DELETE
    @DeleteMapping("/{id}/delete/favoriteMusics")
    public ResponseEntity<?> deleteBookFromFavoriteBook(@PathVariable("id") Long id, @RequestBody Music music) {
        return ResponseEntity.ok(iUserService.deleteMusicFromFavorites(id, music));
    }

    // PATCH - 2
    @PatchMapping("/{id}/update/username")
    public ResponseEntity<?> updateUserPassword(@PathVariable("id") Long id, @RequestBody String username) {
        return ResponseEntity.ok(iUserService.updateUsername(id, username));
    }

    @PatchMapping("/{id}/update/add-music-to-favorites")
    public ResponseEntity<?> addMusicToFavorites(@PathVariable("id") Long id, @RequestBody Music music) {
        return ResponseEntity.ok(iUserService.addMusicToFavorites(id, music));
    }
}
