
package projeto;

import java.io.Serializable;


public class Data implements Serializable {
    private int ano;
    private int mes;
    private int dia;
    private int hora;
    private int minuto;


    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public Data(int ano,int mes, int dia, int hora, int minuto) {
        this.mes = mes;
        this.dia = dia;
        this.hora = hora;
        this.minuto = minuto;
        this.ano=ano;
    }
    
    public Data(){
        
    }
    
    public Data(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    
}
