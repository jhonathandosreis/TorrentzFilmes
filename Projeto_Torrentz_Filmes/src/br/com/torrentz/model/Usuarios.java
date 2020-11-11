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
package br.com.torrentz.model;

/**
 *
 * @author JHONATHAN
 */

public class Usuarios {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int usu_iden = 0;
    private String usu_nome = "";
    private String usu_cpf = "";
    private String usu_email = "";
    private String usu_senha = "";
    private Cupons usu_cup_iden = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Usuarios() {
        
    }
    
    public Usuarios(int usu_iden, String usu_nome, String usu_cpf, String usu_email, String usu_senha, Cupons usu_cup_iden) {
        this.usu_iden = usu_iden;
        this.usu_nome = usu_nome;
        this.usu_cpf = usu_cpf;
        this.usu_email = usu_email;
        this.usu_senha = usu_senha;
        this.usu_cup_iden = usu_cup_iden;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getUsu_iden() {
        return usu_iden;
    }

    public String getUsu_nome() {
        return usu_nome;
    }

    public String getUsu_cpf() {
        return usu_cpf;
    }

    public String getUsu_email() {
        return usu_email;
    }

    public String getUsu_senha() {
        return usu_senha;
    }

    public Cupons getUsu_cup_iden() {
        return usu_cup_iden;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setUsu_iden(int usu_iden) {
        this.usu_iden = usu_iden;
    }

    public void setUsu_nome(String usu_nome) {
        this.usu_nome = usu_nome.trim().toUpperCase();
    }

    public void setUsu_cpf(String usu_cpf) {
        this.usu_cpf = usu_cpf;
    }

    public void setUsu_email(String usu_email) {
        this.usu_email = usu_email.trim().toUpperCase();
    }

    public void setUsu_senha(String usu_senha) {
        this.usu_senha = usu_senha;
    }
    
    public void setUsu_cup_iden(Cupons usu_cup_iden) {
        this.usu_cup_iden = usu_cup_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
