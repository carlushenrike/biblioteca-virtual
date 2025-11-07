package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.ComentarioDTO;
import br.edu.unichristus.backend.domain.dto.ComentarioLowDTO;
import br.edu.unichristus.backend.domain.model.Comentario;
import br.edu.unichristus.backend.domain.model.Livro;
import br.edu.unichristus.backend.domain.model.User;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.ComentarioRepository;
import br.edu.unichristus.backend.repository.LivroRepository;
import br.edu.unichristus.backend.repository.UserRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    @Transactional
    public ComentarioDTO create(ComentarioDTO dto) {
        if (dto.getTexto() == null || dto.getTexto().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.comentario.badrequest",
                    "O texto do comentário é obrigatório.");
        }
        if (dto.getLivroId() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.comentario.badrequest", "O ID do livro é obrigatório.");
        }
        if (dto.getUserId() == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.comentario.badrequest", "O ID do usuário é obrigatório.");
        }

        var comentario = MapperUtil.parseObject(dto, Comentario.class);

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound", "Livro não encontrado."));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.user.notfound", "Usuário não encontrado."));

        //associando..
        comentario.setLivro(livro);
        comentario.setUsuario(user);

        var comentarioPersistido = repository.save(comentario);
        return MapperUtil.parseObject(comentarioPersistido, ComentarioDTO.class);
    }

    // READ ALL
    public List<ComentarioLowDTO> getAll() {
        return MapperUtil.parseListObjects(
                repository.findAll(), ComentarioLowDTO.class);
    }

    // UPDATE
    @Transactional
    public ComentarioDTO update(ComentarioDTO dto) {
        var comentario = repository.findById(dto.getId()).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.comentario.notfound", "Comentário não encontrado.")
        );

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound", "Livro não encontrado."));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.user.notfound", "Usuário não encontrado."));

        comentario.setTitulo(dto.getTitulo());
        comentario.setTexto(dto.getTexto());
        comentario.setNota(dto.getNota());
        comentario.setDataPostagem(dto.getDataPostagem());
        comentario.setLivro(livro);
        comentario.setUsuario(user);

        var comentarioPersistido = repository.save(comentario);
        return MapperUtil.parseObject(comentarioPersistido, ComentarioDTO.class);
    }

    // DELETE
    public void deleteComentarioById(Long id) {
        findComentarioById(id);
        repository.deleteById(id);
    }

    // READ BY ID
    public ComentarioLowDTO findComentarioById(Long id) {
        var comentario = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.comentario.notfound",
                        "O comentário com o id informado não foi encontrado")
        );
        return MapperUtil.parseObject(comentario, ComentarioLowDTO.class);
    }
}