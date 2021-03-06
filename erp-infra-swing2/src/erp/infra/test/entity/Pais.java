package erp.infra.test.entity;

import erp.infra.annotation.Field;
import erp.infra.annotation.Form;
import erp.infra.test.Sigla2Validator;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade Pais.
 * 
 * Os registros podem ser encontrados em: 
 * http://www.bcb.gov.br/rex/ftp/paises.txt
 * 
 * A sigla de 2 letras pode ser encontrada em:
 * http://www.inf.ufrgs.br/~cabral/Paises.html
 * segundo norma ISO3166
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (18/01/2013 23:05)
 */
@Entity
@Table(name = "pais")
@Form(id = "pais", layoutScale=15, verticalPadding=3, layout = "\n"
        + "         [id__]                              \n"
        + "         [codbc_]                            \n"
        + "         [nome_____________________]         \n"
        + "         [s2__   ]                           \n"
)
public class Pais implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_bacen")
    private String codigoBacen;
    
    private String nome;
    
    @Column(name="sigla_2")
    private String sigla2;

    public Pais() {
    }

    public Pais(Long id, String codigoBacen, String nome, String sigla2) {
        this.id = id;
        this.codigoBacen = codigoBacen;
        this.nome = nome;
        this.sigla2 = sigla2;
    }

    @Field(id="id", label="Id", insertable=false, updatable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field(id="codbc", label="C�d. BACEN", regex="[0-9]{5}")
    public String getCodigoBacen() {
        return codigoBacen;
    }

    public void setCodigoBacen(String codigoBacen) {
        this.codigoBacen = codigoBacen;
    }

    @Field(id="nome", label="Nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Field(id="s2", label="Sigla 2", validatorClass=Sigla2Validator.class, regex="[A-Z]{2}")
    public String getSigla2() {
        return sigla2;
    }

    public void setSigla2(String sigla2) {
        this.sigla2 = sigla2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (object==null || !(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.id == null && other.id != null) 
                || (this.id != null && !this.id.equals(other.id))) {
            
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", codigoBacen=" + codigoBacen + ", nome=" + nome + ", sigla2=" + sigla2 + '}';
    }

}
