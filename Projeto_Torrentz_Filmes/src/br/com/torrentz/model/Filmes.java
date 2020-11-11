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
public class Filmes {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int fil_iden = 0;
    private String fil_sinopse = "";
    private String fil_titulo = "";
    private int fil_ano = 0;
    private Categorias fil_cat_iden = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Filmes() {

    }

    public Filmes(int fil_iden, String fil_sinopse, String fil_titulo, int fil_ano, Categorias fil_cat_iden) {
        this.fil_iden = fil_iden;
        this.fil_sinopse = fil_sinopse;
        this.fil_titulo = fil_titulo;
        this.fil_ano = fil_ano;
        this.fil_cat_iden = fil_cat_iden;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getFil_iden() {
        return fil_iden;
    }

    public String getFil_sinopse() {
        return fil_sinopse;
    }

    public String getFil_titulo() {
        return fil_titulo;
    }

    public int getFil_ano() {
        return fil_ano;
    }

    public Categorias getFil_cat_iden() {
        return fil_cat_iden;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setFil_iden(int fil_iden) {
        this.fil_iden = fil_iden;
    }

    public void setFil_sinopse(String fil_sinopse) {
        this.fil_sinopse = fil_sinopse;
    }

    public void setFil_titulo(String fil_titulo) {
        this.fil_titulo = fil_titulo.trim().toUpperCase();
    }

    public void setFil_ano(int fil_ano) {
        this.fil_ano = fil_ano;
    }

    public void setFil_cat_iden(Categorias fil_cat_iden) {
        this.fil_cat_iden = fil_cat_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
