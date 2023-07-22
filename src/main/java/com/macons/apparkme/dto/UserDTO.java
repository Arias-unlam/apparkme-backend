package com.macons.apparkme.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserDTO {
    private String username;
    private String token;
}

