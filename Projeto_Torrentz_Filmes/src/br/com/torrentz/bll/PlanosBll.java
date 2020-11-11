/*
 *  -------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 03/11/2020 15:44:59 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/2
 *  Disciplina : mbd - Modelagem de banco de dados
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : PROJETO CAMADAS
 *  Exercício  : Torrentz Filmes
 *  ---------------------------------------------------------------------------------------------------
 *  Propósito do arquivo (Objetivo).
 *  ---------------------------------------------------------------------------------------------------| 
 */

package br.com.torrentz.bll;

import br.com.torrentz.dal.PlanosDal;
import br.com.torrentz.model.Planos;

import java.util.ArrayList;

/**
 *
 * @author JHONATHAN
 */
public class PlanosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private PlanosDal planosdal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    // 
    public PlanosBll() throws Exception, ClassNotFoundException {
        planosdal = new PlanosDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Planos planos) throws Exception {

        try {
            if (planos.getPla_nome().length() < 3)
                throw new RuntimeException("Nome do plano inválido\nNo mínimo 3 caracteres!");
  
            if (planos.getPla_nome().length() > 50)
                throw new RuntimeException("Nome do plano inválido\nMáximo de caracteres excedido!");
            
            if(planos.getPla_acesso_simultaneo() <= 0) 
                throw new RuntimeException("Acesso simultaneo não pode ser menor ou igual a 0");
            
            if(planos.getPla_preco()<= 0) 
                throw new RuntimeException("Preço não pode ser menor ou igual a 0");

            planosdal.addPlanos(planos);
        } catch (Exception error) {

            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Planos planos) throws Exception {

        try {
            if (planos.getPla_nome().length() < 3) 
                throw new RuntimeException("Nome do plano inválido!\nNo mínimo 3 caracteres!");
            
            if (planos.getPla_nome().length() > 50)
                throw new RuntimeException("Nome do plano inválido\nMáximo de caracteres excedido!");
            
            planosdal.updatePlanos(planos);
        } catch (Exception error) {

            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Remover(Planos planos) throws Exception {

        try {
            planosdal.deletePlanos(planos.getPla_iden());
        } catch (Exception error) {

            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Planos> getConsulta() throws Exception {

        try {
            return planosdal.getAllPlanos();
        } catch (Exception error) {

            throw error;
        }
    }

    public Planos getConsultaPorId(int pla_iden) throws Exception {
        try {
            return planosdal.getPlanosById(pla_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Planos getPlanosNome(String nome) throws Exception {
        try {
            return planosdal.getPlanosNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
