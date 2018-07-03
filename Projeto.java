
package projeto;

import java.util.*;
import java.io.*;



public class Projeto implements Serializable{

    
    public static void main(String[] args) {
        Agencia app=new Agencia();
        Data datatual=dataAtual();
        FicheiroDeObjetos ficheiro = new FicheiroDeObjetos();
        try{
            if (ficheiro.abreLeitura("AppDeTransportes.bin")) {
                app = (Agencia) ficheiro.leObjecto();
                ficheiro.fechaLeitura();
            }
        }
        catch (Exception ex) {
            System.out.printf("Exception found: %s\n",ex);
        }
        init(app,ficheiro,datatual);
    }
    public static Data dataAtual(){
        /*Definir a data  utilizada para os metodod*/
        Scanner sc=new Scanner(System.in);
        int i;
        Data data=new Data();
        int ano,mes,dia,hora,min;
        Calendar cal = Calendar.getInstance();
        System.out.println("Pretende usar a data atual ou definir uma data?(1 -> atual;2 -> definir)");
        do{
            i=sc.nextInt();
            if(i==1){
                data=new Data(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.HOUR),cal.get(Calendar.MINUTE));
            }
            else if(i==2){
                System.out.println("Ano");
                ano=sc.nextInt();
                do{
                    System.out.println("Mes");
                    mes=sc.nextInt();
                }while(mes>12 || mes <=1);
                do{
                    System.out.println("Dia");
                    dia=sc.nextInt();
                }while(dia>31 || dia <=1);
                do{
                    System.out.println("Hora");
                    hora=sc.nextInt();
                }while(hora>24 || hora <=1);
                do{
                    System.out.println("Minuto");
                    min=sc.nextInt();
                }while(mes>60 || mes <=0);
                data=new Data(ano,mes,dia,hora,min);
            }
            else System.out.println("input errado tente outravez");
        }while(i!=1 && i!=2);
        return data;
    }
    public static String login(Agencia app){
        String username;
        String password;
        System.out.printf("Username: ");
        Scanner sc=new Scanner(System.in);
        username=sc.nextLine();
        System.out.printf("Password: ");
        password = sc.nextLine();
        if (app.verificaCredenciais(username,password)==true){
            System.out.println("Username e Password aceite!\nBem-Vindo(a)!");
            return username;
        }
        else{
            System.out.println("PASSWORD E/OU USERNAME ERRADO(S)");
            System.out.println("TENTE OUTRAVEZ");
            login(app);
        }
        return username;
    }
    public static void init(Agencia app,FicheiroDeObjetos ficheiro,Data dataAtual){
        Date date=new Date();
        Admin vazio = new Admin();
        int input;
        System.out.println("--------------- RODOVIARIA ---------------");
        System.out.println(" -------------- Vroomvroom --------------");
        System.out.printf("  ------------- %d/%d/%d %d:%d -------------\n",dataAtual.getDia(),dataAtual.getMes(),dataAtual.getAno(),dataAtual.getHora(),dataAtual.getMinuto());
        if(app.getAdmins()[0]==null){
            app.criaProtoAdmin();
        }
        Scanner sc=new Scanner(System.in);
        String user;
        do{
            System.out.println("1 -> Login.");
            System.out.println("2 -> Registar Conta.");
            System.out.println("0 -> Sair.");
            input=sc.nextInt();
            switch(input){
                case 1:{ user=login(app);
                    if(app.verificaId(user).equals("Admin")){
                        menuAdmin(user,app,ficheiro,dataAtual);
                    }
                    else if(app.verificaId(user).equals("Regular") || app.verificaId(user).equals("Premium")){
                        menuCliente(user,app,ficheiro,dataAtual);
                    }
                    break;}
                    
                case 2:{app.criarConta();init(app,ficheiro,dataAtual);break;}
                
                case 0:{System.out.println("Adeus!");System.exit(0);}
                default:{System.out.println("Input ERRADO! tente outra vez!"); break;}    
            }
        }while(input!=0);
        
    }
    public static void menuCliente(String username,Agencia app,FicheiroDeObjetos ficheiro,Data dataAtual){
        int input;
        Cliente atual=app.devolveCliente(username);
        Scanner sc=new Scanner(System.in);
        /*atual.espera(app); erro*/
        do{
            System.out.println("1 -> Reservar Viagem");
            System.out.println("2 -> Consultar Viagens");
            System.out.println("3 -> Consultar Reservas");
            System.out.println("4 -> Cancelar Reserva");
            System.out.println("5 -> Avaliar");
            System.out.println("6 -> Editar Conta");
            System.out.println("0 -> Sair");
            input=sc.nextInt();
            switch(input){
                case 1:{app.reservar(atual,dataAtual);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                   
                case 2:{app.mostrarViagens();
                    
                    break;}
                   
                case 3:{app.mostrareservas(atual);
                    break;}
                   
                case 4:{app.eliminaReserva(atual); 
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                   
                case 5:{atual.avaliar(app); 
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                   
                case 6:{atual.editar(app); 
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                
                case 0:break;
                
                default:{System.out.println("Input Errado!");
                    break;}
            } 
        }while(input!=0);
    }
    public static void menuAdmin(String username,Agencia app,FicheiroDeObjetos ficheiro,Data dataAtual){
        Admin atual=app.devolveAdmin(username);
        int input;
        Scanner sc=new Scanner(System.in);
        do{
            System.out.println("1 -> Criar Autocarro");
            System.out.println("2 -> Criar Cliente");
            System.out.println("3 -> Criar Viagem");
            System.out.println("4 -> Listar Autocarros");
            System.out.println("5 -> Listar Clientes");
            System.out.println("6 -> Listar Viagens");
            System.out.println("7 -> Listar Reservas");
            System.out.println("8 -> Eliminar Autocarro");
            System.out.println("9 -> Eliminar Cliente");
            System.out.println("10 -> Eliminar Viagem");
            System.out.println("11 -> Editar Autocarro");
            System.out.println("12 -> Editar Cliente");
            System.out.println("13 -> Editar Viagem");
            System.out.println("14 -> Estatisticas");
            System.out.println("0 -> Sair");
            input=sc.nextInt();
            switch(input){
                case 1:{atual.criaAutocarro(app);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 2:{app.criarConta();
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 3:{atual.criaViagem(app,dataAtual);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 4:{app.mostrarAutocarros();break;}
                case 5:{app.mostrarClientes();break;}
                case 6:{app.mostrarViagens();break;}
                case 7:{app.mostraReservas();break;}
                case 8:{atual.eliminaAutocarro(app);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 9:{app.eliminaCliente();
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 10:{app.eliminaViagem();
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 11:{app.editAutocarro();
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 12:{atual.editCliente(app);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 13:{app.editaViagem(app, dataAtual);
                    try{
                        ficheiro.abreEscrita("AppDeTransportes.bin");
                        ficheiro.escreveObjecto(app);
                        ficheiro.fechaEscrita();
                    }
                    catch (Exception ex){
                        System.out.printf("Exception found: %s",ex);
                    }
                    break;}
                case 14:{break;}
            }
        }while(input!=0);
    }
}
