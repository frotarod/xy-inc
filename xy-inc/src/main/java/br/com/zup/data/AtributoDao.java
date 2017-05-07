package br.com.zup.data;

import java.util.List;

import br.com.zup.model.Atributo;

public interface AtributoDao {
	
	public Atributo buscaPorId(int id);

	public Atributo buscaPorNome(String name);

	public List<Atributo> buscaTodosOrdenadoNome();

	public void grava(Atributo basicTypes);
	
	public void deleta(Atributo atributo);
	
	public void atualiza(Atributo atributo);
}
