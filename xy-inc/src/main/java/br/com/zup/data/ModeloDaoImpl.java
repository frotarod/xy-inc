/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.zup.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.model.Atributo;
import br.com.zup.model.Modelo;

@Repository
@Transactional
public class ModeloDaoImpl implements ModeloDao {
    @Autowired
    private EntityManager em;

    public Modelo buscaPorId(int id) {
        return em.find(Modelo.class, id);
    }

    public Modelo buscaPorNome(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Modelo> criteria = cb.createQuery(Modelo.class);
        Root<Modelo> basicType = criteria.from(Modelo.class);
        criteria.select(basicType).where(cb.equal(basicType.get("name"), name));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Modelo> buscaTodosOrdenadoPor(String coluna) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Modelo> criteria = cb.createQuery(Modelo.class);
       
        Root<Modelo> member = criteria.from(Modelo.class);

        criteria.select(member).orderBy(cb.asc(member.get(coluna)));
        return em.createQuery(criteria).getResultList();
    }
    
    
    public List<Modelo> buscaTodos() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Modelo> criteria = cb.createQuery(Modelo.class);
       
        Root<Modelo> member = criteria.from(Modelo.class);

        criteria.select(member);
        return em.createQuery(criteria).getResultList();
    }

    
    public void grava(Modelo basicTypes) {
    	em.persist(basicTypes);
    	return;
    }
    
    public void atualiza(Modelo modelo) {
        try {
        	 em.merge(modelo);
        } catch (Exception ex) {
             ex.printStackTrace();
             em.getTransaction().rollback();
        }
    }
    
    public void deleta(Modelo modelo) {
        try {
             modelo = em.find(Modelo.class, modelo.getId());
             em.remove(modelo);
        } catch (Exception ex) {
             ex.printStackTrace();
             em.getTransaction().rollback();
        }
    }
    
    public List<Modelo> buscaTodosJoinJPA(){
    	TypedQuery<Atributo> query =  em.createQuery("SELECT  c1 FROM Atributo c1 JOIN c1.modelo c2 WHERE c1.modelo.id = c2.id  ", Atributo.class);
		List<Atributo> results = query.getResultList();
		List<Modelo> modelos = new ArrayList<Modelo>();
		
    	Modelo modeloAux = new Modelo();
    
    	for (Atributo at1 : results) {
    		modeloAux = at1.getModelo();
    		if(modelos.isEmpty()){
    			adiciona(results, modelos, modeloAux);
    		}else if(!modelos.contains(modeloAux)){
    			adiciona(results, modelos, modeloAux);
    		}
		}
    	
    		 
    		
    	return modelos;  
    }

	private void adiciona(List<Atributo> results, List<Modelo> modelos, Modelo modeloAux) {
		List<Atributo> att = new ArrayList<Atributo>();
		for (Atributo atributo2 : results) {
			if(modeloAux.getId() ==atributo2.getModelo().getId()){
				att.add(atributo2);
			}
		}
		modeloAux.setAtributo(att);
		modelos.add(modeloAux);
	}
    
 
	  public Modelo buscaPorIdJoinJPA(Integer id){
		    TypedQuery<Atributo> query =  em.createQuery("SELECT  c1 FROM Atributo c1 JOIN c1.modelo c2 WHERE c1.modelo.id = c2.id  and c2.id = :p ", Atributo.class);
		    query.setParameter("p", id);
		    List<Atributo> results = query.getResultList();
		    
			Modelo modeloAux = new Modelo();
		    for (Atributo at1 : results) {
		    	modeloAux = at1.getModelo();
		    	modeloAux = adiciona(results, modeloAux);
		    }
	    	
		    return modeloAux;  
	    }
	  
	  public Modelo buscaPorNomeJoinJPA(String nome){
		    TypedQuery<Atributo> query =  em.createQuery("SELECT  c1 FROM Atributo c1 JOIN c1.modelo c2 WHERE c2.name = :p ", Atributo.class);
		    query.setParameter("p", nome);
		    List<Atributo> results = query.getResultList();
		    
			Modelo modeloAux = new Modelo();
		    for (Atributo at1 : results) {
		    	modeloAux = at1.getModelo();
		    	modeloAux = adiciona(results, modeloAux);
		    }
	    	
		    return modeloAux;  
	    }

	  public Atributo buscaPorNomeEIdJoinJPA(String nome, Integer id){
		    TypedQuery<Atributo> query =  em.createQuery("SELECT  c1 FROM Atributo c1 JOIN c1.modelo c2 WHERE c2.name = :p1 and c1.id = :p2 ", Atributo.class);
		    query.setParameter("p1", nome);
		    query.setParameter("p2", id);
		    Atributo result = query.getSingleResult();
		    
		    return result;  
	    }

	  
	  public List<Atributo> buscaPorAtributoJoinJPA(String nome){
		    TypedQuery<Atributo> query =  em.createQuery("SELECT  c1 FROM Atributo c1 JOIN c1.modelo c2 WHERE c2.name = :p ", Atributo.class);
		    query.setParameter("p", nome);
		    List<Atributo> results = query.getResultList();
	    	
		    return results;  
	    }

	  
	  
		private Modelo adiciona(List<Atributo> results,  Modelo modeloAux) {
			List<Atributo> att = new ArrayList<Atributo>();
			for (Atributo atributo2 : results) {
				if(modeloAux.getId() ==atributo2.getModelo().getId()){
					att.add(atributo2);
				}
			}
			modeloAux.setAtributo(att);
			return modeloAux;
		}
	 
    
    

}
