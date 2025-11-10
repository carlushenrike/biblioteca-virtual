package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.CategoriaDTO;
import br.edu.unichristus.backend.domain.dto.CategoriaLowDTO;
import br.edu.unichristus.backend.domain.model.Categoria;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.CategoriaRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    // CREATE
    public CategoriaDTO create(CategoriaDTO dto){
        if(dto.getNome() == null || dto.getNome().isBlank()){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O nome da categoria é obrigatório.");
        }
        if(dto.getNome().length() > 100){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.categoria.badrequest",
                    "O limite de caracteres do nome da categoria é 100");
        }

        var categoria = MapperUtil.parseObject(dto, Categoria.class);
        var categoriaPersistida = repository.save(categoria);

        return MapperUtil.parseObject(categoriaPersistida, CategoriaDTO.class);
    }

    // READ ALL
    public List<CategoriaLowDTO> getAll(){
        return MapperUtil.parseListObjects(
                repository.findAll(), CategoriaLowDTO.class);
    }

    // UPDATE
    public CategoriaDTO update(CategoriaDTO dto){
        findCategoriaById(dto.getId());

        var categoria = MapperUtil.parseObject(dto, Categoria.class);
        var categoriaPersistida = repository.save(categoria);
        return MapperUtil.parseObject(categoriaPersistida, CategoriaDTO.class);
    }

    // DELETE
    public void deleteCategoriaById(Long id){
        findCategoriaById(id);

        repository.deleteById(id);
    }

    // READ BY ID
    public CategoriaDTO findCategoriaById(Long id){
        var categoria = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.categoria.notfound",
                        "A categoria com o id informado não foi encontrada")
        );
        return MapperUtil.parseObject(categoria, CategoriaDTO.class);
    }
}