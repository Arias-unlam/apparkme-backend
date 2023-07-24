package com.macons.apparkme.controller;

import com.macons.apparkme.dto.UserDTO;
import com.macons.apparkme.dto.UserRequest;
import com.macons.apparkme.entities.User;
import com.macons.apparkme.mapper.UserMapper;
import com.macons.apparkme.service.TokenService;
import com.macons.apparkme.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/apparkme/api/v1")
@RestController
public class LoginController {

    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    LoginController(TokenService tokenService, UserService userService){
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @GetMapping(value="health")
    public ResponseEntity checkHealth(){
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "login")
    public ResponseEntity<UserDTO> login(@RequestParam("username") String username, @RequestParam("password") String pwd) {
        log.info("\n\n Logueando usuario..... \n\n", username);
        User userFound = userService.findUserByUsernameAndPassword(username,pwd);
        UserDTO userDTO = UserMapper.INSTANCE.personaToPersonaDto(userFound);
        String token = tokenService.getJWTToken(username);
        userDTO.setToken(token);
        log.info("\n\n El usuario {} se ha logueado con exito.... \n\n", username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping(value = "signup")
    public ResponseEntity create(@RequestBody UserRequest userRequest){

        log.info("\n\n Creando usuario..... \n\n", userRequest.getUsername());

        userService.createUser(userRequest);
        log.info("\n\n Se ha creado el usuario {} con exito...", userRequest.getUsername());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



}
