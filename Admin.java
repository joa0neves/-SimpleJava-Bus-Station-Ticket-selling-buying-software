
package projeto;

import java.io.Serializable;
import java.util.*;

public class Admin extends Utilizador implements Serializable {
    private String id="Admin";

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

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getNif() {
        return nif;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String nome,String morada,String email,long nif,int telefone,String username, String password) {
        this.nome=nome;
        this.morada=morada;
        this.email=email;
        this.nif=nif;
        this.telefone=telefone;
        this.username=username;
        this.password=password;
    }
    public void ListarClientes(Regular[] regulares,Premium[] premiums){
        for(int i =0;i<regulares.length;i++){
            System.out.println(regulares[i].getNome()+" - Cliente Regular");
        }
        for(int i=0;i<premiums.length;i++){
            System.out.println(premiums[i].getNome()+" - Cliente Premium");
        }
    }

    public Admin() {
    }
    
    public void mostrarReservas(Agencia app){
        for(int i=0;i<app.getViagens().length;i++){
            for(int k=0;k<app.getViagens()[i].getReservas().length;k++){
                if(app.getViagens()[i].getReservas()[k]!=null){
                    System.out.println(app.getViagens()[i].getReservas()[k].toString());
                }
            }
        }
    }
    
    public void eliminaAutocarro(Agencia app){
        String matricula;
        Boolean a=false;
        Scanner sc=new Scanner(System.in);
        do{
            matricula=sc.nextLine();
            if(matricula.equals("0")) break;
            for(int i=0;i<app.getAutocarros().length;i++){
                if(app.getAutocarros()[i]!=null){
                    if(app.getAutocarros()[i].getMatricula().equals(matricula)){ app.getAutocarros()[i]=null; 
                        a=true; 
                        break;}
                }
            }
        }while(a==false);
    }
    
    public void criaAutocarro(Agencia app){
        Autocarro autocarro=new Autocarro();
        String m;
        Cliente[] lugares;
        int l;
        System.out.println("Matricula do Autocarro(##-**-##):");
        Scanner sc=new Scanner(System.in);
        m=sc.nextLine();
        System.out.println("Lotação do Autocarro "+m);
        Scanner scanner= new Scanner(System.in);
        l=scanner.nextInt();
        lugares=new Cliente[l];
        for(int i=0;i<app.getAutocarros().length;i++){
            if(app.getAutocarros()[i]==null){ app.getAutocarros()[i]=new Autocarro(m,l,lugares);break;}
        }
    }
    
    public void editCliente(Agencia app){
        String user;
        Cliente cliente;
        Scanner sc=new Scanner(System.in);
        System.out.println("Escreva o username do Cliente que pretende editar:");
        user=sc.nextLine();
        if(app.devolveCliente(user)!=app.getCvazio()){
            cliente=app.devolveCliente(user);
            cliente.editar(app);
        }
    }
    
    public void criaViagem(Agencia app,Data dataAtual){
        int codigo,n,lugares=0;
        int hora=0,dia=0,minuto=0,mes=0,ano=0;
        double preco;
        String ori;
        String destino;
        Data duracao;
        Data partida;
        String matricula;
        Autocarro[] frota;
        Reserva[] reservas;
        Cliente[] espera;
        Rating[] avaliacao;
        Scanner sc=new Scanner(System.in);
        do{
        System.out.println("Codigo: ");
        codigo=sc.nextInt();
        }while(app.verificarViagem(codigo)==false);
        sc.nextLine();
        System.out.println("Origem:");
        ori=sc.nextLine();
        System.out.println("Destino: ");
        destino=sc.nextLine();
        do{
        System.out.println("Preço: ");
        preco=sc.nextDouble();
        }while(preco<0);
        System.out.println("Dia de partida");
        do{
        System.out.printf("Ano: ");
        ano=sc.nextInt();
        }while(ano<dataAtual.getAno());
        if(ano==dataAtual.getAno()){
            do{
                System.out.printf(" Mês: ");
                mes=sc.nextInt();
            }while(mes<dataAtual.getMes() && mes<=0 || mes>=12);
            if(mes==dataAtual.getMes()){
                do{
                    System.out.println("Dia: ");
                    dia=sc.nextInt();
                }while(dia<dataAtual.getDia() && dia<=0 || dia>=31);
                if(dia==dataAtual.getDia()){
                    do{
                        System.out.printf("Hora: ");
                        hora=sc.nextInt(); 
                    }while(hora<dataAtual.getHora() && hora<0 || hora>=24);
                    if(hora==dataAtual.getHora()){
                       do{
                            System.out.printf("Minuto: ");
                            minuto=sc.nextInt();
                       } while(minuto<dataAtual.getMinuto() && minuto<0 || minuto>60);
                    }
                    else{
                        do{
                            System.out.printf("Minuto: ");
                            minuto=sc.nextInt();
                        }while(minuto>60 || minuto<0);
                    }
                }
                else{
                    do{
                        System.out.printf("Hora: ");
                        hora=sc.nextInt();
                    }while(hora>=24 || hora<=1);
                    do{
                        System.out.printf("Minuto: ");
                        minuto=sc.nextInt();
                    }while(minuto>60 || minuto<=1);
                }
            }
            else{
                do{
                    System.out.printf("Dia: ");
                    dia=sc.nextInt();
                }while(dia>=31 || dia<0);
                do{
                    System.out.printf("Hora: ");
                    hora=sc.nextInt();
                }while(hora>=24 || hora<0);
                do{
                    System.out.printf("Minuto: ");
                    minuto=sc.nextInt();
                }while(minuto>60 || minuto<0);
            }
        }
        else{
            do{
                System.out.printf("Mês: ");
                mes=sc.nextInt();
            }while(mes>12 || mes<0);
            do{
                System.out.printf("Dia: ");
                dia=sc.nextInt();
            }while(dia>31 || dia<0);
            do{
                System.out.printf("Hora: ");
                hora=sc.nextInt();
            }while(hora>24 || hora<0);
            do{
                System.out.printf("Minuto: ");
                minuto=sc.nextInt();
            }while(minuto>60 || minuto<0);
        }
        
        partida= new Data(ano,mes,dia,hora,minuto);
        System.out.println("Duração:");
        do{
            System.out.printf("Hora: ");
            hora=sc.nextInt();
        }while(hora<0 || hora>60);
        do{
        System.out.printf("Minuto: ");
        minuto=sc.nextInt();
        }while(minuto<0 || minuto>60);
        duracao=new Data(hora,minuto);
        System.out.println("Quantos autocarros serão utilizados nesta Viagem:");
        n=sc.nextInt();
        sc.nextLine();
        frota=new Autocarro[n];
        System.out.println("Estes são os autocarros disponiveis:");
        app.mostrarAutocarros();
        System.out.println("Introduza a(s) matricula(s) do(s) autocarro(s):");
        for(int i=0;i<frota.length;i++){
            matricula=sc.nextLine();
            for(int k=0;i<app.getAutocarros().length;i++){
                if(app.getAutocarros()[i]!=null){
                if(matricula.equals(app.getAutocarros()[k].getMatricula())){ frota[i]=new Autocarro(app.getAutocarros()[k].getMatricula(),app.getAutocarros()[k].getLotacao(),app.getAutocarros()[k].getLugares());break;}  
            }}
        }
        for(int i=0;i<frota.length;i++){
            if(frota[i]!=null){
                lugares=lugares+frota[i].getLotacao();
            }
        }
        avaliacao=new Rating[lugares];
        reservas=new Reserva[lugares];
        espera=new Cliente[lugares];
        
        for(int i=0;i<app.getViagens().length;i++){
            if(app.getViagens()[i]==null){ app.getViagens()[i]=new Viagem(codigo,ori,destino,frota,preco,duracao,partida,reservas,avaliacao);break;}
        }
        
    }
    
    public void estatistica(Agencia app){
        int input;
        int vendas=0,pontos=0;
        Viagem viagem=new Viagem();
        Scanner sc=new Scanner(System.in);;
        System.out.println("1 -> Viagem mais vendida num determinado mes.");
        System.out.println("2 -> Viagem com mais pontos");
        input=sc.nextInt();
        switch(input){
            case 1:{
                for(int i=0;i<app.getViagens().length;i++){
                    if(app.contaPontos(app.getViagens()[i])>pontos) viagem=app.getViagens()[i];
                }
                System.out.println(viagem.toString());
                break;}
            case 2:{
                for(int i=0;i<app.getViagens().length;i++){
                    if(app.contador(app.getViagens()[i])>vendas) viagem=app.getViagens()[i];
                }
                System.out.println(viagem.toString());
                break;}

        }
    }
}
