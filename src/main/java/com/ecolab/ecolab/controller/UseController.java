package com.ecolab.ecolab.controller;

import com.ecolab.ecolab.request.UseRequest;
import com.ecolab.ecolab.request.UseRequestRelatorio;
import com.ecolab.ecolab.service.UseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/use")
@CacheConfig(cacheNames = "use")
@Tag(name = "use")
public class UseController {
    @Autowired
    private UseService useService;

    @PostMapping("/ligar")
    @Cacheable
    @Operation(
            summary = "Ligar um dispositivo de luz"
    )
    public ResponseEntity on(@RequestBody @Valid UseRequest useRequest, UriComponentsBuilder uriBuilder) {

        var useResponse = useService.ligar(useRequest);
        var uri = uriBuilder.path("{id}").buildAndExpand(useResponse.id().toString()).toUri();
        return ResponseEntity.created(uri).body(useResponse);
    }
    @PutMapping("/desligar")
    @Cacheable
    @Operation(
            summary = "Ligar um dispositivo de luz"
    )
    public ResponseEntity off(@RequestBody @Valid UseRequest useRequest, UriComponentsBuilder uriBuilder) {

        var useResponse = useService.desligar(useRequest);
        var uri = uriBuilder.path("{id}").buildAndExpand(useResponse.id().toString()).toUri();
        return ResponseEntity.created(uri).body(useResponse);
    }

    @GetMapping
    @Cacheable
    @Operation(
            summary = "Gerar relatorio de gasto um dispositivo de luz"
    )
    public ResponseEntity relatorio(@RequestBody @Valid UseRequestRelatorio useRequest) {
        try{
            return ResponseEntity.ok().body(useService.relatorio(useRequest));
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


}
