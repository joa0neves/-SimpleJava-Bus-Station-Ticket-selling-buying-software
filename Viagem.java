
package projeto;
import java.io.Serializable;
import java.util.*;

public class Viagem implements Serializable{
    private int codigo;
    private String origem;
    private String destino;
    private Autocarro[] autocarros;
    private double preco;
    private Data duracao;
    private Data partida;
    private Cliente[] espera;
    private Rating[] ratings;
    private Reserva[] reservas;

    public Viagem() {
    }

    public Cliente[] getEspera() {
        return espera;
    }

    public void setEspera(Cliente[] espera) {
        this.espera = espera;
    }

    public Rating[] getRatings() {
        return ratings;
    }

    public void setRatings(Rating[] ratings) {
        this.ratings = ratings;
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public void setReservas(Reserva[] reservas) {
        this.reservas = reservas;
    }

    
    
    @Override
    public String toString() {
        return "Viagem - "+this.codigo+"\nOrigem: "+this.origem+"\nDestino: "+this.destino+"\nPreço: "+this.preco+"€\nDuração: "+this.duracao.getHora()+":"+this.duracao.getMinuto()+"\nPartida: "+this.partida.getDia()+"/"+this.partida.getMes()+"/"+this.partida.getAno()+" "+this.partida.getHora()+":"+this.partida.getMinuto();
    }

    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Autocarro[] getAutocarros() {
        return autocarros;
    }

    public void setAutocarros(Autocarro[] autocarros) {
        this.autocarros = autocarros;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Data getDuracao() {
        return duracao;
    }

    public void setDuracao(Data duracao) {
        this.duracao = duracao;
    }

    public Data getPartida() {
        return partida;
    }

    public void setPartida(Data partida) {
        this.partida = partida;
    }

    public Viagem(int codigo, String origem, String destino, Autocarro[] autocarros, double preco, Data duracao, Data partida,Reserva[] reservas,Rating[] ratings) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.autocarros = autocarros;
        this.preco = preco;
        this.duracao = duracao;
        this.partida = partida;
        this.reservas=reservas;
        this.ratings=ratings;
    }
    
    public void editViagem(Agencia app,Data dataAtual){
        Scanner sc=new Scanner(System.in);
        int i,ano,mes,dia,hora,min;
        System.out.println("1 -> Alterar codigo");
        System.out.println("2 -> Alterar Origem");
        System.out.println("3 -> Alterar Destino");
        System.out.println("4 -> Alterar Preço");
        System.out.println("5 -> Alterar Duração");
        System.out.println("6 -> Alterar data e hora da partida");
        i=sc.nextInt();
        switch(i){
            case 1:{
                do{
                    System.out.println("Novo Codigo: ");
                    codigo=sc.nextInt();
                    }while(app.verificarViagem(codigo)==false);
                    this.codigo=codigo;
                break;}
            case 2:{
                System.out.println("Novo Origem:");
                this.origem=sc.nextLine();
                break;}
            case 3:{
                System.out.println("Novo Destino:");
                this.destino=sc.nextLine();
                break;}
            case 4:{
                System.out.println("Novo Preço;");
                this.preco=sc.nextDouble();
                break;}
            case 5:{
                System.out.println("Nova Duração:");
                do{
                    System.out.printf("Hora: ");
                    hora=sc.nextInt();
                }while(hora<=0 || hora>60);
                do{
                System.out.printf("Minuto: ");
                min=sc.nextInt();
                }while(min<=0 || min<60);
                this.duracao=new Data(hora,min);
                break;}
            case 6:{
                do{
                    System.out.printf("Ano: ");
                    ano=sc.nextInt();
                }while(ano<dataAtual.getAno());
                if(ano==dataAtual.getAno()){
                    do{
                        System.out.printf(" Mês: ");
                        mes=sc.nextInt();
                    }while(mes<dataAtual.getMes() && mes<=1 || mes>=12);
                    if(mes==dataAtual.getMes()){
                        do{
                            System.out.println("Dia: ");
                            dia=sc.nextInt();
                        }while(dia<dataAtual.getDia() && dia<=1 || dia>=31);
                        if(dia==dataAtual.getDia()){
                            do{
                                System.out.printf("Hora: ");
                                hora=sc.nextInt(); 
                            }while(hora<dataAtual.getHora() && hora<=1 || hora>=24);
                            if(hora==dataAtual.getHora()){
                                do{
                                    System.out.printf("Minuto: ");
                                    min=sc.nextInt();
                                } while(min<dataAtual.getMinuto() && min<=1 || min>60);
                            }
                            else{
                                do{
                                    System.out.printf("Minuto: ");
                                    min=sc.nextInt();
                                }while(min>60 || min<=1);
                            }
                        }   
                        else{
                            do{
                                System.out.printf("Hora: ");
                                hora=sc.nextInt();
                            }while(hora>=24 || hora<=1);
                            do{
                                System.out.printf("Minuto: ");
                                min=sc.nextInt();
                            }while(min>60 || min<=1);
                        }
                    }
                    else{
                        do{
                            System.out.printf("Dia: ");
                            dia=sc.nextInt();
                        }while(dia>=31 || dia<=1);
                        do{
                            System.out.printf("Hora: ");
                            hora=sc.nextInt();
                        }while(hora>=24 || hora<=1);
                        do{
                            System.out.printf("Minuto: ");
                            min=sc.nextInt();
                        }while(min>60 || min<=1);
                    }
                }
                else{
                    do{
                        System.out.printf("Mês: ");
                        mes=sc.nextInt();
                    }while(mes>=12 || mes<=1);
                    do{
                        System.out.printf("Dia: ");
                        dia=sc.nextInt();
                    }while(dia>=31 || dia<=1);
                    do{
                        System.out.printf("Hora: ");
                        hora=sc.nextInt();
                    }while(hora>=24 || hora<=1);
                    do{
                        System.out.printf("Minuto: ");
                        min=sc.nextInt();
                    }while(min>60 || min<=0);
                }
        partida= new Data(ano,mes,dia,hora,min);
                break;}
            default:{System.out.println("Input errado!");break;}
        }
    }
}
