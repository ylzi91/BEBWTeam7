package yurilenzi.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="utenti")
public class Utenti {
    @Id
    @GeneratedValue
    @Column(name="id_utente")
    private UUID utenteId;

    @OneToMany(mappedBy = "utenti")
    private List<Tessere> tessere;

    private String nome;
    private String cognome;
    private boolean autorizzazione;

    public Utenti(){

    }

    public Utenti(String nome, String cognome, boolean autorizzazione) {
        this.nome = nome;
        this.cognome = cognome;
        this.autorizzazione = autorizzazione;
    }

    public UUID getUtenteId() {
        return utenteId;
    }

    public List<Tessere> getTessere() {
        return tessere;
    }

    public void setTessere(List<Tessere> tessere) {
        this.tessere = tessere;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public boolean isAutorizzazione() {
        return autorizzazione;
    }

    public void setAutorizzazione(boolean autorizzazione) {
        this.autorizzazione = autorizzazione;
    }

    @Override
    public String toString() {
        return "Utenti{" +
                "utenteId=" + utenteId +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", autorizzazione=" + autorizzazione +
                '}';
    }
}
