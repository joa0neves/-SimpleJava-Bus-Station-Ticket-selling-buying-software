
package projeto;
import java.util.*;
import java.io.*;

public class Agencia implements Serializable {
    private Cliente[] clientes=new Cliente[200];
    private Admin[] admins=new Admin[100];
    private Autocarro[] autocarros=new Autocarro[100];
    private Viagem[] viagens= new Viagem[100];
    private Admin avazio=new Admin();
    private Cliente cvazio;
    
    public void eliminaReserva (Cliente cliente){
        Scanner sc=new Scanner(System.in);
        int codigo;
        mostrareservas(cliente);
        System.out.println("Escreva o codigo da viagem que pretende cancelar:");
        codigo=sc.nextInt();
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]!=null){
                if(viagens[i].getCodigo()==codigo){
                    for(int k=0;k<viagens[i].getReservas().length;k++){
                        if(viagens[i].getReservas()[k]!=null){
                            if(viagens[i].getReservas()[k].getCliente()==cliente){ viagens[i].getReservas()[k]=null;break;}
                        }
                    }
                }
            }
        }
    }
    
    public void eliminaViagem(){
        int c;
        Scanner sc=new Scanner(System.in);
        System.out.println("Escreva o codigo da viagem que pretende eliminar:");
        c=sc.nextInt();
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]!=null){
                if(viagens[i].getCodigo()==c) viagens[i]=null;
            }
        }
    }
    
    public void eliminaCliente(){
        String user;
        Scanner sc=new Scanner(System.in);
        Boolean a=false;
        do{
            System.out.println("Escreva o username:");
            user=sc.nextLine();
            if(user.equals("0")) break;
            for(int i=0;i<clientes.length;i++){
                if(clientes[i]!=null){
                    if(clientes[i].getUsername().equals(user)){ clientes[i]=null; a=true; break;}
                }
            }
        }while(a==false);
    }
    public void reservar(Cliente reservador,Data dataAtual){
       int codigo;
       int lugar=0;
       Double preco;
       Autocarro autocarro=new Autocarro();
       Viagem viagem=new Viagem();
       String matricula;
       Scanner sc=new Scanner (System.in);
       System.out.println("Codigo da Viagem:");
       codigo=sc.nextInt();
       sc.nextLine();
       for(int i=0;i<viagens.length;i++){
           viagem=viagens[i];
           if(viagens[i].getCodigo()==codigo) break;
       }
       if(efetuada(viagem,dataAtual)==false){
            if(lugaresVazios(viagem)==0){ 
                 System.out.println("Esta Viagem esta cheia. Voce ira ser colocado na lista de espera");
                 for(int i=0;i<viagem.getEspera().length;i++){
                     if(viagem.getEspera()[i]==null){
                         viagem.getEspera()[i]=reservador;
                          break;
                     }
                 }
             }
             else{
                 System.out.println("Esta viagem tem os seguintes autocarros disponiveis: ");
                 for(int l=0;l<viagem.getAutocarros().length;l++){
                     if(viagem.getAutocarros()[l]!=null)
                        System.out.printf("%s\n",viagem.getAutocarros()[l].getMatricula());
                 }
                 System.out.println("Escolha um dos autocarros: ");
                 matricula=sc.nextLine();
                 for(int k=0;k<viagem.getAutocarros().length;k++){
                     if(viagem.getAutocarros()[k]!=null)
                        if(matricula.equals(viagem.getAutocarros()[k].getMatricula()))autocarro=viagem.getAutocarros()[k];break;
                 }
                 if(reservador.getId().equals("Premium")) preco=viagem.getPreco()*0.9;
                 else preco=viagem.getPreco();
                 System.out.println("Lugares disponiveis no autocarro "+matricula+" :");
                 for(int i=0;i<autocarro.getLugares().length;i++){
                     if(autocarro.getLugares()[i]==null){
                         System.out.printf(" %d",i+1);
                     }
                 }

                  System.out.println("Escolha o seu lugar:");
                  lugar=sc.nextInt();
                  autocarro.getLugares()[lugar-1]=reservador;
                  for(int i=0;i<viagem.getReservas().length;i++){
                      if(viagem.getReservas()[i]==null){
                          viagem.getReservas()[i]=new Reserva(reservador,autocarro,lugar,preco);
                          break;
                      }

                 }
            }
       }
       else System.out.println("Esta viagem já decorreu");
   }
    
    public Viagem devolveViagem(int codigo){
        Viagem viagem=new Viagem();
        for(int i=0;i<viagens.length;i++){
           viagem=viagens[i];
           if(viagens[i].getCodigo()==codigo) return viagem;
        }
        return viagem;
    }
    
    public void mostrareservas(Cliente cliente){
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]!=null){
                for(int k=0;k<viagens[i].getReservas().length;k++){
                    if(viagens[i].getReservas()[k]!=null){
                        if(viagens[i].getReservas()[k].getCliente()==cliente){
                            System.out.println(viagens[i].getReservas()[k].toString());
                        } 
                    }
                }
            }
        }
    }
    
    public void mostrarAutocarros(){
        Autocarro a=new Autocarro();
        for(int i=0;i<autocarros.length;i++){
            if(autocarros[i]==null){
                
            }
            else System.out.println(autocarros[i].toString());
        }
    }
    public void mostrarClientes(){
        for(int i=0;i<counter(clientes);i++){
            if(clientes!=null){
                System.out.println(clientes[i].toString());
            }
        }
    }
    public void criaProtoAdmin(){
        admins[0]=new Admin("Proto Admin","Rodoviaria","protoadmin@rodoviaria.pt",123456789,235962867,"protoadmin","789");
    }
    
    public void mostrarViagens(){
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]!=null){
            System.out.println(this.viagens[i].toString());}
        }
    }
    public boolean verificarViagem(int codigo){
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]==null){}
            else{
                if(viagens[i].getCodigo()==codigo){
                    System.out.println("Existe uma Viagem com este codigo.");
                    return false;
                }
            }
        }
        return true;
    }
    
    public int lugaresVazios(Viagem viagem){
        int lugaresvazios=0;
        for(int i=0;i<viagem.getReservas().length;i++){
            if(viagem.getReservas()[i]==null){
                lugaresvazios++;
            }
        }
        return lugaresvazios;
    }
    
    public void mostraReservas(){
        for(int i=0;i<viagens.length;i++){
            if(viagens[i]!=null){
                for(int k=0;k<viagens[i].getReservas().length;k++){
                    if(viagens[i].getReservas()[k]!=null){
                        System.out.println(viagens[i].getReservas()[k].toString());
                    }
                }
            }
        }
    }
    
    
    
    public void editAutocarro(){
        String m;
        Autocarro autocarro;
        int i;
        Scanner sc=new Scanner(System.in);
        m=sc.nextLine();
        if(devolveAutocarro(m).getMatricula().equals(m)){
            autocarro=devolveAutocarro(m);
            System.out.println("1 -> Editar matricula.");
            System.out.println("2 -> Editar Lotaçao.");
            i=sc.nextInt();
            switch(i){
                case 1:{
                    System.out.println("Escreva a nova matricula.");
                    m=sc.nextLine();
                    autocarro.setMatricula(m);
                }
                case 2:{
                    System.out.println("Escreva a nova lotação:");
                    i=sc.nextInt();
                    autocarro.setLotacao(i);
                    autocarro.setLugares(new Cliente[i]);
                }
            }
        }
    }
    
    public Autocarro devolveAutocarro(String m){
        for(int i=0;i<autocarros.length;i++){
            if(autocarros[i]!=null){
                if(autocarros[i].getMatricula().equals(m)){
                    return autocarros[i];
                }
            }
        }
        return new Autocarro();
    }
            
            
    public boolean verificaCredenciais (String username,String password){
        for(int i=0;i<2/*cookies*/;i++){
            switch(i){
                    case 0:{
                        if(counter(clientes)!=0){
                            for(int l=0;l<=counter(clientes);l++){
                                if(clientes[l]!=null){
                                    if(clientes[l].getUsername().equals(username) && clientes[l].getPassword().equals(password)) return true;
                                }
                            }
                            break;}}
                    case 1:{
                        if(counter(admins)!=0){
                            for(int l=0;l<counter(admins);l++){
                                if(admins[l]!=null){
                                if(admins[l].getUsername().equals(username) && admins[l].getPassword().equals(password)) return true;
                            }}
                            break;}}
            }
        }
        return false;
            
    }
    
    public boolean verificaUsername (String username){
        for(int i=0;i<2/*cookies*/;i++){
            switch(i){
                    case 0:{
                        for(int l=0;l<clientes.length;i++){
                            if(clientes[l].getUsername().equals(username)) return true;
                        }
                        break;}
                    case 1:{
                        for(int l=0;l<admins.length;i++){
                            if(admins[l].getUsername().equals(username)) return true;
                        }
                        break;}
            }
        }
        return false;
            
    }
    
    public String verificaId (String username){
        for(int i=0;i<2/*cookies*/;i++){
            switch(i){
                    case 0:{
                        for(int l=0;l<counter(clientes);l++){
                                if(clientes[l].getUsername().equals(username)) return clientes[l].getId();
                        }
                        break;}
                    case 1:{
                        for(int l=0;l<counter(admins);l++){
                            if(admins[l].getUsername().equals(username)) return admins[l].getId();
                        }
                        break;}
            }
        }
        return " ";     
    }
    
    public void criarConta(){
        String nome;
        String email;
        String morada;
        int tlm;
        long nif;
        String username;
        String password;
        String id;
        boolean avail;
        Scanner sc=new Scanner(System.in);
        System.out.println("Nome: ");
        nome=sc.nextLine();
        System.out.println("Morada: ");
        morada=sc.nextLine();
        System.out.println("E-mail: ");
        email=sc.nextLine();
        do{
            System.out.println("Número de Telefone:");
            tlm=sc.nextInt();
            sc.nextLine();
            if(tlm<99999999 || tlm>1000000000) System.out.println("O NUMERO DE TELEFONE TEM DE TER 9 ALGARISMOS!\nTENTE OUTRAVEZ!");
        }while(tlm<99999999 || tlm>1000000000);
        do{
            System.out.println("Número de Identificação Fiscal: ");
            nif=sc.nextLong();
            sc.nextLine();
            if(nif<99999999 || nif>1000000000) System.out.println("O NIF TEM DE TER 9 ALGARISMOS!\nTENTE OUTRAVEZ!");
        }while(nif<99999999 || nif>1000000000);
        
        do{
            System.out.println("Username: ");
            username=sc.nextLine();
            System.out.println("Password: ");
            password=sc.nextLine();
            avail=verificaCredenciais(username,password);
            if(avail==true) System.out.println("Username e/ou Password já esta a ser utilizada por outro utilizador.\n Tente outravez!");
        }while(avail==true);
        do{
            System.out.println("Tipo de Conta(Administrador,Regular,Premium):");
            id=sc.nextLine();
            if(id.equals("Regular") || id.equals("regular") || id.equals("r") || id.equals("R")){
                for(int i=0;i<clientes.length;i++){
                    if(clientes[i]==null){
                        clientes[i]=new Regular(nome,morada,email,nif,tlm,username,password);
                        break;
                    }
                }
            }
            else if(id.equals("Premium") || id.equals("premium") || id.equals("p") || id.equals("P")){
                for(int i=0;i<clientes.length;i++){
                    if(clientes[i]==null){
                        clientes[i]=new Premium(nome,morada,email,nif,tlm,username,password);
                        break;
                    }
                }
            }
        }while(!id.equals("Regular") && !id.equals("regular") && !id.equals("r") && !id.equals("R") && !id.equals("Premium") && !id.equals("premium") && !id.equals("p") && !id.equals("P") );
    }
    
    public Cliente devolveCliente(String username){
        for(int i=0;i<clientes.length;i++){
            if(clientes[i]!=null){
                if(clientes[i].getUsername().equals(username)) return clientes[i];
            }         
        }
        return cvazio;
    }
    
    public Admin devolveAdmin(String username){
        for(int i=0;i<admins.length;i++){
            if(admins[i]!=null){
                if(admins[i].getUsername().equals(username)) return admins[i];
            }         
        }
        return avazio;
    }
   
    public boolean efetuada(Viagem viagem,Data dataAtual){
        Data data=viagem.getPartida();
        if(data.getAno()==dataAtual.getAno()){
            if(data.getMes()==dataAtual.getMes()){
                if (data.getDia()==dataAtual.getDia()){
                    if (data.getHora()==dataAtual.getHora()){
                        if (data.getMinuto()==dataAtual.getMinuto()) return true;
                        else if (data.getMinuto()<dataAtual.getMinuto()) return true;
                        else return false;
                    }
                    else if (data.getHora()<dataAtual.getHora()) return true;
                    else return false;
                }
                else if (data.getDia()<dataAtual.getDia()) return true;
                else return false;
            }
            else if (data.getMes()<dataAtual.getMes()) return true;
            else return false;
        }
        else if (data.getAno()<dataAtual.getAno()) return true;
        else return false;
    }
    
    public void editaViagem(Agencia app,Data dataAtual){
        int i;
        Scanner sc=new Scanner(System.in);
        System.out.println("Codigo da viagem que pretende alterar:");
        i=sc.nextInt();
        if(verificarViagem(i)==false){
            devolveViagem(i).editViagem(app, dataAtual);
        }
        else System.out.println("Não existe um Viagem com esse codigo");
    }
    
    public Agencia() {
    }

    public Utilizador[] getClientes() {
        return clientes;
    }

    public void setClientes(Cliente[] clientes) {
        this.clientes = clientes;
    }

    public Viagem[] getViagens() {
        return viagens;
    }

    public void setViagens(Viagem[] viagens) {
        this.viagens = viagens;
    }

    public Admin[] getAdmins() {
        return admins;
    }

    public void setAdmins(Admin[] admins) {
        this.admins = admins;
    }

    public Autocarro[] getAutocarros() {
        return autocarros;
    }

    public void setAutocarros(Autocarro[] autocarros) {
        this.autocarros = autocarros;
    }

    public Cliente getCvazio() {
        return cvazio;
    }

    public void setCvazio(Cliente cvazio) {
        this.cvazio = cvazio;
    }


    public Admin getAvazio() {
        return avazio;
    }

    public void setAvazio(Admin avazio) {
        this.avazio = avazio;
    }

    public int counter(Utilizador[] contado){
        int i=0;
        while(contado[i]!=null)i++;
        return i;
    }
    
    public int contador(Viagem viagem){
        int c=0;
        for(int i=0;i<viagem.getReservas().length;i++){
            if(viagem.getReservas()[i]!=null) c++;
        }
        return c;
    }
    public int contaPontos(Viagem viagem){
        int pontos=0;
        for(int i=0;i<viagem.getRatings().length;i++){
            if(viagem.getRatings()[i]!=null){
                pontos=pontos+viagem.getRatings()[i].getRating();
            } 
        }
        return pontos;
    }
    
}

