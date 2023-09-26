package com.abdel.gestiondestock.handlers;

import com.abdel.gestiondestock.Exception.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes code;

    private String message;

    private List<String> errors =new ArrayList<>();

}
