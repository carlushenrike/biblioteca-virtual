package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.CategoriaDTO;
import br.edu.unichristus.backend.domain.dto.CategoriaLowDTO;
import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(summary = "Cadastra dados referentes à categoria", tags = "Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Categoria cadastrada com sucesso!"),
            @ApiResponse(responseCode = "400", description = "O nome da categoria está em branco ou excede 100 caracteres.")
    })
    @PostMapping
    public CategoriaDTO create(@RequestBody CategoriaDTO categoria){
        return service.create(categoria);
    }

    @Operation(summary = "Retorna todas as categorias cadastradas", tags = "Categoria")
    @GetMapping("/all")
    public List<CategoriaLowDTO> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Atualiza os dados de uma categoria", tags = "Categoria")
    @PutMapping
    public CategoriaDTO update(@RequestBody CategoriaDTO categoria){
        return service.update(categoria);
    }

    @Operation(summary = "Deleta uma categoria baseado no ID", tags = "Categoria")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteCategoriaById(id);
    }

    @Operation(summary = "Retorna os dados de uma categoria baseado no ID", tags = "Categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados da categoria"),
            @ApiResponse(responseCode = "404",
                    description = "A categoria com o id informado não foi encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/{id}")
    public CategoriaLowDTO findById(@PathVariable(name = "id") Long id){
        return service.findCategoriaById(id);
    }
}