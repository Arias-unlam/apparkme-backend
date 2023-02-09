package com.macons.apparkme.entities;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {

    private String user;
    private String password;
}
