package com.ecolab.ecolab.service;

import com.ecolab.ecolab.entity.UseEntity;
import com.ecolab.ecolab.entity.UserDeviceEntity;
import com.ecolab.ecolab.repository.UseRepository;
import com.ecolab.ecolab.repository.UserDeviceRepository;
import com.ecolab.ecolab.request.UseRequest;
import com.ecolab.ecolab.request.UseRequestRelatorio;
import com.ecolab.ecolab.request.UseResponseRelatorio;
import com.ecolab.ecolab.response.UseResponse;
import jakarta.validation.Valid;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class UseService {
    @Autowired
    private UseRepository useRepository;
    @Autowired
    private UserDeviceRepository userDeviceRepository;
    @Autowired
    private OpenAiChatClient gpt;

    public UseResponse ligar(UseRequest useRequest) {

        var userDevice = userDeviceRepository
                .findById(useRequest.userDeviceId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe Device com o id informado"));
        var useEntity = UseEntity.builder()
                .on(LocalDateTime.now())
                .userDevice(userDevice)
                .build();
        useRepository.save(useEntity);
        return new UseResponse(useEntity);
    }

    public UseResponse desligar(UseRequest useRequest) {

            var userDevice = userDeviceRepository
                    .findById(useRequest.userDeviceId())
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe Device com o id informado"));
            var useEntity = useRepository.findByUserDeviceAndOffIsNull(userDevice)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe Device ligado com o id informado"));

            var value = calculeValueSpent(userDevice, LocalDateTime.now(), useEntity.getOn());
            BigDecimal valueSpent = new BigDecimal(value);
            var use = UseEntity.builder()
                    .off(LocalDateTime.now())
                    .userDevice(userDevice)
                    .valueSpent(valueSpent)
                    .build();
            useRepository.save(useEntity);
            return new UseResponse(useEntity);
    }

    private String calculeValueSpent(UserDeviceEntity userDevice, LocalDateTime now, LocalDateTime on) {
        String brand = userDevice.getDevice().getBrand();
        String model = userDevice.getDevice().getModel();
        String potency = userDevice.getDevice().getPotency();

        String call = "Calcule o gasto em dinheiro de energia da luz da marca " + brand
                + " modelo " + model
                + " com potência de " + potency
                + "W ligado por " + on.toString() + " até " + now.toString() + " retorne apenas o valor em reais";
        String valueSpent = gpt.call(call);

        return valueSpent;
    }

    public UseResponseRelatorio relatorio(UseRequestRelatorio useRequest) {
        var valorTotal = useRepository.sumValueSpentByUserDeviceIdAndDateRange(useRequest.userDeviceId(), useRequest.dataInicio(), useRequest.dataFim());
        var deviceName = userDeviceRepository.findById(useRequest.userDeviceId()).get().getDevice().getBrand();
        var response = new UseResponseRelatorio(valorTotal.toString(), deviceName);
        return null;
    }
}
