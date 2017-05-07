package br.com.zup.enuns;

public enum EnumTipos {

	STRING(1, "String") , 
	INTEGER(2, "Integer"),
	DOUBLE(3, "Double"),
	FLOAT(4, "float"),
	OBJECT(5, "Object"),
	BIGDECIMAL(6, "BigDecimal");

	private int codigo;
	private String descricao;

	
	/**
	 * Construtor básico da enumeração...
	 * 
	 * @param codigo - Código da enumeração
	 * @param descricao - Descrição da enumeração
	 */
	EnumTipos(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	
	/**
	 * Retorna codigo
	 * 
	 * @return codigo - codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Especifica codigo
	 * 
	 * @param codigo - codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Retorna descricao
	 * 
	 * @return descricao - descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Especifica descricao
	 * 
	 * @param descricao - descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Metodo para obter o enum a partir do código
	 * 
	 * @param codigo - código do enum.
	 * @return TipoRequisicaoEnum - enum.
	 */
	public static EnumTipos obterTipoRequisicaoEnum(int codigo){   
		switch(codigo) {  
        	case 1: return STRING;   
        	case 2: return INTEGER;
        	case 3: return DOUBLE;   
        	case 4: return FLOAT;
        	case 5: return OBJECT;   
        	case 6: return BIGDECIMAL;   
        	
        	default: return null;
		}
    } 
}
