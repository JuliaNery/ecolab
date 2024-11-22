package com.ecolab.ecolab.service;

import com.ecolab.ecolab.entity.UserEntity;
import com.ecolab.ecolab.repository.UserRepository;
import com.ecolab.ecolab.request.UserRequest;
import com.ecolab.ecolab.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public UserResponse create(UserRequest userRequest) {
        var userEntity = UserEntity.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .password(userRequest.password())
                .cep(userRequest.cep())
                .build();
        userRepository.save(userEntity);
        return new UserResponse(userEntity);
    }

    public void delete(Long id) {
        userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe user com o id informado"));

        userRepository.deleteById(id);
    }

    public Page<UserEntity> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public UserResponse findById(Long id) {
        var userEntity = userRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe user com o id informado"));
        return new UserResponse(userEntity);
    }
}
