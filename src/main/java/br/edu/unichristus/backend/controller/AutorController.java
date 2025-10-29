package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.AutorDTO;
import br.edu.unichristus.backend.domain.dto.AutorLowDTO;
import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/autor")
public class AutorController {

    @Autowired
    private AutorService service;

    @Operation(summary = "Cadastra dados referentes ao autor",
            tags = "Autor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Autor cadastrado com sucesso!"),
            @ApiResponse(responseCode = "400", description = "Possíveis causas:" +
                    "\n- O nome do autor está em branco." +
                    "\n- O nome do autor excede o limite de 100 caracteres.")
    })
    @PostMapping
    public AutorDTO create(@RequestBody AutorDTO autor){
        return service.create(autor);
    }

    @Operation(summary = "Retorna todos os autores cadastrados",
            tags = "Autor")
    @GetMapping("/all")
    public List<AutorLowDTO> getAll(){
        return service.getAll();
    }

    @Operation(summary = "Atualiza os dados de um autor",
            tags = "Autor")
    @PutMapping
    public AutorDTO update(@RequestBody AutorDTO autor){
        return service.update(autor);
    }

    @Operation(summary = "Deleta um autor baseado no ID",
            tags = "Autor")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteAutorById(id);
    }

    @Operation(summary = "Retorna os dados de um autor baseado no ID",
            tags = "Autor")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados do autor"),
            @ApiResponse(responseCode = "404",
                    description = "O autor com o id informado não foi encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDTO.class)
                    ))
    })
    @GetMapping("/{id}")
    public AutorLowDTO findById(@PathVariable(name = "id") Long id){
        return service.findAutorById(id);
    }
}