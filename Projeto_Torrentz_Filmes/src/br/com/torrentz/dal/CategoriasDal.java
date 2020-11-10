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

package br.com.torrentz.dal;

import br.com.torrentz.model.Categorias;
import java.sql.Connection;
import br.com.torrentz.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author JHONATHAN
 */
public class CategoriasDal {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public CategoriasDal() throws Exception {
        conexao = Conexao.getConexao();
    } 
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addCategoria(Categorias categoria) throws Exception {

        String sql = "INSERT INTO categorias(cat_nome) VALUES(?)";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getCat_nome());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Já existe uma categoria com o mesmo nome!");
            }
           
            
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteCategoria(int cat_iden) throws Exception {

        String sql = "DELETE FROM categorias WHERE cat_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cat_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
             if (error.getMessage().contains("filmes_fil_cat_iden_fkey")) {
                throw new RuntimeException("Existe um filme com essa categoria cadastrado em nosso sistema!");
            }
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateCategoria(Categorias categoria) throws Exception {

        String sql = "UPDATE categorias SET cat_nome=? WHERE cat_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, categoria.getCat_nome());
            preparedStatement.setInt(2, categoria.getCat_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar esta categoria!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Categorias> getAllCategorias() throws Exception {

        ArrayList<Categorias> lista = new ArrayList<Categorias>();
        
        String sql = "SELECT * FROM categorias";
        
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Categorias categorias = new Categorias();
                categorias.setCat_nome(rs.getString("cat_nome"));
                categorias.setCat_iden(rs.getInt("cat_iden"));
                lista.add(categorias);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Categorias getCategoriasById(int cat_iden) throws Exception {
        
        Categorias categoria = new Categorias();

        String sql = ("SELECT * FROM categorias WHERE cat_iden=?");

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cat_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoria.setCat_iden(rs.getInt("cat_iden"));
                categoria.setCat_nome(rs.getString("cat_nome"));
            }
        } catch (Exception error) {
            throw error;
        }
        return categoria;
    }
    
    public Categorias getCategoriaNome(String nome) throws Exception {
        
        Categorias cat = new Categorias();

        String sql = "SELECT * FROM categorias WHERE cat_nome=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                cat.setCat_iden(rs.getInt("cat_iden"));
                cat.setCat_nome(rs.getString("cat_nome"));
            }
        } catch (Exception error) {
            throw error;
        }
        return cat;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
