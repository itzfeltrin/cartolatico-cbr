package cartola.gamer.web.model;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "passos")

public class Passos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "obrigatório")
    @Column(name = "descricao")
    private String descricao;

    public Passos(){}

    public Passos(String descricao){
        this.descricao = descricao;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
    
    
}
