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
import br.com.torrentz.model.Cupons;
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
public class CuponsDal {

    private Connection conexao;

    public CuponsDal() throws Exception {
        conexao = Conexao.getConexao();
    }

    public void addCupons(Cupons cupons) throws Exception {

        String sql = "INSERT INTO cupons (cup_porcentagem, cup_data_geracao) values (?,?)";
        try{

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cupons.getCup_porcentagem());
            preparedStatement.setDate(2, cupons.getCup_data_geracao());
            preparedStatement.executeUpdate();

        } catch (Exception error) {
            if (error.getMessage().contains("cupon_porcentagem")) {
            throw new RuntimeException("Erro, cupom não pode ser menor que 0%, e maior que 60%");   
            }
            else throw new RuntimeException("Erro,"+error.getMessage());  
        }
    }

    public void deleteCupons(int cup_iden) throws Exception {

        String sql = "DELETE FROM cupons WHERE cup_iden =?";
        try {

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cup_iden);
            preparedStatement.executeUpdate();

        } catch (Exception error) {

            if (error.getMessage().contains("violates foreign key constraint")) {
                throw new RuntimeException("Cupon não pode ser deletado pois existe um usuário cadastrado com este cupon ");
            }
        }
    }

    public void updateCupons (Cupons cupon) throws Exception {

        String sql = "UPDATE cupons SET cup_porcentagem=?, cup_data_geracao=? WHERE cup_id=?";
        try{

            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cupon.getCup_porcentagem());
            preparedStatement.setDate(2, cupon.getCup_data_geracao());
            preparedStatement.setInt(3, cupon.getCup_iden());
            preparedStatement.executeUpdate();

            } catch (Exception error) {
             if (error.getMessage().contains("cupon_porcentagem")) {
            throw new RuntimeException("Erro, cupom não pode ser menor que 0%, e maior que 60%");   
            }
        }
    }

    public Cupons getCupomByID(int cup_iden) throws Exception {

        Cupons cupon = new Cupons();
        String sql = "SELECT * FROM cupons WHERE cup_iden =?";
        try {
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            preparedStatement.setInt(1, cup_iden);
            ResultSet rs = preparedStatement.executeQuery();
         while (rs.next()) {
                
                cupon.setCup_porcentagem(rs.getInt("cup_porcentagem"));
                cupon.setCup_data_geracao(rs.getDate("cup_data_geracao"));
                cupon.setCup_iden(rs.getInt("cup_iden"));  
                
            }
          } catch (Exception error) {
            throw error;
        }
        return cupon;   
     }

     public Cupons getLastCupons() throws Exception {
         
        Cupons ultimoCupom;
        ArrayList<Cupons> lista = new ArrayList<Cupons>();
        String sql = "SELECT * FROM cupons";
        try {

            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
         while (rs.next()) {

                Cupons cupon = new Cupons();
                cupon.setCup_porcentagem(rs.getInt("cup_porcentagem"));
                cupon.setCup_data_geracao(rs.getDate("cup_data_geracao"));
                cupon.setCup_iden(rs.getInt("cup_iden"));  
                lista.add(cupon);
            }
         int Ultimo = lista.size()-1;
         ultimoCupom = lista.get(Ultimo);
          } catch (Exception error) {
            throw error;
        }
        return ultimoCupom;   
     }

}

