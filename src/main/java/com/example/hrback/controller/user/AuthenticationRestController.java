package com.example.hrback.controller.user;

import com.example.hrback.controller.Url;
import com.example.hrback.model.dto.AuthenticationRequestDto;
import com.example.hrback.model.participant.Participant;
import com.example.hrback.model.user.User;
import com.example.hrback.security.jwt.JwtTokenProvider;
import com.example.hrback.service.participant.impl.ParticipantServiceImpl;
import com.example.hrback.service.user.UserService;
import com.example.hrback.service.user.impl.ResponseServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.USER)
public class AuthenticationRestController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ParticipantServiceImpl participantService;
    private final ResponseServiceImpl responseService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, ParticipantServiceImpl participantService, ResponseServiceImpl responseService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.participantService = participantService;
        this.responseService = responseService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto){
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            Participant participant = participantService.participantByEmail(username);
            if (user == null){
                return new ResponseEntity<>("Пользоветель с логином " + username + " не найден", HttpStatus.NOT_FOUND);
            }
            if (participantService.expiration(participant.getRegistrationDate())){
                return new ResponseEntity<>("Время прохождения онлайн собеседования прошло, просьба начать с начала", HttpStatus.UNAUTHORIZED);
            }
            String token = jwtTokenProvider.createToken(username, user.getRoles());
            return responseService.response(username, token);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Неверный логин или пароль", HttpStatus.UNAUTHORIZED);
        }
    }
}
