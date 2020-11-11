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

public class Categorias {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int cat_iden = 0;
    private String cat_nome = "";
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Categorias() {

    }

    public Categorias(int cat_iden, String cat_nome) {
        this.cat_iden = cat_iden;
        this.cat_nome = cat_nome;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getCat_iden() {
        return cat_iden;
    }

    public String getCat_nome() {
        return cat_nome;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setCat_iden(int cat_iden) {
        this.cat_iden = cat_iden;
    }

    public void setCat_nome(String cat_nome) {
        
        this.cat_nome = cat_nome.trim().toUpperCase();
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
