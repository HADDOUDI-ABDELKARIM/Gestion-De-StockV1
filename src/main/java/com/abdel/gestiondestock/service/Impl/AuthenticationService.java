package com.abdel.gestiondestock.service.Impl;

import com.abdel.gestiondestock.Exception.EntityNotFoundException;
import com.abdel.gestiondestock.Exception.ErrorCodes;
import com.abdel.gestiondestock.controller.imp.AuthenticationRequest;
import com.abdel.gestiondestock.controller.imp.AuthenticationResponse;
import com.abdel.gestiondestock.controller.imp.RegisterRequest;
import com.abdel.gestiondestock.model.Utilisateur;
import com.abdel.gestiondestock.repository.UtilisateurRepository;
import com.abdel.gestiondestock.service.jwt.JwtService;
import com.abdel.gestiondestock.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UtilisateurService service;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

     @Autowired
    private UtilisateurRepository utilisateurRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new Utilisateur();
        user.setPrenom(request.getFirstname());
        user.setNom(request.getLastname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        utilisateurRepository.save(user);

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
        
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail()
                        ,request.getPassword()
                )
        );
        var user=utilisateurRepository.findUtilisateurByEmail(request.getEmail())
                .orElseThrow(()->new EntityNotFoundException("user not fonund "
                        , ErrorCodes.UTILISATEUR_FOUND));

        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
}
