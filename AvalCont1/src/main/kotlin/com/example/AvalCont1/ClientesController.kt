package com.example.AvalCont1

import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cliente")
class ClientesController {

    val listinha = mutableListOf<Clientes>()

    @GetMapping()
    fun buscar(): ResponseEntity<MutableListIterator<Clientes>> {
        return ResponseEntity.ok().body(listinha.listIterator())
    }


    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Clientes> {
       return ResponseEntity.ok().body(listinha.elementAt(id-1))
    }

    @DeleteMapping("/delete/{id}")
    fun deleteByID(@PathVariable id : Int){
        listinha.removeAt(id-1)
     }


    @PostMapping("/post")
    fun add(@RequestBody clientes: Clientes) {
        clientes.classe()
        listinha.add(clientes)
    }

    @GetMapping("/maisricos")
    fun riquinhos(): ResponseEntity<List<Clientes>> {
        return ResponseEntity.ok().body(listinha.filter {it.renda > 15000 })
    }
}