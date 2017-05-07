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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.zup.model.Atributo;

@Repository
@Transactional
public class AtributoDaoImpl implements AtributoDao {
    @Autowired
    private EntityManager em;

    public Atributo buscaPorId(int id) {
        return em.find(Atributo.class, id);
    }

    public Atributo buscaPorNome(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Atributo> criteria = cb.createQuery(Atributo.class);
        Root<Atributo> basicType = criteria.from(Atributo.class);
        criteria.select(basicType).where(cb.equal(basicType.get("name"), name));
        return em.createQuery(criteria).getSingleResult();
    }

    public List<Atributo> buscaTodosOrdenadoNome() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Atributo> criteria = cb.createQuery(Atributo.class);
        Root<Atributo> member = criteria.from(Atributo.class);
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }
    
    public void grava(Atributo basicTypes) {
        em.persist(basicTypes);
        return;
    }
   
    public void deleta(Atributo atributo) {
        try {
        	atributo = em.find(Atributo.class, atributo.getId());
             em.remove(atributo);
        } catch (Exception ex) {
             ex.printStackTrace();
             em.getTransaction().rollback();
        }
    }

    public void atualiza(Atributo atributo) {
        try {
        	 em.merge(atributo);
        } catch (Exception ex) {
             ex.printStackTrace();
             em.getTransaction().rollback();
        }
    }

   
}
