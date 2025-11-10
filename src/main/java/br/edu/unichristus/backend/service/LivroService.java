package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.LivroDTO;
import br.edu.unichristus.backend.domain.dto.LivroLowDTO;
import br.edu.unichristus.backend.domain.model.Autor;
import br.edu.unichristus.backend.domain.model.Categoria;
import br.edu.unichristus.backend.domain.model.Editora;
import br.edu.unichristus.backend.domain.model.Livro;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.AutorRepository;
import br.edu.unichristus.backend.repository.CategoriaRepository;
import br.edu.unichristus.backend.repository.EditoraRepository;
import br.edu.unichristus.backend.repository.LivroRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @Autowired
    private AutorRepository autorRepository;

    // CREATE
    @Transactional
    public LivroDTO create(LivroDTO dto) {
        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.livro.badrequest",
                    "O título do livro é obrigatório.");
        }

        var livro = MapperUtil.parseObject(dto, Livro.class);

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.categoria.notfound", "Categoria não encontrada."));

        Editora editora = editoraRepository.findById(dto.getEditoraId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.editora.notfound", "Editora não encontrada."));

        Set<Autor> autores = new HashSet<>(autorRepository.findAllById(dto.getAutorIds()));
        if (autores.size() != dto.getAutorIds().size()) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "unichristus.service.autor.notfound", "Um ou mais autores não foram encontrados.");
        }

        livro.setCategoria(categoria);
        livro.setEditora(editora);
        livro.setAutores(autores);

        var livroPersistido = repository.save(livro);

        return MapperUtil.parseObject(livroPersistido, LivroDTO.class);
    }

    // READ ALL
    public List<LivroLowDTO> getAll() {
        return MapperUtil.parseListObjects(
                repository.findAll(), LivroLowDTO.class);
    }

    // UPDATE
    @Transactional
    public LivroDTO update(LivroDTO dto) {
        var livro = repository.findById(dto.getId()).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound", "Livro não encontrado.")
        );

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.categoria.notfound", "Categoria não encontrada."));

        Editora editora = editoraRepository.findById(dto.getEditoraId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.editora.notfound", "Editora não encontrada."));

        Set<Autor> autores = new HashSet<>(autorRepository.findAllById(dto.getAutorIds()));
        if (autores.size() != dto.getAutorIds().size()) {
            throw new ApiException(HttpStatus.NOT_FOUND,
                    "unichristus.service.autor.notfound", "Um ou mais autores não foram encontrados.");
        }

        livro.setTitulo(dto.getTitulo());
        livro.setIsbn(dto.getIsbn());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setNumPaginas(dto.getNumPaginas());
        livro.setSinopse(dto.getSinopse());
        livro.setCategoria(categoria);
        livro.setEditora(editora);
        livro.setAutores(autores);

        var livroPersistido = repository.save(livro);
        return MapperUtil.parseObject(livroPersistido, LivroDTO.class);
    }

    // DELETE
    public void deleteLivroById(Long id) {
        findLivroById(id);
        repository.deleteById(id);
    }

    // READ BY ID
    public LivroDTO findLivroById(Long id) {
        var livro = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.livro.notfound",
                        "O livro com o id informado não foi encontrado")
        );

        var dto = MapperUtil.parseObject(livro, LivroDTO.class);
        dto.setCategoriaId(livro.getCategoria().getId());
        dto.setEditoraId(livro.getEditora().getId());
        dto.setAutorIds(
                livro.getAutores().stream()
                        .map(autor -> autor.getId())
                        .collect(Collectors.toSet())
        );

        return dto;
    }
}