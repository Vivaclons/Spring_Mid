package kz.spring.endterm.controller;

import kz.spring.endterm.entity.Music;
import kz.spring.endterm.service.iservice.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/musics")
public class MusicController {
    @Autowired
    private IMusicService iMusicService;

    // GET - 3
    @GetMapping("")
    public ResponseEntity<?> findMusicByNameOrAuthor(@RequestParam("name") String name, @RequestParam("author") String author) {
        return ResponseEntity.ok(iMusicService.findMusicByNameOrAuthor(name, author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMusicById(@PathVariable() Long id) {
        return ResponseEntity.ok(iMusicService.getMusicById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllMusics() {
        return ResponseEntity.ok(iMusicService.getAll());
    }

    // POST
    @PostMapping("/action/create")
    public ResponseEntity<?> createNewMusic(@RequestBody Music music) {
        return ResponseEntity.ok(iMusicService.createNew(music));
    }

    // PUT
    @PutMapping("/action/update")
    public ResponseEntity<?> updateMusic(@RequestBody Music music) {
        return ResponseEntity.ok(iMusicService.update(music));
    }

    // DELETE
    @DeleteMapping("/action/delete/{id}")
    public void deleteMusicById(@PathVariable("id") Long id) {
        iMusicService.deleteMusicById(id);
    }

    // PATCH
    @PatchMapping("/action/update/{id}")
    public ResponseEntity<?> updateMusicPrice(@PathVariable("id") Long id, @RequestParam("min") Integer min) {
        return ResponseEntity.ok(iMusicService.updateMusicMin(id, min));
    }
}
