package br.com.bandtec.ac3isabellacosta.controller;

import br.com.bandtec.ac3isabellacosta.classes.Doce;
import br.com.bandtec.ac3isabellacosta.obj.FilaObj;
import br.com.bandtec.ac3isabellacosta.obj.PilhaObj;
import br.com.bandtec.ac3isabellacosta.repository.DoceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

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
        UUID id = UUID.randomUUID();
        doce.setUu(id);
        f.insert(doce);
        p.push(doce);
        return ResponseEntity.ok().body(id);
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
}
