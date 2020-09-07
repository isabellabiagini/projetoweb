package br.com.bandtec.example.projetoweb;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/viagens")
public class ViagensController {

    private List<Viagens> viagens = new ArrayList<>();

    @GetMapping
    public List<Viagens> getViagens(){
        return viagens;
    }

    @PostMapping("/addInternacional")
    public void addViagens(@RequestBody VInternacional v){
        v.precoTotal();
        viagens.add(v);
    }

    @PostMapping("/addNacional")
    public void addViagens(@RequestBody VNacional v){
        v.precoTotal();
        viagens.add(v);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        viagens.remove(id - 1);
    }

}
