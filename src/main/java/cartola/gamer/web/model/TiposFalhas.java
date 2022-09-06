package cartola.gamer.web.model;



import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tipos_falhas")

public class TiposFalhas{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank(message = "obrigatório")
    @Column(name = "descricao")
    private String descricao;

    public TiposFalhas(){}

    public TiposFalhas(String descricao){
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
