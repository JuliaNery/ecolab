package com.ecolab.ecolab.service;

import com.ecolab.ecolab.entity.DeviceEntity;
import com.ecolab.ecolab.repository.DeviceRepository;
import com.ecolab.ecolab.request.DeviceRequest;
import com.ecolab.ecolab.response.DeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    public DeviceResponse create(DeviceRequest deviceRequest) {
        var deviceEntity = DeviceEntity.builder()
                .brand(deviceRequest.brand())
                .model(deviceRequest.model())
                .potency(deviceRequest.potency())
                .type(deviceRequest.type())
                .build();
        deviceRepository.save(deviceEntity);
        return new DeviceResponse(deviceEntity);
    }

    public void delete(Long id) {
            deviceRepository
                    .findById(id)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe device com o id informado"));

        deviceRepository.deleteById(id);
    }

    public Page<DeviceEntity> findAll(Pageable pageable) {
        return deviceRepository.findAll(pageable);
    }

    public DeviceResponse findById(Long id) {
        var deviceEntity = deviceRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe device com o id informado"));
        return new DeviceResponse(deviceEntity);
    }
}
