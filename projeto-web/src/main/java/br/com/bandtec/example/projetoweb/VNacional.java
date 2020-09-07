package br.com.bandtec.example.projetoweb;

public class VNacional extends Viagens {

    private Double taxaNacional;
    private Double preco;
    private Double precoTotal;

    public VNacional(String partida, String destino, Double taxaviagem, Double taxaNacional, Double preco) {
        super(partida, destino, taxaviagem);
        this.taxaNacional = taxaNacional;
        this.preco = preco;
    }

    @Override
    public Double precoTotal() {
        return precoTotal = preco + (super.getTaxaviagem() + taxaNacional * preco);
    }

    public Double getTaxaNacional() {
        return taxaNacional;
    }

    public void setTaxaNacional(Double taxaNacional) {
        this.taxaNacional = taxaNacional;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
