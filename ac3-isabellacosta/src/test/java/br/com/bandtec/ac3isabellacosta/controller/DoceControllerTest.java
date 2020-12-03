package br.com.bandtec.ac3isabellacosta.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class DoceControllerTest {

    @Test
    void gets() {
        DoceController doceController = new DoceController();
        ResponseEntity resposta = doceController.gets();

        assertEquals(200, resposta.getStatusCodeValue());
    }

    @Test
    void add() {
    }

    @Test
    void desfazer() {
    }

    @Test
    void verificar() {
    }

    @Test
    void pesquisa() {
    }

    @Test
    void enviar() {
    }
}