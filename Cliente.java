
package projeto;

import java.io.Serializable;


public class Cliente extends Utilizador implements Serializable {
    private String id="Cliente";
   
    public String getId(){
        return id;
    }
}

