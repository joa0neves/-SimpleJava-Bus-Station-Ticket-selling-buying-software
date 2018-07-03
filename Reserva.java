
package projeto;

import java.io.Serializable;


public class Reserva implements Serializable {
    private Cliente cliente;
    private Autocarro autocarro;
    private int lugar;
    private Double preco;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Autocarro getAutocarro() {
        return autocarro;
    }

    public void setAutocarro(Autocarro autocarro) {
        this.autocarro = autocarro;
    }

    

    public int getLugar() {
        return lugar;
    }

    public void setLugar(int lugar) {
        this.lugar = lugar;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Reserva(Cliente cliente, Autocarro autocarro, int lugar, Double preco) {
        this.cliente = cliente;
        this.autocarro = autocarro;
        this.lugar = lugar;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Reserva{" + "cliente=" + cliente.nome + ", autocarro=" + autocarro + ", lugar=" + lugar + ", preco=" + preco + '}';
    }
    
    
}
