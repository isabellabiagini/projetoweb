package br.com.bandtec.example.projetoweb;

public class VInternacional extends Viagens {

    private Double taxaInternacional;
    private Double preco;
    private Double precoTotal;


    public VInternacional(String partida, String destino, Double taxaviagem, Double taxaInternacional, Double preco) {
        super(partida, destino, taxaviagem);
        this.taxaInternacional = taxaInternacional;
        this.preco = preco;
    }

    @Override
    public Double precoTotal() {
        return precoTotal = preco + (super.getTaxaviagem() + taxaInternacional * preco);
    }

    public Double getTaxaInternacional() {
        return taxaInternacional;
    }

    public void setTaxaInternacional(Double taxaInternacional) {
        this.taxaInternacional = taxaInternacional;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
