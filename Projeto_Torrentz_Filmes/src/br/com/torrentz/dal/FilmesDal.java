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
import br.com.torrentz.model.Filmes;
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
public class FilmesDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public FilmesDal() throws Exception {
        conexao = Conexao.getConexao();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addFilmes(Filmes filmes) throws Exception {
        
        String sql = "INSERT INTO filmes (fil_sinopse, fil_titulo, fil_ano, fil_cat_iden) VALUES (?,?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, filmes.getFil_sinopse());
            preparedStatement.setString(2, filmes.getFil_titulo());
            preparedStatement.setInt(3, filmes.getFil_ano());
            preparedStatement.setInt(4, filmes.getFil_cat_iden().getCat_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("filmes_fil_titulo_key")) {
                throw new RuntimeException("Já existe um filme com este titulo cadastrado em nosso banco de dados");
            }
             if (error.getMessage().contains("filmes_fil_sinopse_key")) {
                throw new RuntimeException("Já existe um filme com esta sinopse cadastrado em nosso banco de dados");
            }
              
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteFilmes(int fil_iden) throws Exception {
        String sql = "DELETE FROM filmes WHERE fil_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, fil_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("filmes_fil_cat_iden_fkey")) {
                throw new RuntimeException("Algum usuário tem relacionamento de visualização com este filme");
            }
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateFilmes(Filmes filmes) throws Exception {
        
        String sql = "UPDATE filmes SET fil_sinopse=?, fil_titulo=?, fil_ano=?, fil_cat_iden=? WHERE fil_iden=?";
        
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, filmes.getFil_sinopse());
            preparedStatement.setString(2, filmes.getFil_titulo());
            preparedStatement.setInt(3, filmes.getFil_ano());
            preparedStatement.setInt(4, filmes.getFil_cat_iden().getCat_iden());
            preparedStatement.setInt(5, filmes.getFil_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível altera este filme!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Filmes> getAllFilmes() throws Exception {
        
        ArrayList<Filmes> lista = new ArrayList<Filmes>();

        String sql = "SELECT * FROM filmes";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Filmes filmes = new Filmes();
                
                filmes.setFil_iden(rs.getInt("fil_iden"));               
                filmes.setFil_sinopse(rs.getString("fil_sinopse"));
                filmes.setFil_titulo(rs.getString("fil_titulo"));
                filmes.setFil_ano(rs.getInt("fil_ano"));

                //Utilizando chave estrangeira
                int fil_cat_iden = rs.getInt("fil_cat_iden");
                CategoriasDal catDal = new CategoriasDal();
                Categorias objetoCategorias = catDal.getCategoriasById(fil_cat_iden);
                filmes.setFil_cat_iden(objetoCategorias);
                lista.add(filmes);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Filmes getFilmesById(int fil_iden) throws Exception {

        Filmes filmes = new Filmes();

        String sql = "SELECT * FROM filmes WHERE fil_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, fil_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                filmes.setFil_iden(rs.getInt("fil_iden"));
                filmes.setFil_sinopse(rs.getString("fil_sinopse"));
                filmes.setFil_titulo(rs.getString("fil_titulo"));
                filmes.setFil_ano(rs.getInt("fil_ano"));

                //Utilizando chave estrangeira
                int fil_cat_iden = rs.getInt("fil_cat_iden");
                CategoriasDal catDal = new CategoriasDal();
                Categorias objetoCategorias = catDal.getCategoriasById(fil_cat_iden);
                filmes.setFil_cat_iden(objetoCategorias);
            }
        } catch (Exception error) {
            throw error;
        }
        return filmes;
    }
    
    public Filmes getFilmesNome(String nome) throws Exception {

        Filmes filmes = new Filmes();

        String sql = ("SELECT * FROM filmes WHERE fil_titulo=?");

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                
                filmes.setFil_iden(rs.getInt("fil_iden"));
                filmes.setFil_sinopse(rs.getString("fil_sinopse"));
                filmes.setFil_titulo(rs.getString("fil_titulo"));
                filmes.setFil_ano(rs.getInt("fil_ano"));

                //Utilizando chave estrangeira
                int fil_cat_iden = rs.getInt("fil_cat_iden");
                CategoriasDal catDal = new CategoriasDal();
                Categorias objetoCategorias = catDal.getCategoriasById(fil_cat_iden);
                filmes.setFil_cat_iden(objetoCategorias);
            }
        } catch (Exception error) {
            throw error;
        }
        return filmes;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
