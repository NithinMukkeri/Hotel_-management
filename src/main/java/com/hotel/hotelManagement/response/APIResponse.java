package com.hotel.hotelManagement.response;


import lombok.*;

@Data
@NoArgsConstructor

@AllArgsConstructor
@Setter
@Getter
public class APIResponse {
    private String status;

    private Integer httpStatus;


    private String message;

    private Object data;

   // public void setHttpStatus(int httpStatus) { this.httpStatus = httpStatus; }
}
