package com.hotel.hotelManagement.response;

import lombok.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class APIResponse {
    @Setter
    private String status;

    private Integer httpStatus;

    @Setter
    private String message;

    @Setter
    private Object data;

   // public void setHttpStatus(int httpStatus) { this.httpStatus = httpStatus; }
}
