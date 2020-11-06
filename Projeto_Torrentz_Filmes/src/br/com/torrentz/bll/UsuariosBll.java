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

import br.com.torrentz.dal.UsuariosDal;
import br.com.torrentz.model.Usuarios;

import java.util.ArrayList;

/**
 *
 * @author Gustavo Gabriel
 */
public class UsuariosBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private UsuariosDal usuariosDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public UsuariosBll() throws Exception, ClassNotFoundException {
        usuariosDal = new UsuariosDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Usuarios usuario) throws Exception {
        try {
            if (usuario.getUsu_nome().length() < 3) {
                throw new RuntimeException("Nome do usuário inválido\nNo mínimo 3 caracteres!");
            }

            if (!usuario.getUsu_email().contains("@") && !usuario.getUsu_email().contains(".")) {
                throw new RuntimeException("No e-mail é necessário ter os seguintes caracteres [@] e [.] !");
            }

            if (usuario.getUsu_senha().length() < 6) {
                throw new RuntimeException("Senha deve possuir no mínimo 6 caracteres!");
            }

            usuariosDal.addUsuario(usuario);

        } catch (Exception error) {

            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void Remover(Usuarios usuarios) throws Exception {

        try {
            
            usuariosDal.deleteUsuario(usuarios.getUsu_iden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Usuarios usuario) throws Exception {
        try {
            if (usuario.getUsu_nome().length() < 3) {
                throw new RuntimeException("Nome do usuário inválido\nNo mínimo 3 caracteres!");
            }

            if (!usuario.getUsu_email().contains("@") && !usuario.getUsu_email().contains(".")) {
                throw new RuntimeException("No e-mail é necessário ter os seguintes caracteres [@] e [.] !");
            }

            if (usuario.getUsu_senha().length() < 6) {
                throw new RuntimeException("Senha deve possuir no mínimo 6 caracteres!");
            }

            usuariosDal.updateUsuario(usuario);

        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //

    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Usuarios> getConsulta() throws Exception{

        try {

            return usuariosDal.getAllUsuario();

        } catch (Exception error) {
            throw error;
        }
    }

    public Usuarios getConsultaPorId(int usu_iden) throws Exception {
        try {

            return usuariosDal.getUsuariosById(usu_iden);

        } catch (Exception error) {
            throw error;
        }
    }
    
     public Usuarios getUsuariosNome(String nome) throws Exception{
        try {
            return usuariosDal.getUsuariosNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
