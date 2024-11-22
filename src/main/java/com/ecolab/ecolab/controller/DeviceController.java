package com.ecolab.ecolab.controller;

import com.ecolab.ecolab.request.DeviceRequest;
import com.ecolab.ecolab.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/device")
@CacheConfig(cacheNames = "device")
@Tag(name = "device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    @Cacheable
    @Operation(
            summary = "Cadastrar um dispositivo de luz"
    )
    public ResponseEntity create(@RequestBody @Valid DeviceRequest deviceRequest, UriComponentsBuilder uriBuilder) {

        var deviceResponse = deviceService.create(deviceRequest);
        var uri = uriBuilder.path("{id}").buildAndExpand(deviceResponse.id().toString()).toUri();
        return ResponseEntity.created(uri).body(deviceResponse);
    }

    @DeleteMapping("/{id}")
    @Cacheable
    @Operation(
            summary = "Deletar um dispositivo de luz"
    )
    public ResponseEntity delete(@PathVariable Long id) {
        deviceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Lista dispositivos de luz"
    )
    public ResponseEntity listAll(@PageableDefault(size = 8) Pageable pageable) {
        try{
            return ResponseEntity.ok().body(deviceService.findAll(pageable));
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
            return ResponseEntity.ok().body(deviceService.findById(id));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
