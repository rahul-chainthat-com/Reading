package com.getir.reading.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequest {

    @NonNull
    private String name;
}
