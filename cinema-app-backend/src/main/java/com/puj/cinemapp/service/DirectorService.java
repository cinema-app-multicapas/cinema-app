package com.puj.cinemapp.service;

import com.puj.cinemapp.domain.dto.DirectorDTO;
import com.puj.cinemapp.domain.model.Director;
import com.puj.cinemapp.repository.DirectorRepository;
import com.puj.cinemapp.repository.MovieRepository;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;    

    public List<DirectorDTO> getAllDirectors() {
        return directorRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DirectorDTO getDirectorById(Long id) {
        return directorRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Transactional
    public DirectorDTO createDirector(DirectorDTO dto) {
        Director director = convertToEntity(dto);
        return convertToDTO(directorRepository.save(director));
    }

    @Transactional
    public DirectorDTO updateDirector(Long id, DirectorDTO dto) {
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();
            director.setName(dto.getName());
            director.setBirthDate(dto.getBirthDate());
            director.setNationality(dto.getNationality());
            director.setBiography(dto.getBiography());
            director.setPhotoUrl(dto.getPhotoUrl());

            return convertToDTO(directorRepository.save(director));
        }
        return null;
    }


    @Transactional
    public void deleteDirector(Long id) {
        if (directorRepository.existsById(id) && movieRepository.existsByDirectorId(id) == false) {
            directorRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("No se puede eliminar.");
        }
    }

    private DirectorDTO convertToDTO(Director director) {
        return modelMapper.map(director, DirectorDTO.class);
    }
    
    private Director convertToEntity(DirectorDTO dto) {
        return modelMapper.map(dto, Director.class);
    }
    
}