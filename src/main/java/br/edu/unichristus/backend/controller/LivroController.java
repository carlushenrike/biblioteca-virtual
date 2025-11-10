package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.domain.dto.LivroDTO;
import br.edu.unichristus.backend.domain.dto.LivroLowDTO;
import br.edu.unichristus.backend.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/livro")
public class LivroController {

    @Autowired
    private LivroService service;

    @Operation(summary = "Cadastra dados referentes ao livro", tags = "Livro")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Livro cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O título do livro está em branco."),
            @ApiResponse(responseCode = "404", description = "Categoria, Editora ou um dos Autores não encontrado.")
    })
    @PostMapping
    public LivroDTO create(@RequestBody LivroDTO livro){
        return service.create(livro);
    }

    @Operation(summary = "Retorna todos os livros cadastrados", tags = "Livro")
    @GetMapping("/all")
    public List<LivroLowDTO> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Atualiza os dados de um livro", tags = "Livro")
    @PutMapping
    public LivroDTO update(@RequestBody LivroDTO livro){
        return service.update(livro);
    }

    @Operation(summary = "Deleta um livro baseado no ID", tags = "Livro")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteLivroById(id);
    }

    @Operation(summary = "Retorna os dados de um livro baseado no ID", tags = "Livro")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados do livro (versão 'Low')"),
            @ApiResponse(responseCode = "404",
                    description = "O livro com o id informado não foi encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/{id}")
    public LivroDTO findById(@PathVariable(name = "id") Long id){
        return service.findLivroById(id);
    }
}