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

    @OneToMany(mappedBy = "id_utente")
    private List<Tessere> tessere;

    private String nome;
    private String cognome;
    private boolean autorizzazione;
}
