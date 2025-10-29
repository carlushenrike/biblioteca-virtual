package br.edu.unichristus.backend.service;

import br.edu.unichristus.backend.domain.dto.UserDTO;
import br.edu.unichristus.backend.domain.dto.UserLowDTO;
import br.edu.unichristus.backend.domain.model.User;
import br.edu.unichristus.backend.exception.ApiException;
import br.edu.unichristus.backend.repository.UserRepository;
import br.edu.unichristus.backend.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO create(UserDTO dto){
        if(dto.getName().isBlank()){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O nome do usuário é obrigatório.");
        }
        if(dto.getName().length() > 100){
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "unichristus.service.user.badrequest",
                    "O limite de caracteres do nome do usuário é 100");
        }
        var user = MapperUtil.parseObject(dto, User.class);
        var userPersist = repository.save(user);

        return MapperUtil.parseObject(userPersist, UserDTO.class);
    }

    public List<UserLowDTO> getAll(){

        return MapperUtil.parseListObjects
                (repository.findAll(), UserLowDTO.class);
    }

    public UserDTO update(UserDTO dto){
        var user = MapperUtil.parseObject(dto, User.class);
        var userPersist = repository.save(user);
        return MapperUtil.parseObject(userPersist, UserDTO.class);
    }

    public void deleteUserById(Long id){
        repository.deleteById(id);
    }

    public UserLowDTO findUserById(Long id){
        var user = repository.findById(id).orElseThrow(
                () -> new ApiException(HttpStatus.NOT_FOUND,
                        "unichristus.service.user.notfound",
                        "O usuário com o id informado não foi encontrado")
        );
        return MapperUtil.parseObject(user, UserLowDTO.class);
    }
}
