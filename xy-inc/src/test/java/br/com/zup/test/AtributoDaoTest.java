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
import br.com.zup.model.Atributo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-context.xml",
                                   "classpath:/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AtributoDaoTest {

    @Autowired
    private AtributoDao atributoDao;
    
    
    
    @Test
    public void testBuscaPorId() {
    	Atributo atributo  = atributoDao.buscaPorId(0);
    	
    	assertEquals("Zup", atributo.getName());
    	assertEquals(0, atributo.getId());

        return;
    }
    
    @Test
    public void testGravar() {
    	Atributo atributo = new Atributo();
    	atributo.setName("Teste de dados");
    	atributoDao.grava(atributo);

    	int id = atributo.getId();
        assertNotNull(id);
    	
        List<Atributo> lista = atributoDao.buscaTodosOrdenadoNome();
        
        assertEquals(2, lista.size());
    	Atributo novoModelo = lista.get(0);
   	 	assertEquals("Teste de dados", novoModelo.getName());
        
   	 	return;
    }
    
    
    @Test
    public void testBuscaTodosOrdenadoNome() {
    	Atributo atributo = new Atributo();
    	atributo.setName("Teste de dados");
    	atributoDao.grava(atributo);

    	int id = atributo.getId();
        assertNotNull(id);
    	
        List<Atributo> lista = atributoDao.buscaTodosOrdenadoNome();
        
        assertEquals(2, lista.size());
    	Atributo novoModelo = lista.get(0);
   	 	assertEquals("Teste de dados", novoModelo.getName());
        
   	 	return;
    }

}
