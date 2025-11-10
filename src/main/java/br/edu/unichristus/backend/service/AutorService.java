package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.AutorDTO;
import br.edu.unichristus.backend.domain.dto.AutorLowDTO;
import br.edu.unichristus.backend.domain.model.Autor;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.AutorRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    // CREATE
    public AutorDTO create(AutorDTO dto){
        if(dto.getNome() == null || dto.getNome().isBlank()){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.autor.badrequest",
                    "O nome do autor é obrigatório.");
        }
        if(dto.getNome().length() > 100){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.autor.badrequest",
                    "O limite de caracteres do nome do autor é 100");
        }

        var autor = MapperUtil.parseObject(dto, Autor.class);
        var autorPersistido = repository.save(autor);

        return MapperUtil.parseObject(autorPersistido, AutorDTO.class);
    }

    // READ ALL
    public List<AutorLowDTO> getAll(){
        return MapperUtil.parseListObjects(
                repository.findAll(), AutorLowDTO.class);
    }

    // UPDATE
    public AutorDTO update(AutorDTO dto){
        findAutorById(dto.getId());

        var autor = MapperUtil.parseObject(dto, Autor.class);
        var autorPersistido = repository.save(autor);
        return MapperUtil.parseObject(autorPersistido, AutorDTO.class);
    }

    // DELETE
    public void deleteAutorById(Long id){
        findAutorById(id);
        repository.deleteById(id);
    }

    // READ BY ID
    public AutorDTO findAutorById(Long id){
        var autor = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.autor.notfound",
                        "O autor com o id informado não foi encontrado")
        );
        return MapperUtil.parseObject(autor, AutorDTO.class);
    }
}