package io.github.monthalcantara.advice;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    @Getter
    List<String> errors;

    public ApiError(String e){
        this.errors = Arrays.asList(e);
    }

    public ApiError(List<String> listErrors) {
        this.errors = listErrors;
    }
}
