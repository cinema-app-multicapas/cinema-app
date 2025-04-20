package com.puj.cinemapp.controller;

import com.puj.cinemapp.domain.dto.DirectorDTO;
import com.puj.cinemapp.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping
    public List<DirectorDTO> getAllDirectors() {
        return directorService.getAllDirectors();
    }

    @GetMapping("/{id}")
    public DirectorDTO getDirectorById(@PathVariable Long id) {
        return directorService.getDirectorById(id);
    }

    @PostMapping
    public DirectorDTO createDirector(@RequestBody DirectorDTO directorDTO) {
        return directorService.createDirector(directorDTO);
    }

    @PutMapping("/{id}")
    public DirectorDTO updateDirector(@PathVariable Long id, @RequestBody DirectorDTO directorDTO) {
        return directorService.updateDirector(id, directorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDirector(@PathVariable Long id) {
        String result = directorService.deleteDirector(id);

        if (result.equals("Director eliminado exitosamente.")) {
            return ResponseEntity.ok(result);
        } else if (result.startsWith("No se puede eliminar porque hay peliculas asociadas")) {
            return ResponseEntity.badRequest().body(result); // 400
        } else {
            return ResponseEntity.status(404).body(result); // 404
        }
    }

}