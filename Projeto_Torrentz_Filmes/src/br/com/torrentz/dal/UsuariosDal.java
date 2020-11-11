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
import br.com.torrentz.bll.CuponsBll;
import br.com.torrentz.model.Cupons;
import br.com.torrentz.model.Usuarios;
import java.sql.Connection;
import br.com.torrentz.util.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class UsuariosDal {

     private Connection conexao;
     private Cupons cupon = null;
     private CuponsBll cupBll = new CuponsBll();


    public UsuariosDal() throws Exception{
        conexao = Conexao.getConexao();
    }
    
    public void addUsuario(Usuarios usuarios) throws Exception {

        String sql = "INSERT INTO usuarios(usu_nome, usu_cpf, usu_email, usu_senha, usu_cup_iden) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, usuarios.getUsu_nome());
            preparedStatement.setString(2, usuarios.getUsu_cpf());
            preparedStatement.setString(3, usuarios.getUsu_email());
            preparedStatement.setString(4, usuarios.getUsu_senha());
            preparedStatement.setInt(5, usuarios.getUsu_cup_iden().getCup_iden());
            preparedStatement.executeUpdate();

        } catch (Exception error) {
            if (error.getMessage().contains("usuario_repetido")) {
                throw new RuntimeException("Nome de usuário ja cadastrado em nosso banco de dados");
            }
             if (error.getMessage().contains("cpf_repetido")) {
                throw new RuntimeException("CPF do usuário ja cadastrado em nosso banco de dados!");
            }
              if (error.getMessage().contains("email_repetido")) {
                throw new RuntimeException("E-Mail do usuário ja cadastrado em nosso banco de dados!");
            }
             
        }
    }

    public void deleteUsuario(int usu_iden) throws Exception {

        String sql = "DELETE FROM usuarios WHERE usu_iden =?";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, usu_iden);
            preparedStatement.executeUpdate();

        } catch (Exception error) {
            if (error.getMessage().contains("visualizados_vis_usu_iden_fkey")) {
                throw new RuntimeException("Usuário relacionado com visualização de algum filme");
            }
             if (error.getMessage().contains("contratos_con_usu_iden_fkey")) {
                throw new RuntimeException("Não é possivel deletar usuário pois possui um contrato em seu nome");
            }   
        }
    }

    public void updateUsuario(Usuarios usuarios) throws Exception {

        String sql = "UPDATE usuarios SET usu_nome=?, usu_cpf=?, usu_email=?, usu_senha=?, usu_cup_iden=? WHERE usu_iden=?";

        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setString(1, usuarios.getUsu_nome());
            preparedStatement.setString(2, usuarios.getUsu_cpf());
            preparedStatement.setString(3, usuarios.getUsu_email());
            preparedStatement.setString(4, usuarios.getUsu_senha());
            preparedStatement.setInt(5, usuarios.getUsu_cup_iden().getCup_iden());
            preparedStatement.setInt(6, usuarios.getUsu_iden());
            preparedStatement.executeUpdate();

        } catch (Exception error) {
            if (error.getMessage().contains("duplicate key value violates unique constraint")) {
                throw new RuntimeException("Já existe um usuário com este dado cadastrado no sistema!");
            }
        }
    }

    public ArrayList<Usuarios> getAllUsuario() throws Exception {

        ArrayList<Usuarios> lista = new ArrayList<Usuarios>();
        String sql = "SELECT * FROM usuarios";
        try {

            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {

                int cup_id = rs.getInt("usu_cup_iden");
                cupon = cupBll.getConsultaId(cup_id);
                Usuarios usuario = new Usuarios();
                usuario.setUsu_iden(rs.getInt("usu_iden"));
                usuario.setUsu_nome(rs.getString("usu_nome"));
                usuario.setUsu_cpf(rs.getString("usu_cpf"));
                usuario.setUsu_email(rs.getString("usu_email"));
                usuario.setUsu_senha(rs.getString("usu_senha"));
                usuario.setUsu_cup_iden(cupon);
                lista.add(usuario);
            }
        } catch (Exception error) {
            throw error;
        }
        return lista;
    }
    
    public Usuarios getUsuariosById(int usu_iden) throws Exception {
        Usuarios usuario = new Usuarios();
        try {

          String sql = "SELECT * FROM usuarios WHERE usu_iden=?";
          PreparedStatement preparedStatement = conexao.prepareStatement(sql);
          preparedStatement.setInt(1, usu_iden);
          ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int cup_id = rs.getInt("usu_cup_iden");
                cupon = cupBll.getConsultaId(cup_id);
                usuario.setUsu_iden(rs.getInt("usu_iden"));
                usuario.setUsu_nome(rs.getString("usu_nome"));
                usuario.setUsu_cpf(rs.getString("usu_cpf"));
                usuario.setUsu_email(rs.getString("usu_email"));
                usuario.setUsu_senha(rs.getString("usu_senha"));
                usuario.setUsu_cup_iden(cupon);
            }
        } catch (Exception error) {
            throw error;
        }
        return usuario;
    }
    
    public Usuarios getUsuariosNome(String nome) throws Exception {
        Usuarios usuario = new Usuarios();
        
       
        try {
          String sql = ("SELECT * FROM usuarios WHERE usu_nome=?");
          PreparedStatement preparedStatement = conexao.prepareStatement(sql);
          preparedStatement.setString(1, nome);
          ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {     
                int idUsu = rs.getInt("usu_iden");
                usuario = getUsuariosById(idUsu);    
            }
        } catch (Exception error) {
            throw error;
        }
        return usuario;
    }
}
