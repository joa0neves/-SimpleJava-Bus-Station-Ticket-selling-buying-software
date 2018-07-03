
package projeto;

import java.io.Serializable;
import java.util.*;

public class Utilizador implements Serializable {
   protected String nome;
   protected String morada;
   protected String email;
   protected long nif;
   protected int telefone;
   protected String username;
   protected String password;
   protected String id;

    @Override
    public String toString() {
        return "Utilizador{\n" + "\t nome=" + this.nome + "\n\t morada=" + this.morada + "\n\t email=" + this.email + "\n\t nif=" + this.nif + "\n\t telefone=" + this.telefone + "\n\t username=" + this.username + "\n\t password=" + this.password + "}";
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
   
   public void avaliar (Agencia app){
        int codigo=0;
        boolean existe=false;
        Viagem viagem;
        int r;
        String comentario;
        Rating avaliacao;
        Scanner s=new Scanner(System.in);
        do{
            System.out.println("Insira o codigo da viagem: ");
            codigo=s.nextInt();
            if (codigo==-1){ 
                break;}
            existe=app.verificarViagem(codigo);
        }while(existe==true);
        viagem=app.devolveViagem(codigo);
        if(codigo!=-1){
            for(int n=0;n<viagem.getReservas().length;n++){
                if(viagem.getReservas()[n]!=null){
                    if(viagem.getReservas()[n].getCliente().getNome().equals(this.nome)){
                        existe=true;
                        do{
                            System.out.println("Avalie a sua viagem numa escala de 1 a 5:");
                            r=s.nextInt();
                        }while(r>6 &&r<0);
                        System.out.println("Escreva um pequeno comentario sobre a sua viagem:");
                        comentario=s.nextLine();
                        avaliacao=new Rating(this.nome,comentario,r);
                        for(int i=0;i<viagem.getRatings().length;i++){
                            if(viagem.getRatings()[i]==null) viagem.getRatings()[i]=avaliacao;
                        }
                    }
                }
            }
            if(existe==false){
                System.out.println("Não faz parte desta Viagem.");
            }
        }
      /*verificar se a viagem existe e verificar se o cliente faz parte da viagem*/  
   }
   
    public void espera(Agencia app){
        for(int i=0;i<app.getViagens().length;i++){
            if(app.getViagens()[i]!=null){
                if(app.lugaresVazios(app.getViagens()[i])>0){
                    for(int k=0;k<app.getViagens()[i].getEspera().length;k++){
                        if(app.getViagens()[i].getEspera()[k]!=null){
                            if(app.getViagens()[i].getEspera()[k].getUsername().equals(this.username)){
                                System.out.println("Existe um lugar disponivel na Viagem: "+app.getViagens()[i].getCodigo());
                                
                            }
                        }
                    }
                }
            }
        }
    }
   
   public void editar(Agencia app){
       int input;
       Scanner sc=new Scanner(System.in);
       System.out.println("Estes São Os Dados Atuais:");
       System.out.println(toString());
       do{
          System.out.printf("1 -> Mudar Nome\n2 -> Mudar Morada\n3 -> Mudar E-mail\n4 -> Mudar NIF\n5 -> Mudar Telefone\n6 -> Mudar Username\n7 -> Mudar Password\n0 -> Sair");
          input=sc.nextInt();
          sc.nextLine();
          switch(input){
              case 1:{
                  System.out.println("Novo Nome: ");
                  this.nome=sc.nextLine();
                  break;
              }   
              case 2:{
                  System.out.println("Nova Morada: ");
                  this.morada=sc.nextLine();
                  break;
              }
              case 3:{
                  System.out.println("Novo E-mail: ");
                  this.email=sc.nextLine();
                  break;
              }
                  
              case 4:{
                do{
                    System.out.println("Novo Número de Telefone:");
                    this.telefone=sc.nextInt();
                    if(this.telefone<99999999 || this.telefone>1000000000) System.out.println("O NUMERO DE TELEFONE TEM DE TER 9 ALGARISMOS!\nTENTE OUTRAVEZ!");
                }while(this.telefone<99999999 || this.telefone>1000000000);
                break;
              }
                  
              case 5:{
                do{
                    System.out.println("Novo Número de Identificação Fiscal: ");
                    this.nif=sc.nextLong();
                    if(this.nif<99999999 || this.nif>1000000000) System.out.println("O NIF TEM DE TER 9 ALGARISMOS!\nTENTE OUTRAVEZ!");
                }while(this.nif<99999999 || this.nif>1000000000);
                break;
              }
                  
              case 6:{
                  do{
                      System.out.println("Novo Username: ");
                      this.username=sc.nextLine();
                  }while(app.verificaUsername(this.username)==true);
                  break;
              }
                  
              case 7:{
                System.out.println("Nova Password: ");
                this.password=sc.nextLine();
                break;
              }
              
              case 0:break;
                  
              default:{
                  System.out.println("Input errado!");
                  break;
              }
          }
       }while(input!=0);
   }
   
}
