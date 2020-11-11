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

import br.com.torrentz.dal.ContratosDal;
import br.com.torrentz.model.Contratos;

import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class ContratosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private ContratosDal contratoDal;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public ContratosBll() throws Exception{
        contratoDal = new ContratosDal();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Contratos contrato) throws Exception {

        try {
            
            ArrayList<Contratos> lista = getConsulta();
      

        for (Contratos cont : lista) {
            if (cont.getCon_usu_iden().getUsu_nome().equals(contrato.getCon_usu_iden().getUsu_nome())) {
                throw new RuntimeException("Usuário já possui um Contrato!");
            }
        }  
            contratoDal.addContratos(contrato);

        } catch (Exception error) {

            throw error;
        }

    }
//--- FIM CREATE ----------------------------------------------------------------------------------|
//
//--- DELETE -------------------------------------------------------------------------------------->
//

    public void Remover(Contratos contrato) throws Exception {

        try {

            contratoDal.deleteContratos(contrato.getCon_iden());

        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Contratos contrato) throws Exception {

        try {

//            if (contrato.getCon_status().length() < 3) {
//                throw new RuntimeException("Nome da categoria inválida!\nNo mínimo 3 caracteres!");
//            }
//            if (contrato.getCon_status().length() > 50) {
//                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
//            }
            contratoDal.updateContratos(contrato);

        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Contratos> getConsulta() throws Exception {
        try {

            return contratoDal.getAllContratos();

        } catch (Exception error) {
            throw error;
        }
    }

    public Contratos getConsultaPorId(int id) throws Exception{
        try {
            return contratoDal.getContratosById(id);
        } catch (Exception error) {
            throw error;
        }
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
