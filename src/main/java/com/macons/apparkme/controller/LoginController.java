package com.macons.apparkme.controller;

import com.macons.apparkme.dto.UserDTO;
import com.macons.apparkme.entities.User;
import com.macons.apparkme.mapper.UserMapper;
import com.macons.apparkme.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/apparkme/api/v1")
@RestController
public class LoginController {

    @Autowired
    public TokenService tokenService;

    @GetMapping("hola")
    public String getHola(){
        return "HOLA";
    }

    @PostMapping(value = "login")
    public ResponseEntity<UserDTO> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        log.info("\n\n Logueando usuario..... \n\n", username);
        String token = tokenService.getJWTToken(username);
        User user = User.builder().user(username).password(pwd).build();
        UserDTO userDTO = UserMapper.INSTANCE.personaToPersonaDto(user);
        userDTO.setToken(token);
        log.info("\n\n El usuario {} se ha logueado con exito.... \n\n", username);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);

    }

}
