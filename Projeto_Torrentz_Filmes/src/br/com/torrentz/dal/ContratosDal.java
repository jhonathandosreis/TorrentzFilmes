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

import br.com.torrentz.model.Contratos;
import br.com.torrentz.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;

/**
 *
 * @author miguelneto
 */
public class ContratosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private Connection conexao;

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public ContratosDal() throws Exception {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addContratos(Contratos contratos) throws Exception {

        String sql = "INSERT INTO contratos(con_usu_iden, con_pla_iden  , con_inicio , con_fim , con_status ) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, contratos.getCon_usu_iden().getUsu_iden());
            preparedStatement.setInt(2, contratos.getCon_pla_iden().getPla_iden());
            preparedStatement.setString(3, contratos.getCon_inicio());
            preparedStatement.setString(4, contratos.getCon_fim());
            preparedStatement.setString(5, contratos.getCon_status());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível cadastrar este Contrato!");
            }
        }
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteContratos(int con_iden) throws Exception {

        String sql = "DELETE FROM contratos WHERE con_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, con_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("violates foreign key constraint")) {
                throw new RuntimeException("Não é possível deletar este Contrato!");
            }
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateContratos(Contratos contrato) throws Exception {

        try {
            PreparedStatement preparedStatement = conexao
                    .prepareStatement("UPDATE contratos SET con_usu_iden=? , con_pla_iden=? , con_inicio=? , con_fim=? , con_status=?  WHERE  con_iden=?");

            preparedStatement.setInt(1, contrato.getCon_usu_iden().getUsu_iden());
            preparedStatement.setInt(2, contrato.getCon_pla_iden().getPla_iden());
            preparedStatement.setString(3, contrato.getCon_inicio());
            preparedStatement.setString(4, contrato.getCon_fim());
            preparedStatement.setString(5, contrato.getCon_status());
            preparedStatement.setInt(6, contrato.getCon_iden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar este Contrato!!");
            }
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Contratos> getAllContratos() throws Exception, ClassNotFoundException {

        ArrayList<Contratos> lista = new ArrayList<Contratos>();

        String sql = "SELECT * FROM contratos";

        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                Contratos contrato1 = new Contratos();

                UsuariosDal usu = new UsuariosDal();
                contrato1.setCon_usu_iden(usu.getUsuariosById(rs.getInt("con_usu_iden")));

                PlanosDal pla = new PlanosDal();
                contrato1.setCon_pla_iden(pla.getPlanosById(rs.getInt("con_pla_iden")));

                contrato1.setCon_inicio(rs.getString("con_inicio"));
                contrato1.setCon_fim(rs.getString("con_fim"));
                contrato1.setCon_status(rs.getString("con_status"));
                contrato1.setCon_iden(rs.getInt("con_iden"));

                lista.add(contrato1);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Contratos getContratosById(int id) throws Exception, ClassNotFoundException {

        Contratos contrato2 = new Contratos();
        String sql = "SELECT * FROM contratos WHERE con_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                UsuariosDal usu = new UsuariosDal();
                contrato2.setCon_usu_iden(usu.getUsuariosById(rs.getInt("con_usu_iden")));

                PlanosDal pla = new PlanosDal();
                contrato2.setCon_pla_iden(pla.getPlanosById(rs.getInt("con_pla_iden")));

                contrato2.setCon_inicio(rs.getString("con_inicio"));
                contrato2.setCon_fim(rs.getString("con_fim"));
                contrato2.setCon_status(rs.getString("con_status"));
                contrato2.setCon_iden(rs.getInt("con_iden"));

            }
        } catch (Exception error) {
            throw error;
        }
        return contrato2;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
