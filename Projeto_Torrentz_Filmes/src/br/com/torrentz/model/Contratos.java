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

public class Contratos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int con_iden = 0;
    private String con_status = "";
    private String con_inicio = "";
    private String con_fim = "";
    private Usuarios con_usu_iden = null;
    private Planos con_pla_iden = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Contratos() {

    }
    
    public Contratos(int con_iden, String con_status, String con_inicio, String con_fim, Planos con_pla_iden, Usuarios com_usu_iden) {
        this.con_iden = con_iden;
        this.con_status = con_status;
        this.con_fim = con_fim;
        this.con_usu_iden = com_usu_iden;
        this.con_pla_iden = con_pla_iden;
        this.con_inicio = con_inicio;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getCon_iden() {
        return con_iden;
    }

    public String getCon_status() {
        return con_status;
    }

    public String getCon_inicio() {
        return con_inicio ;
    }

    public String getCon_fim() {
        return con_fim;
    }

    public Usuarios getCon_usu_iden() {
        return con_usu_iden;
    }

    public Planos getCon_pla_iden() {
        return con_pla_iden;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setCon_iden(int con_iden) {
        this.con_iden = con_iden;
    }

    public void setCon_status(String con_status) {
        this.con_status = con_status.trim().toUpperCase();
    }

    public void setCon_inicio(String con_inicio) {
        this.con_inicio = con_inicio;
    }

    public void setCon_fim(String con_fim) {
        this.con_fim = con_fim;
    }

    public void setCon_usu_iden(Usuarios con_usu_iden) {
        this.con_usu_iden = con_usu_iden;
    }
    
    public void setCon_pla_iden(Planos con_pla_iden) {
        this.con_pla_iden = con_pla_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //

  

 
}
