/*
 *  --------------------------------------------------------------------------------------------------->
 *  Licença    : MIT - Copyright 2019 Jhonathan, Gustavo e Miguel 
 *  Criado em  : 03/11/2020 15:44:59 
 *  Instituição: FACULDADE SENAI FATESG
 *  Curso      : Análise e Desenvolvimento de sistemas - Módulo 3 - 2020/2
 *  Disciplina : mbd - Modelagem de banco de dados
 *  Alunos     : Jhonathan dos Reis, Gustavo Gabriel e Miguel Neto
 *  Projeto    : PROJETO CAMADAS
 *  Exercício  : Torrentz Filmes
 *  ----------------------------------------------------------------------------------------------------
 *  Propósito do arquivo (Objetivo).
 *  ---------------------------------------------------------------------------------------------------| 
 */

package br.com.torrentz.model;
import java.sql.Date;
import java.util.Random;

/**
 *
 * @author Gustavo
 */
public class Cupons {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int cup_iden = 0;
    private int cup_porcentagem = 0;
    private Date cup_data_geracao; 

    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Cupons() {
        Random porcentagemAleatoria = new Random();
        this.cup_porcentagem = porcentagemAleatoria.nextInt(61) + 1 ; // +1 pra não sortear zero e 61 pos ele gera ate n-1
        this.cup_data_geracao = new Date(System.currentTimeMillis());
    }
    
    public Cupons(int cup_iden, int cup_porcentagem, Date cup_data_geracao) {
        Random porcentagemAleatoria = new Random();
        this.cup_iden = cup_iden;
        this.cup_porcentagem = porcentagemAleatoria.nextInt(61) + 1 ; // +1 pra não sortear zero e 61 pos ele gera ate n-1
        this.cup_data_geracao = new Date(System.currentTimeMillis());
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getCup_iden() {
        return cup_iden;
    }

    public int getCup_porcentagem() {
        return cup_porcentagem;
    }

    public Date getCup_data_geracao() {
        return cup_data_geracao;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setCup_iden(int cup_iden) {
        this.cup_iden = cup_iden;
    }

    public void setCup_porcentagem(int cup_porcentagem) {
        this.cup_porcentagem = cup_porcentagem;
    }
    
    public void setCup_data_geracao(Date cup_data_geracao) {
        this.cup_data_geracao = cup_data_geracao;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
    
}
