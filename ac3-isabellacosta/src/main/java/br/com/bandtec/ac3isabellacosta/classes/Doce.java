package br.com.bandtec.ac3isabellacosta.classes;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Doce {

    @Id
    public String id;

    public String nome;

    public String tipo;

    public Double valor;

    public UUID uu;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public UUID getUu() {
        return uu;
    }

    public void setUu(UUID uu) {
        this.uu = uu;
    }


}
