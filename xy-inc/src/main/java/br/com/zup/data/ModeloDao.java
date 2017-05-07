package br.com.zup.data;

import java.util.List;

import br.com.zup.model.Atributo;
import br.com.zup.model.Modelo;

public interface ModeloDao {
	
	public Modelo buscaPorId(int id);

	public Modelo buscaPorNome(String name);

	 public List<Modelo> buscaTodosOrdenadoPor(String coluna);

	public void grava(Modelo domain);
	
	public void atualiza(Modelo modelo);
	  
	public void deleta(Modelo modelo);
	
	public List<Modelo> buscaTodos();
	
	public List<Modelo> buscaTodosJoinJPA();
	
	public Modelo buscaPorIdJoinJPA(Integer id);
	 
	public Modelo buscaPorNomeJoinJPA(String nome);
	
	public Atributo buscaPorNomeEIdJoinJPA(String nome, Integer id);
	
	public List<Atributo> buscaPorAtributoJoinJPA(String nome);
}
