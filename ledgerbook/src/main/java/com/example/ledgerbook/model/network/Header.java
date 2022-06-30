package com.example.ledgerbook.model.network;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Header<T> {
    // response time
    private LocalDateTime responseTime;

    private String resultCode;

    private String description;

    private T data;

    //OK
    public static <T> Header<T> OK(){
        return (Header<T>) Header.builder()
                .responseTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .build();
    }
    //DATA OK
    public static <T> Header<T> OK(T data){
        return (Header<T>) Header.builder()
                .responseTime(LocalDateTime.now())
                .resultCode("OK")
                .description("OK")
                .data(data)
                .build();
    }
    //ERROR
    public static <T> Header<T> ERROR(String description){
        return (Header<T>) Header.builder()
                .responseTime(LocalDateTime.now())
                .resultCode("ERROR")
                .description(description)
                .build();
    }
}
