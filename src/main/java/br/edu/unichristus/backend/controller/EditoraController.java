package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.EditoraDTO;
import br.edu.unichristus.backend.domain.dto.EditoraLowDTO;
import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.service.EditoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/editora")
public class EditoraController {

    @Autowired
    private EditoraService service;

    @Operation(summary = "Cadastra dados referentes à editora", tags = "Editora")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Editora cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O nome da editora está em branco ou excede 100 caracteres.")
    })
    @PostMapping
    public EditoraDTO create(@RequestBody EditoraDTO editora){
        return service.create(editora);
    }

    @Operation(summary = "Retorna todas as editoras cadastradas", tags = "Editora")
    @GetMapping("/all")
    public List<EditoraLowDTO> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Atualiza os dados de uma editora", tags = "Editora")
    @PutMapping
    public EditoraDTO update(@RequestBody EditoraDTO editora){
        return service.update(editora);
    }

    @Operation(summary = "Deleta uma editora baseado no ID", tags = "Editora")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteEditoraById(id);
    }

    @Operation(summary = "Retorna os dados de uma editora baseado no ID", tags = "Editora")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados da editora"),
            @ApiResponse(responseCode = "404",
                    description = "A editora com o id informado não foi encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/{id}")
    public EditoraDTO findById(@PathVariable(name = "id") Long id){
        return service.findEditoraById(id);
    }
}