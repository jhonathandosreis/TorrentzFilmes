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

import br.com.torrentz.dal.VisualizadosDal;
import br.com.torrentz.model.Visualizados;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class VisualizadosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private VisualizadosDal visualizadosDal;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public VisualizadosBll() throws SQLException, ClassNotFoundException {
        visualizadosDal = new VisualizadosDal();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Visualizados visualizado) throws SQLException {

        try {
            //           if (visualizado.isVis_completo().length() < 3) {
//                throw new RuntimeException("Nome da categoria inválida\nNo mínimo 3 caracteres!");
//            }
            //           if (visualizado.isVis_completo()length() > 50) {
//                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
//            }

            visualizadosDal.addVisualizados(visualizado);

        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void Remover(Visualizados visualizado) throws SQLException {

        try {

            visualizadosDal.deleteVisualizados(visualizado.getVis_iden());

        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Visualizados visualizado) throws SQLException {

        try {

//            if (contrato.getCon_status().length() < 3) {
//                throw new RuntimeException("Nome da categoria inválida!\nNo mínimo 3 caracteres!");
//            }
//            if (contrato.getCon_status().length() > 50) {
//                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
//            }
            visualizadosDal.updateVisualizados(visualizado);

        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Visualizados> getConsulta() throws SQLException, ClassNotFoundException{
        try {

            return visualizadosDal.getAllVisualizados();

        } catch (SQLException error) {
            throw error;
        }
    }

    public Visualizados getConsultaPorId(int id) throws SQLException, ClassNotFoundException {
        try {
            return visualizadosDal.getVisualizadosById(id);
        } catch (SQLException error) {
            throw error;
        }
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
