package br.com.zup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.zup.data.AtributoDao;
import br.com.zup.data.ModeloDao;
import br.com.zup.enuns.EnumTipos;
import br.com.zup.model.Atributo;
import br.com.zup.model.Modelo;
import br.com.zup.vorest.AtributoRest;



@Controller
@RequestMapping("/rest/modelo")
public class ModeloRestController {
	
	  @Autowired
	  private ModeloDao modeloDao; 
	  
	  @Autowired
	  private AtributoDao atributoDao;

    // lista todos os atributos do modelo "X"     -- ok
    @RequestMapping(value = "/{nome}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<AtributoRest> listarTodosAtributos(@PathVariable("nome") String nome) {
    	
    	List<AtributoRest> atributoRests = converter(modeloDao.buscaPorAtributoJoinJPA(nome));
    	
        return atributoRests;
    }
    
 // busca o atributo do modelo "X" -- ok
    @RequestMapping(value = "/{nome}/{id}", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody AtributoRest buscarAtributoPorNomeModelo(@PathVariable("nome") String nome, @PathVariable("id")  Integer id) {
    	Atributo atributo = modeloDao.buscaPorNomeEIdJoinJPA(nome,id);
    	
    	
        return new AtributoRest(atributo.getName(), atributo.getEnumTipos().getCodigo(), atributo.getModelo().getName());
    }
    
    // inclui um atributo do modelo "X"
    @RequestMapping(value = "/{nome}", method = RequestMethod.POST)
    public @ResponseBody String incluir(@RequestBody AtributoRest atributo, @PathVariable("nome") String nome) {
    	Modelo modelo = modeloDao.buscaPorNome(nome);
    	
    	atributoDao.grava(new Atributo(atributo.getNome(), modelo, EnumTipos.obterTipoRequisicaoEnum(atributo.getCodigoTipo())));
    	
    	return "criado";
    }
    
    // altera um atributo do modelo "X"
    @RequestMapping(value = "/{nome}/{id}", method = RequestMethod.PUT)
    public @ResponseBody String alterar(@RequestBody AtributoRest atributo, @PathVariable("nome") String nome, @PathVariable("id")  Integer id) {
    	Atributo novoAtritbuto = modeloDao.buscaPorNomeEIdJoinJPA(nome, id);
    	
    	novoAtritbuto.setName(atributo.getNome());
    	novoAtritbuto.setEnumTipos(EnumTipos.obterTipoRequisicaoEnum(atributo.getCodigoTipo()));
    	
    	atributoDao.atualiza(novoAtritbuto);
    	
    	
    	return "criado";
    }

    // remove um atributo do modelo "X"
    @RequestMapping(value = "/{nome}/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody String deletaAtributo(@PathVariable("nome") String nome, @PathVariable("id")  Integer id) {
    	Atributo atributo = modeloDao.buscaPorNomeEIdJoinJPA(nome,id);
    	
    	String nomeAtributo  = atributo.getName();
    	
    	atributoDao.deleta(atributo);
    	
    	return "deletado atributo nome : "+nomeAtributo;
    }
    
    private List<AtributoRest> converter(List<Atributo> lista){
    	List<AtributoRest> atributoRests = new ArrayList<AtributoRest>();
    	for (Atributo atributo : lista) {
    		atributoRests.add(new AtributoRest(atributo.getName(), atributo.getEnumTipos().getCodigo(), atributo.getModelo().getName()));
    	}
    	
    	return atributoRests;
    }
}