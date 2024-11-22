package com.ecolab.ecolab.service;

import com.ecolab.ecolab.entity.UserDeviceEntity;
import com.ecolab.ecolab.repository.UserRepository;
import com.ecolab.ecolab.request.UserDeviceRequest;
import com.ecolab.ecolab.repository.DeviceRepository;
import com.ecolab.ecolab.repository.UserDeviceRepository;
import com.ecolab.ecolab.response.UserDeviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UserDeviceService {
    @Autowired
    private UserDeviceRepository userDeviceRepository;

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private UserRepository userRepository;
    public UserDeviceResponse create(UserDeviceRequest userDeviceRequest) {
        var device = deviceRepository
                .findById(userDeviceRequest.deviceId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe Device com o id informado"));
        var user = userRepository
                .findById(userDeviceRequest.userId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe user com o id informado"));

        var userDeviceEntity = UserDeviceEntity.builder()
                .name(userDeviceRequest.name())
                .device(device)
                .user(user)
                .build();
        userDeviceRepository.save(userDeviceEntity);
        return new UserDeviceResponse(userDeviceEntity);
    }

    public void delete(Long id) {
        userDeviceRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe userDevice com o id informado"));

        userDeviceRepository.deleteById(id);
    }

    public Page<UserDeviceEntity> findAll(Pageable pageable) {
        return userDeviceRepository.findAll(pageable);
    }

    public UserDeviceResponse findById(Long id) {
        var userDeviceEntity = userDeviceRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe userDevice com o id informado"));
        return new UserDeviceResponse(userDeviceEntity);
    }
}
