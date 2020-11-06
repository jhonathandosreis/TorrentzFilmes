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
import java.sql.SQLException;
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
    public ContratosBll() throws SQLException, ClassNotFoundException {
        contratoDal = new ContratosDal();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Contratos contrato) throws SQLException {

        try {
            
//            ArrayList<Usuarios> lista = UsuariosBll.getConsulta();
//      
//
//        for (Usuarios uso : lista) {
//            if (contrato.getCon_usu_iden().getUsu_nome().equals(contrato.getCon_usu_iden().getUsu_nome())) {
//                throw new RuntimeException("Usuário já existente!");
//            }
//        }
            
           
//            if (contrato.getCon_inicio()) {
//                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
//            }
            contratoDal.addContratos(contrato);

        } catch (SQLException error) {

            throw error;
        }

    }
//--- FIM CREATE ----------------------------------------------------------------------------------|
//
//--- DELETE -------------------------------------------------------------------------------------->
//

    public void Remover(Contratos contrato) throws SQLException {

        try {

            contratoDal.deleteContratos(contrato.getCon_iden());

        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Contratos contrato) throws SQLException {

        try {

//            if (contrato.getCon_status().length() < 3) {
//                throw new RuntimeException("Nome da categoria inválida!\nNo mínimo 3 caracteres!");
//            }
//            if (contrato.getCon_status().length() > 50) {
//                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
//            }
            contratoDal.updateContratos(contrato);

        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Contratos> getConsulta() throws SQLException , ClassNotFoundException{
        try {

            return contratoDal.getAllContratos();

        } catch (SQLException error) {
            throw error;
        }
    }

    public Contratos getConsultaPorId(int id) throws SQLException , ClassNotFoundException{
        try {
            return contratoDal.getContratosById(id);
        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
