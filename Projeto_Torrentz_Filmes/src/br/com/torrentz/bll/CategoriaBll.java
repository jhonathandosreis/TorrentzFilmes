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

import br.com.torrentz.dal.CategoriasDal;
import br.com.torrentz.model.Categorias;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author JHONATHAN
 */
public class CategoriaBll {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private CategoriasDal categoriadal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public CategoriaBll() throws SQLException, ClassNotFoundException {
        categoriadal = new CategoriasDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Categorias categorias) throws SQLException {

        try {
            if (categorias.getCat_nome().length() < 3) {
                throw new RuntimeException("Nome da categoria inválida\nNo mínimo 3 caracteres!");
            }
            if (categorias.getCat_nome().length() > 50) {
                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
            }
            categoriadal.addCategoria(categorias);
        } catch (SQLException error) {
            
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Categorias categorias) throws SQLException {

        try {

            if (categorias.getCat_nome().length() < 3) {
                throw new RuntimeException("Nome da categoria inválida!\nNo mínimo 3 caracteres!");
            }
            if (categorias.getCat_nome().length() > 50) {
                throw new RuntimeException("Nome da categoria inválida\nMáximo de caracteres excedido!");
            }
            categoriadal.updateCategoria(categorias);
        } catch (SQLException error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void Remover(Categorias categorias) throws SQLException {
        
        try {
            categoriadal.deleteCategoria(categorias.getCat_iden());
        } catch (SQLException error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Categorias> getConsulta() throws SQLException {
        try {

            return categoriadal.getAllCategorias();
        } catch (SQLException error) {
            throw error;
        }
    }

    public Categorias getConsultaPorId(int cat_iden) throws SQLException {
        try {
            return categoriadal.getCategoriasById(cat_iden);
        } catch (SQLException error) {
            throw error;
        }
    }
    
    public Categorias getCategoriaNome(String nome) throws SQLException{
        try {
            return categoriadal.getCategoriaNome(nome);
        } catch (SQLException error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
