package erp.infra.test.entity;

import erp.infra.annotation.Field;
import erp.infra.annotation.Form;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 * Entidade Produto.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (18/01/2013 14:14)
 */
@Entity
@Table(name="produto")
@Form(id="produto", layout="\n"
        + "              [id____]                 [codint____]                                \n"
        + "              [desc______________________________________________________]         \n"
        + "              [tipo__]                                                             \n"
        + "              [codncm__]               [codgen____]           [codserv___]         \n"
        + "              [aliqicms__]             [custunit__]           [vlrvnd____]         \n"
        + "              [unidestoq_]             [pesounit__]                                \n"
        + "              [datacad___]             [dataultatu]           [prom      ]         \n"
)
public class Produto {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="codigo_interno")
    private String codigoInterno;
    
    private String descricao;
    
    @Column(name="tipo_item")
    private String tipoItem;
    
    @Column(name="codigo_ncm")
    private String codigoNcm;
    
    @Column(name="codigo_genero")
    private String codigoGenero;
    
    // Consultar: Anexo I da Lei Complementar Federal n. 116/03
    @Column(name="codigo_servico")
    private String codigoServico;
    
    @Column(name="aliquota_icms")
    private BigDecimal aliquotaIcms;
    
    @Column(name="custo_unitario")
    private BigDecimal custoUnitario;
    
    @Column(name="valor_venda")
    private BigDecimal valorVenda;
    
    @Column(name="unidade_estoque")
    private String unidadeEstoque;
    
    @Column(name="peso_unitario")
    private String pesoUnitario;
    
    private String situacao;
    
    @Column(name="data_cadastro")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataCadastro;
    
    @Column(name="data_ultima_atualizacao")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataUltimaAtualizacao;
    
    @Transient
    private boolean promocao;
    
    @Field(id="id", label="Id", insertable=false, updatable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Field(id="codint", label="C�d. Interno")
    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    @Field(id="desc", label="Descri��o")
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Field(id="tipo", label="Tipo de item")
    public String getTipoItem() {
        return tipoItem;
    }

    public void setTipoItem(String tipoItem) {
        this.tipoItem = tipoItem;
    }

    @Field(id="codncm", label="C�d. NCM")
    public String getCodigoNcm() {
        return codigoNcm;
    }

    public void setCodigoNcm(String codigoNcm) {
        this.codigoNcm = codigoNcm;
    }

    @Field(id="codgen", label="C�d. do g�nero")
    public String getCodigoGenero() {
        return codigoGenero;
    }

    public void setCodigoGenero(String codigoGenero) {
        this.codigoGenero = codigoGenero;
    }

    @Field(id="codserv", label="C�d. do servi�o")
    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    @Field(id="aliqicms", label="Al�q. ICMS")
    public BigDecimal getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(BigDecimal aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    @Field(id="custunit", label="Custo unit�rio")
    public BigDecimal getCustoUnitario() {
        return custoUnitario;
    }

    public void setCustoUnitario(BigDecimal custoUnitario) {
        this.custoUnitario = custoUnitario;
    }

    @Field(id="vlrvnd", label="Valor de venda")
    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Field(id="unidestoq", label="Unidade de estoque")
    public String getUnidadeEstoque() {
        return unidadeEstoque;
    }

    public void setUnidadeEstoque(String unidadeEstoque) {
        this.unidadeEstoque = unidadeEstoque;
    }

    @Field(id="pesounit", label="Peso unit�rio")
    public String getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(String pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    @Field(id="sit", label="Situacao")
    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Field(id="datacad", label="Data de cadastro")
    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Field(id="dataultatu", label="Data �lt. atualiza��o")
    public Date getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    @Field(id="prom", label="Promo��o")
    public boolean getPromocao() {
        return promocao;
    }

    public void setPromocao(boolean promocao) {
        this.promocao = promocao;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id
                + ", codigoInterno=" + codigoInterno + ", descricao=" + descricao 
                + ", tipoItem=" + tipoItem + ", codigoNcm=" + codigoNcm 
                + ", codigoGenero=" + codigoGenero + ", codigoServico=" 
                + codigoServico + ", aliquotaIcms=" + aliquotaIcms 
                + ", custoUnitario=" + custoUnitario + ", valorVenda=" 
                + valorVenda + ", unidadeEstoque=" + unidadeEstoque 
                + ", pesoUnitario=" + pesoUnitario + ", situacao=" + situacao 
                + ", dataCadastro=" + dataCadastro + ", dataUltimaAtualizacao=" 
                + dataUltimaAtualizacao + '}';
    }

}
