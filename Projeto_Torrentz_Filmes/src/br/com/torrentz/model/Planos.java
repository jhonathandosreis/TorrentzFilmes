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
public class Planos {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //  
    private int pla_iden = 0;
    private int pla_acesso_simultaneo = 0;
    private String pla_nome = "";
    private float pla_preco = 0;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Planos() {
        
    }
    
    public Planos(int pla_iden, int pla_acesso_simultaneo, String pla_nome, float pla_preco) {
        this.pla_iden = pla_iden;
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
        this.pla_nome = pla_nome;
        this.pla_preco = pla_preco;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getPla_iden() {
        return pla_iden;
    }

    public int getPla_acesso_simultaneo() {
        return pla_acesso_simultaneo;
    }

    public String getPla_nome() {
        return pla_nome;
    }

    public float getPla_preco() {
        return pla_preco;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setPla_iden(int pla_iden) {
        this.pla_iden = pla_iden;
    }

    public void setPla_acesso_simultaneo(int pla_acesso_simultaneo){   
        this.pla_acesso_simultaneo = pla_acesso_simultaneo;
    }

    public void setPla_nome(String pla_nome) {
        this.pla_nome = pla_nome.trim().toUpperCase();
    }
    
    public void setPla_preco(float pla_preco){
        this.pla_preco = pla_preco;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
