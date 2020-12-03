package br.com.bandtec.ac3isabellacosta.controller;

import br.com.bandtec.ac3isabellacosta.arquivo.LeArquivo;
import br.com.bandtec.ac3isabellacosta.classes.Doce;
import br.com.bandtec.ac3isabellacosta.obj.FilaObj;
import br.com.bandtec.ac3isabellacosta.obj.PilhaObj;
import br.com.bandtec.ac3isabellacosta.repository.DoceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doces")
public class DoceController {
    @Autowired
    private DoceRepository repository;

    FilaObj f = new FilaObj(30);

    PilhaObj p = new PilhaObj(30);

    List<Doce> l = new ArrayList<Doce>();

    @GetMapping("/get")
    public ResponseEntity gets(){
        return ResponseEntity.ok(repository.findAll());
    }



    @PostMapping("/post")
    public ResponseEntity add(@RequestBody Doce doce){
        UUID uid = UUID.randomUUID();
        doce.setUu(uid);
        f.insert(doce);
        p.push(doce);
        return ResponseEntity.ok().body(uid);
    }

    @DeleteMapping("/desfazer")
    public void desfazer(){
        repository.delete((Doce) p.pop());
    }

    @Scheduled(fixedRate = 10000)
    public void verificar() {
        Object a = f.poll();
        repository.save((Doce) a );
        System.out.println("item adicionado");
        l.add((Doce) a);
    }

    @GetMapping("/pesquisa/{docinho}")
    public ResponseEntity pesquisa(@PathVariable UUID docinho){
        for (Doce d : l) {
            if (d.getUu().equals(docinho)) {
                l.remove(0);
                return ResponseEntity.ok().body("Docinho adicionado");
            }
        }
        return ResponseEntity.badRequest().body("O docinho não foi adicionado :/, aguarde...");
    }

    @PostMapping("/arquivo")
    public ResponseEntity enviar(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {

        // verificando se o arquivo foi enviado mesmo
        if (arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo não enviado!");
        }

        // obtendo o tipo do arquivo
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        // obtendo o conteúdo do arquivo
        byte[] conteudo = arquivo.getBytes();

        // obtendo o nome original do arquivo para criar uma cópia dele
        Path path = Paths.get(arquivo.getOriginalFilename());
        Files.write(path, conteudo);

        String nomeArq = "doce.txt";

        List<Doce>doces = LeArquivo.leArquivo(nomeArq);

        for (Doce d : doces){

            repository.save(d);

        }

        return ResponseEntity.created(null).build();
    }
}
