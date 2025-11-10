package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.EditoraDTO;
import br.edu.unichristus.backend.domain.dto.EditoraLowDTO;
import br.edu.unichristus.backend.domain.model.Editora;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.EditoraRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository repository;

    // CREATE
    public EditoraDTO create(EditoraDTO dto){
        if(dto.getNome() == null || dto.getNome().isBlank()){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.editora.badrequest",
                    "O nome da editora é obrigatório.");
        }
        if(dto.getNome().length() > 100){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.editora.badrequest",
                    "O limite de caracteres do nome da editora é 100");
        }

        var editora = MapperUtil.parseObject(dto, Editora.class);
        var editoraPersistida = repository.save(editora);

        return MapperUtil.parseObject(editoraPersistida, EditoraDTO.class);
    }

    // READ ALL
    public List<EditoraLowDTO> getAll(){
        return MapperUtil.parseListObjects(
                repository.findAll(), EditoraLowDTO.class);
    }

    // UPDATE
    public EditoraDTO update(EditoraDTO dto){
        findEditoraById(dto.getId());

        var editora = MapperUtil.parseObject(dto, Editora.class);
        var editoraPersistida = repository.save(editora);
        return MapperUtil.parseObject(editoraPersistida, EditoraDTO.class);
    }

    // DELETE
    public void deleteEditoraById(Long id){
        findEditoraById(id);

        repository.deleteById(id);
    }

    // READ BY ID
    public EditoraDTO findEditoraById(Long id){
        var editora = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.editora.notfound",
                        "A editora com o id informado não foi encontrada")
        );
        return MapperUtil.parseObject(editora, EditoraDTO.class);
    }
}