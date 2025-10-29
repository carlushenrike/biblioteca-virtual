package br.edu.unichristus.backend.controller;

import br.edu.unichristus.backend.domain.dto.ErrorDTO;
import br.edu.unichristus.backend.domain.dto.UserDTO;
import br.edu.unichristus.backend.domain.dto.UserLowDTO;
import br.edu.unichristus.backend.domain.model.User;
import br.edu.unichristus.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Cadastra dados referentes ao usuário",
                   tags = "User")
    @ApiResponse(responseCode = "200", description = "Usuário cadastrado com sucesso!")
    @ApiResponse(responseCode = "400", description = "Possíveis causas:" +
            "\n- O login informado já existe" +
            "\n- O nome do usuário excede o limite de 100 caracteres.")
    @PostMapping
    public UserDTO create(@RequestBody UserDTO user){
        return service.create(user);
    }

    @GetMapping("/all")
    public List<UserLowDTO> getAll(){
        return service.getAll();
    }

    @PutMapping
    public UserDTO update(@RequestBody UserDTO user){
        return service.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") Long id){
        service.deleteUserById(id);
    }

    @Operation(summary = "Retorna os dados de um usuário baseado no ID",
            tags = "User")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Retorno dos dados do usuário"),
            @ApiResponse(responseCode = "404",
            description = "O usuário com o id " +
                    "informado não foi encontrado",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorDTO.class)
            ))
    })
    @GetMapping("/{id}")
    public UserLowDTO findById(@PathVariable(name = "id") Long id){
       return service.findUserById(id);
    }

}
