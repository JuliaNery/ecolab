package com.ecolab.ecolab.controller;

import com.ecolab.ecolab.request.UserRequest;
import com.ecolab.ecolab.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/user")
@CacheConfig(cacheNames = "user")
@Tag(name = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Cacheable
    @Operation(
            summary = "Cadastrar um usuario de luz"
    )
    public ResponseEntity create(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriBuilder) {

        var userResponse = userService.create(userRequest);
        var uri = uriBuilder.path("{id}").buildAndExpand(userResponse.id().toString()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @DeleteMapping("/{id}")
    @Cacheable
    @Operation(
            summary = "Deletar um usuario de luz"
    )
    public ResponseEntity delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Lista usuarios de luz"
    )
    public ResponseEntity listAll(@PageableDefault(size = 8) Pageable pageable) {
        try{
            return ResponseEntity.ok().body(userService.findAll(pageable));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Cacheable
    @Operation(
            summary = "Buscar um usuario de luz"
    )
    public ResponseEntity findById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(userService.findById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
