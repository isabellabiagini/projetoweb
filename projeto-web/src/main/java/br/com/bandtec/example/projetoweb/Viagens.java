package br.com.bandtec.example.projetoweb;

public abstract class Viagens {

    private String partida;
    private String destino;
    private Double taxaviagem;

    public Viagens(String partida, String destino, Double taxaviagem) {
        this.partida = partida;
        this.destino = destino;
        this.taxaviagem = taxaviagem;
    }

    public abstract Double precoTotal();

    public String getPartida() {
        return partida;
    }

    public String getDestino() {
        return destino;
    }

    public Double getTaxaviagem() {
        return taxaviagem;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setTaxaviagem(Double taxaviagem) {
        this.taxaviagem = taxaviagem;
    }
}
