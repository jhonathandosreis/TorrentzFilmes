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

import br.com.torrentz.model.Visualizados;
import br.com.torrentz.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author miguelneto
 */
public class VisualizadosDal {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private java.sql.Connection conexao;


    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //
    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public VisualizadosDal() throws Exception, ClassNotFoundException {
        conexao = Conexao.getConexao();
    }

    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //
    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void addVisualizados(Visualizados visualizado) throws Exception {

        String sql = "INSERT INTO visualizados (vis_completo , vis_data_visualizacao , vis_usu_iden, vis_fil_iden ) VALUES(?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, visualizado.isVis_completo());
            preparedStatement.setString(2, visualizado.getVis_data_geracao());
            preparedStatement.setInt(3, visualizado.getVis_usu_iden().getUsu_iden());
            preparedStatement.setInt(4, visualizado.getVis_fil_iden().getFil_iden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível cadastrar a Visualização!");
            }
        }
    }

    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //
    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void deleteVisualizados(int vis_iden) throws Exception {

        String sql = "DELETE FROM visualizados WHERE vis_iden =?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, vis_iden);
            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("violates foreign key constraint")) {
                throw new RuntimeException("Não é possível deletar esta Vizualização!");
            }
        }
    }

    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //
    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void updateVisualizados(Visualizados visualizado) throws Exception {

        String sql = "UPDATE visualizados SET vis_completo=? , vis_data_visualizacao =? , vis_usu_iden=? , vis_fil_iden=?  WHERE vis_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setBoolean(1, visualizado.isVis_completo());
            preparedStatement.setString(2, visualizado.getVis_data_geracao());
            preparedStatement.setInt(3, visualizado.getVis_usu_iden().getUsu_iden());
            preparedStatement.setInt(4, visualizado.getVis_fil_iden().getFil_iden());
            preparedStatement.setInt(5, visualizado.getVis_iden());

            preparedStatement.executeUpdate();
        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Não é possível alterar esta Visualização!!");
            }
        }
    }

    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Visualizados> getAllVisualizados() throws Exception, ClassNotFoundException {

        ArrayList<Visualizados> lista = new ArrayList<Visualizados>();
        String sql = "SELECT * FROM visualizados";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                
                Visualizados visualizado = new Visualizados();
                
                visualizado.setVis_iden(rs.getInt("vis_iden"));
                visualizado.setVis_completo(rs.getBoolean("vis_completo"));
                visualizado.setVis_data_geracao(rs.getString("vis_data_visualizacao"));
                
                UsuariosDal usu = new UsuariosDal();
                visualizado.setVis_usu_iden(usu.getUsuariosById(rs.getInt("vis_usu_iden")));

                FilmesDal fil = new FilmesDal();
                visualizado.setVis_fil_iden(fil.getFilmesById(rs.getInt("vis_fil_iden")));
                

                lista.add(visualizado);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }

    public Visualizados getVisualizadosById(int vis_iden) throws Exception, ClassNotFoundException {

        Visualizados visualizado = new Visualizados();
        
        String sql = "SELECT * FROM visualizados WHERE vis_iden=?";

        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, vis_iden);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {

                visualizado.setVis_iden(rs.getInt("vis_iden"));
                visualizado.setVis_completo(rs.getBoolean("vis_completo"));
                visualizado.setVis_data_geracao(rs.getString("vis_data_visualizacao"));
                
                UsuariosDal usu = new UsuariosDal();
                visualizado.setVis_usu_iden(usu.getUsuariosById(rs.getInt("vis_usu_iden")));

                FilmesDal fil = new FilmesDal();
                visualizado.setVis_fil_iden(fil.getFilmesById(rs.getInt("vis_fil_iden")));
            }
        } catch (Exception error) {
            throw error;
        }
        return visualizado;
    }

    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}