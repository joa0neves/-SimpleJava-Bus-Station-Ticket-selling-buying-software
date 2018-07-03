
package projeto;

import java.io.Serializable;

public class Autocarro implements Serializable {
    private String matricula;
    private int lotacao;
    private Cliente[] lugares;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Cliente[] getLugares() {
        return lugares;
    }

    public void setLugares(Cliente[] lugares) {
        this.lugares = lugares;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public Autocarro(String matricula, int lotacao,Cliente[] lugares) {
        this.matricula = matricula;
        this.lotacao = lotacao;
        this.lugares=lugares;
    }

    public Autocarro() {
    }

    @Override
    public String toString() {
        return "Autocarro{" + "matricula=" + matricula + " "+"lotacao=" + lotacao + '}';
    }

    
    
    
}
