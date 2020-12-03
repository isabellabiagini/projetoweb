package br.com.bandtec.ac3isabellacosta.controller;

import br.com.bandtec.ac3isabellacosta.classes.Doce;
import br.com.bandtec.ac3isabellacosta.repository.DoceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = DoceController.class)
class DoceControllerTest {

    @Autowired
    DoceController controller;

    @MockBean
    DoceRepository repository;

    @Test
    @DisplayName("Lista com todos os doces, com  status 200")
    void gets() {

        List<Doce> doces = Arrays.asList(Mockito.mock(Doce.class));

        Mockito.when(repository.findAll()).thenReturn(doces);

        ResponseEntity resposta = controller.gets();

        assertEquals(200, resposta.getStatusCodeValue());
        assertEquals(doces,resposta.getBody());
    }

    @Test
    void gets2(){

        List<Doce> doces = new ArrayList<>();

        Mockito.when(repository.findAll()).thenReturn(doces);

        ResponseEntity resposta = controller.gets();

        assertEquals(204,resposta.getStatusCodeValue());
        assertEquals(null,resposta.getBody());
    }

    @Test
    void desfazer() {
        Doce d1= Mockito.mock(Doce.class);

        ResponseEntity resposta1 = controller.add(d1);
        ResponseEntity resposta2 = controller.desfazer();
        assertEquals(200,resposta2.getStatusCodeValue());

    }

    @Test
    void desfazer2() {

        ResponseEntity resposta = controller.desfazer();
        assertEquals(404,resposta.getStatusCodeValue());

    }

    @Test
    void add() {
        ResponseEntity resposta = controller.add(Mockito.mock(Doce.class));

        controller.desfazer();
        controller.conferir();

        assertEquals(200,resposta.getStatusCodeValue());

    }

    @Test
    void pesquisa1() {

        UUID id = UUID.randomUUID();

        Doce doce = new Doce();
        doce.setUu(id);

        List<Doce> list = new ArrayList<>();

        controller.l.clear();
        controller.l.add(doce);

        ResponseEntity resposta = controller.pesquisa(id);

        assertEquals(200,resposta.getStatusCodeValue());

    }

    @Test
    void pesquisa2() {

        UUID id = UUID.randomUUID();

        Doce doce = new Doce();
        doce.setUu(id);

        List<Doce> list = new ArrayList<>();
        list.add(doce);

        ResponseEntity resposta = controller.pesquisa(id);

        assertEquals(400,resposta.getStatusCodeValue());

    }


    @Test
    void arquivo() throws IOException, URISyntaxException {
        MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

        Mockito.when(multipartFile.isEmpty()).thenReturn(true);

        ResponseEntity resposta = controller.arquivo(multipartFile);

        assertEquals(400,resposta.getStatusCodeValue());

    }
}