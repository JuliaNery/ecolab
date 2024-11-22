package com.ecolab.ecolab.request;

import java.time.LocalDate;

public record UseRequestRelatorio(
        LocalDate dataInicio,
        LocalDate dataFim,
        Long userDeviceId

) {
}
