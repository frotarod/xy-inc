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
package br.com.zup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.data.AtributoDao;
import br.com.zup.data.ModeloDao;
import br.com.zup.enuns.EnumTipos;
import br.com.zup.model.Atributo;
import br.com.zup.model.Modelo;

@Controller
@RequestMapping(value = "/visualizar")
public class AtributoController {
	@Autowired
    private AtributoDao atributoDao;

    @Autowired
    private ModeloDao modeloDao;
    
    private EnumTipos[] enumTipos ; 
    
    private List<String> modelos = new ArrayList<String>();
    

    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String exibirAtributos(Model model) {
        model.addAttribute("novoAtributo", new Atributo());
        
        model.addAttribute("atributos", atributoDao.buscaTodosOrdenadoNome());
        modelos = new ArrayList<String>();
        
        modelos = nomesModelos(modeloDao.buscaTodos(),modelos);

        model.addAttribute("nomeModelos", modelos);

        
        model.addAttribute("enuns",getStatuses());

        return "visualizar";
    }
    
    public EnumTipos[] getStatuses() {
        return EnumTipos.values();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String gravaAtributo(@Valid @ModelAttribute("novoAtributo") Atributo atributo, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
            	atributo.setModelo(modeloDao.buscaPorNome(atributo.getModelo().getName()));
            	
            	atributoDao.grava(atributo);
            	
            	return "redirect:/visualizar";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("atributos",  atributoDao.buscaTodosOrdenadoNome());
                model.addAttribute("error", e.getCause().getCause());
                return "index";
            }
        } else {
            model.addAttribute("atributos", atributoDao.buscaTodosOrdenadoNome());
            return "index";
        }
    }

    
    private List<String> nomesModelos(List<Modelo> modelos, List<String> nomeModelos){
    	for (Modelo modelo : modelos) {
    		nomeModelos.add(modelo.getName());
		}
    	return nomeModelos;
    }
    
	public EnumTipos[] getEnumType() {
		return enumTipos;
	}

	public void setEnumType(EnumTipos[] enumType) {
		this.enumTipos = enumType;
	}

	public List<String> getModelos() {
		return modelos;
	}

	public void setModelos(List<String> modelos) {
		this.modelos = modelos;
	}
}
