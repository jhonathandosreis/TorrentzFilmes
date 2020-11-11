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

import br.com.torrentz.model.Planos;
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
public class PlanosDal {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;  
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public PlanosDal()throws Exception {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addPlanos(Planos planos) throws Exception {

        String sql = "INSERT INTO planos(pla_acesso_simultaneo, pla_nome, pla_preco) VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, planos.getPla_acesso_simultaneo());
            preparedStatement.setString(2, planos.getPla_nome());
            preparedStatement.setFloat(3, planos.getPla_preco());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Já existe um plano com este nome!");
            }
        }
    }    

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deletePlanos(int pla_iden) throws Exception {

        String sql = "DELETE FROM planos WHERE pla_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, pla_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("contratos_con_pla_iden_fkey")) {
                throw new RuntimeException("Não é possível deletar este plano pois esta vinculado a um contrato!");
            }
        }
    }
    
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updatePlanos(Planos planos) throws Exception {

        String sql = "UPDATE planos SET pla_acesso_simultaneo=?, pla_nome=?, pla_preco=? WHERE pla_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, planos.getPla_acesso_simultaneo());
            preparedStatement.setString(2, planos.getPla_nome());
            preparedStatement.setFloat(3, planos.getPla_preco());
            preparedStatement.setInt(4, planos.getPla_iden());
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar este plano!");
            }
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Planos> getAllPlanos() throws Exception {

        ArrayList<Planos> lista = new ArrayList<Planos>();
        String sql = "SELECT * FROM planos";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Planos planos = new Planos();                
                planos.setPla_iden(rs.getInt("pla_iden"));
                planos.setPla_acesso_simultaneo(rs.getInt("pla_acesso_simultaneo"));
                planos.setPla_nome(rs.getString("pla_nome"));
                planos.setPla_preco(rs.getFloat("pla_preco"));
                lista.add(planos);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Planos getPlanosById(int pla_iden) throws Exception {

        Planos planos = new Planos();

        String sql = "SELECT * FROM planos WHERE pla_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, pla_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                planos.setPla_iden(rs.getInt("pla_iden"));
                planos.setPla_acesso_simultaneo(rs.getInt("pla_acesso_simultaneo"));
                planos.setPla_nome(rs.getString("pla_nome"));
                planos.setPla_preco(rs.getFloat("pla_preco"));
            }
        } catch (Exception error) {
            throw error;
        }
        return planos;
    }
    
    public Planos getPlanosNome(String nome ) throws Exception {

        Planos planos = new Planos();

        String sql = "SELECT * FROM planos WHERE pla_nome=?";

        try {
            
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, nome);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                planos.setPla_iden(rs.getInt("pla_iden"));
                planos.setPla_acesso_simultaneo(rs.getInt("pla_acesso_simultaneo"));
                planos.setPla_nome(rs.getString("pla_nome"));
                planos.setPla_preco(rs.getFloat("pla_preco"));
            }
        } catch (Exception error) {
            throw error;
        }
        return planos;
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
