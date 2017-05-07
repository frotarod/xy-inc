package br.com.zup.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.data.AtributoDao;
import br.com.zup.data.ModeloDao;
import br.com.zup.enuns.EnumTipos;
import br.com.zup.model.Atributo;
import br.com.zup.model.Modelo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml",
                                   "classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ModeloDaoTest {
    
    @Autowired
    private ModeloDao modeloDao;

    @Autowired
    private AtributoDao atributoDao;
    
    
    
    @Test
    public void testBuscaTodos() {
        Modelo modelo = new Modelo();
        modelo.setName("Teste de dados");

        modeloDao.grava(modelo);
        List<Modelo> modelos = modeloDao.buscaTodos();
        		
        assertEquals(2, modelos.size());
    	
        Modelo novoModelo = modelos.get(1);
    	
        assertEquals("Teste de dados", novoModelo.getName());
    	

        return;
    }
    
    

    @Test
    public void testGravar() {
    	Modelo modelo = new Modelo();
    	modelo.setName("Teste de dados");
    	modeloDao.grava(modelo);

    	int id = modelo.getId();
        assertNotNull(id);
    	
        List<Modelo> lista = modeloDao.buscaTodos();
        
        assertEquals(2, lista.size());
    	Modelo novoModelo = lista.get(1);
   	 	assertEquals("Teste de dados", novoModelo.getName());
        
   	 	return;
    }
    
   
    @Test
    public void testFindPorId() {
    	Modelo modelo  = modeloDao.buscaPorId(0);
    	
    	assertEquals("Zup", modelo.getName());
    	assertEquals(0, modelo.getId());

    	return;
    }

    
    @Test
    public void testBuscaPorNome() {
        Modelo modelo = modeloDao.buscaPorNome("Zup");
        		
    	
        assertEquals("Zup",modelo.getName());
    	
        return;
    }

    @Test
    public void testBuscaTodosOrdenadoPor() {
        Modelo modelo = new Modelo();
        modelo.setName("w");
        
        modeloDao.grava(modelo);

        List<Modelo> lista =   modeloDao.buscaTodosOrdenadoPor("name");
    	
        Modelo novoModelo =  lista.get(1);
        
    	
        assertEquals(2,lista.size());
        assertEquals("w",novoModelo.getName());
    	
        return;
    }
    
    
    
    @Test
    public void testDeleta() {
    	Modelo modelo  = modeloDao.buscaPorId(0);
    	
    	modeloDao.deleta(modelo);
    	
    	List<Modelo> lista = modeloDao.buscaTodos();
    	
    	assertEquals(0, lista.size());

    	return;
    }

    
    @Test
    public void testAtualiza() {
    	Modelo modelo  = modeloDao.buscaPorId(0);
    	modelo.setName("Nova Zup");
    	
    	modeloDao.atualiza(modelo);
    	
    	List<Modelo> lista = modeloDao.buscaTodos();
    	Modelo novoModelo = lista.get(0);
    	
    	
    	assertEquals(1, lista.size());
    	assertEquals("Nova Zup", novoModelo.getName());

    	return;
    }

    
    @Test
    public void testBuscaTodosJoinJPA() {
    	Atributo atributo = new Atributo();
    	Modelo modelo  = modeloDao.buscaPorId(0);
    	atributo.setEnumTipos(EnumTipos.BIGDECIMAL);
    	atributo.setName("atributo zup");
    	atributo.setModelo(modelo);
    	atributoDao.grava(atributo);
    	
    	List<Modelo> modelos  =  modeloDao.buscaTodosJoinJPA();
    	
    	Atributo novoAtributo  = modelos.get(0).getAtributo().get(0);
    	
    	assertEquals(1, modelos.size());
    	assertEquals("atributo zup", novoAtributo.getName());

    	return;
    }

    @Test
    public void testBuscaPorIdJoinJPA() {
    	Atributo atributo = new Atributo();
    	Modelo modelo  = modeloDao.buscaPorId(0);
    	atributo.setEnumTipos(EnumTipos.BIGDECIMAL);
    	atributo.setName("atributo zup");
    	atributo.setModelo(modelo);
    	atributoDao.grava(atributo);
    	
    	Modelo novoModelo  =  modeloDao.buscaPorIdJoinJPA(0);
    	
    	assertNotNull(modelo);
    	assertEquals("Zup", novoModelo.getName());

    	return;
    }
}