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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.zup.data.ModeloDao;
import br.com.zup.model.Modelo;

@Controller
@RequestMapping(value = "/")
public class ModeloController {
    @Autowired
    private ModeloDao modeloDao;
    
    private final static String COLUNA_ORGENACAO="id";
    
    @RequestMapping(method = RequestMethod.GET)
    public String exibirModelos(Model model) {
    	
        model.addAttribute("novoModelo", new Modelo());
        model.addAttribute("modelos", modeloDao.buscaTodosOrdenadoPor(COLUNA_ORGENACAO));
        
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String gravaNovoModelo(@Valid @ModelAttribute("novoModelo") Modelo modelo, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            try {
            		modeloDao.grava(modelo);
         
            		model.addAttribute("modelos", modeloDao.buscaTodosOrdenadoPor(COLUNA_ORGENACAO));
            	
            	return "redirect:/visualizar";
            } catch (UnexpectedRollbackException e) {
                model.addAttribute("modelos", modeloDao.buscaTodosOrdenadoPor(COLUNA_ORGENACAO));
                model.addAttribute("error", e.getCause().getCause());
                return "index";
            }
        } else {
            model.addAttribute("modelos",  modeloDao.buscaTodosOrdenadoPor(COLUNA_ORGENACAO));
            return "index";
        }
    }
    
    
}
