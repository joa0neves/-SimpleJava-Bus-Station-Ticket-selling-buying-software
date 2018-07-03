
package projeto;

import java.io.Serializable;


public class Rating implements Serializable{
    String nome;
    String comentario;
    int rating;

    public Rating(String nome, String comentario, int rating) {
        this.nome = nome;
        this.comentario = comentario;
        this.rating = rating;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    
    
}
