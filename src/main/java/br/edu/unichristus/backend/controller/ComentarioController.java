package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.ComentarioDTO;
import br.edu.unichristus.backend.domain.dto.ComentarioLowDTO;
import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.service.ComentarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    private ComentarioService service;

    @Operation(summary = "Cadastra dados referentes ao comentário", tags = "Comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Comentário cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Texto, ID do livro ou ID do usuário está faltando."),
            @ApiResponse(responseCode = "404", description = "Livro ou Usuário não encontrado.")
    })
    @PostMapping
    public ComentarioDTO create(@RequestBody ComentarioDTO comentario){
        return service.create(comentario);
    }

    @Operation(summary = "Retorna todos os comentários cadastrados", tags = "Comentario")
    @GetMapping("/all")
    public List<ComentarioLowDTO> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Atualiza os dados de um comentário", tags = "Comentario")
    @PutMapping
    public ComentarioDTO update(@RequestBody ComentarioDTO comentario){
        return service.update(comentario);
    }

    @Operation(summary = "Deleta um comentário baseado no ID", tags = "Comentario")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteComentarioById(id);
    }

    @Operation(summary = "Retorna os dados de um comentário baseado no ID", tags = "Comentario")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados do comentário (LowDTO)"),
            @ApiResponse(responseCode = "404",
                    description = "O comentário com o id informado não foi encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/{id}")
    public ComentarioLowDTO findById(@PathVariable(name = "id") Long id){ // <-- MUDANÇA AQUI
        return service.findComentarioById(id);
    }
}