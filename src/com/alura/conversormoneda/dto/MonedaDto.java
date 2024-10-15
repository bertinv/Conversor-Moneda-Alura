package com.alura.conversormoneda.dto;

public record MonedaDto(String base_code,
                        String target_code,
                        double conversion_rate,
                        double conversion_result,
                        String time_last_update_utc) {

    public String formato() {
        return String.format("%s equivalen a %.2f %s.",
                base_code, conversion_result, target_code);
    }
}
