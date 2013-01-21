package erp.tributacao.entidade;

import erp.tributacao.entidade.dao.TributoSistemaDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Entidade NaturezaOperacao
 * 
 * Teste para tributacao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (21/01/2013 00:28)
 */
public class NaturezaOperacao implements Entidade {
    
    private TributoSistemaDao tributoSistemaDao = new TributoSistemaDao();
    
    private Long id;
    
    // Fixo, operacoes implementadas/suportadas pelo sistema:
    // VND, COMPR, DEV_VND, DEV_COMPR
    private String codigo;
    
    private String descricao;
    
    // "E" ou "S"
    private char entradaSaida;
    
    private Map<String, TributoSistema> tributosSistema = new HashMap<String, TributoSistema>();

    private List<SelecionadorCfop> selecionadorCfops
            = new ArrayList<SelecionadorCfop>();
    
    public NaturezaOperacao() {
        // Adiciona todos possiveis TributoSistema's
        // de acordo com o cadastro do emitente se contribuinte para este tributo
        for (TributoSistema tributoSistema : tributoSistemaDao.list()) {
            tributosSistema.put(tributoSistema.getNome(), tributoSistema);
        }
    }

    public NaturezaOperacao(Long id, String codigo, String descricao, char entradaSaida) {
        this();
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
        this.entradaSaida = entradaSaida;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public char getEntradaSaida() {
        return entradaSaida;
    }

    public void setEntradaSaida(char entradaSaida) {
        this.entradaSaida = entradaSaida;
    }

    public Map<String, TributoSistema> getTributosSistema() {
        return tributosSistema;
    }

    public void setTributosSistema(Map<String, TributoSistema> tributosSistema) {
        this.tributosSistema = tributosSistema;
    }

    public List<SelecionadorCfop> getSelecionadorCfops() {
        return selecionadorCfops;
    }

    public void setSelecionadorCfops(List<SelecionadorCfop> selecionadorCfops) {
        this.selecionadorCfops = selecionadorCfops;
    }
    
    public String selecionarCfop(ContextoTributacao contexto) throws Exception {
        List<String> cfops = new ArrayList<String>();
        for (SelecionadorCfop selecionador : selecionadorCfops) {
            if (selecionador.conferir(contexto)) {
                cfops.add(selecionador.getCfop());
            }
        }
        if (cfops.isEmpty()) {
            throw new Exception("Nao foi possivel obter nenhum CFOP para a operacao atual !");
        }
        else if (cfops.size() > 1) {
            throw new Exception("A condicao configurada no sistema retornou mais de um CFOP !");
        }
        return cfops.get(0);        
    }    

    @Override
    public String toString() {
        return "\nNaturezaOperacao{" + "tributoSistemaDao=" + tributoSistemaDao + ", id=" + id + ", codigo=" + codigo + ", descricao=" + descricao + ", entradaSaida=" + entradaSaida + ", tributosSistema=" + tributosSistema + ", selecionadorCfops=" + selecionadorCfops + '}';
    }
    
}