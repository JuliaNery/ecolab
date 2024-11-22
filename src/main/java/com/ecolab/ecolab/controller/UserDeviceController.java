package com.ecolab.ecolab.controller;

import com.ecolab.ecolab.request.UserDeviceRequest;
import com.ecolab.ecolab.service.UserDeviceService;
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
@RequestMapping("/useruserDevice")
@CacheConfig(cacheNames = "useruserDevice")
@Tag(name = "useruserDevice")
public class UserDeviceController {
    @Autowired
    private UserDeviceService userDeviceService;

    @PostMapping
    @Cacheable
    @Operation(
            summary = "Cadastrar um dispositivo de luz"
    )
    public ResponseEntity create(@RequestBody @Valid UserDeviceRequest userDeviceRequest, UriComponentsBuilder uriBuilder) {

        var userDeviceResponse = userDeviceService.create(userDeviceRequest);
        var uri = uriBuilder.path("{id}").buildAndExpand(userDeviceResponse.id().toString()).toUri();
        return ResponseEntity.created(uri).body(userDeviceResponse);
    }

    @DeleteMapping("/{id}")
    @Cacheable
    @Operation(
            summary = "Deletar um dispositivo de luz"
    )
    public ResponseEntity delete(@PathVariable Long id) {
        userDeviceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Lista dispositivos de luz"
    )
    public ResponseEntity listAll(@PageableDefault(size = 8) Pageable pageable) {
        try{
            return ResponseEntity.ok().body(userDeviceService.findAll(pageable));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Cacheable
    @Operation(
            summary = "Buscar um dispositivo de luz"
    )
    public ResponseEntity findById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok().body(userDeviceService.findById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
