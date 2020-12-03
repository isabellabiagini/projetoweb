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

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/doces")
public class DoceController {
    @Autowired
    private DoceRepository repository;

    FilaObj<Doce> f = new FilaObj<>(30);

    PilhaObj<Doce> p = new PilhaObj<>(30);

    List<Doce> l = new ArrayList<Doce>();

    @GetMapping("/get")
    public ResponseEntity gets() {
        List<Doce> doces = repository.findAll();

        return doces.isEmpty() ? noContent().build() : ok(doces);
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
    public ResponseEntity desfazer(){
        if(!p.isEmpty()) {
            repository.delete(p.pop());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Scheduled(fixedRate = 10000)
    public void conferir() {
        if (!f.isEmpty()) {
            repository.save(f.poll());
            System.out.println("item adicionado");
            l.add(f.poll());
        }
    }

    @GetMapping("/pesquisa/{docinho}")
    public ResponseEntity pesquisa(@PathVariable UUID docinho){
        for (Doce d : l) {
            if (d.getUu().equals(docinho)) {
                l.remove(d);
                return ResponseEntity.ok().body("Docinho adicionado");
            }
        }
        return ResponseEntity.badRequest().body("O docinho não foi adicionado :/, aguarde...");
    }

    @PostMapping("/arquivo")
    public ResponseEntity arquivo(@RequestParam("arquivo") MultipartFile arquivo) throws IOException {

        // importando o arquivo de texto

        if (arquivo.isEmpty()) {
            return ResponseEntity.badRequest().body("Arquivo não enviado!");
        }

        // obtendo o tipo do arquivo
        System.out.println("Recebendo um arquivo do tipo: " + arquivo.getContentType());

        // obtendo o conteúdo do arquivo
        byte[] conteudo = arquivo.getBytes();


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
