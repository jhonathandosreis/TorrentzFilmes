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

import java.sql.Date;

/**
 *
 * @author JHONATHAN
 */
public class Visualizados {
    
    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private int vis_iden = 0;
    private boolean vis_completo = false;
    private String vis_data_geracao = "";
    private Usuarios vis_usu_iden = null;
    private Filmes vis_fil_iden = null;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public Visualizados() {
        
    }
    
    public Visualizados(int vis_iden, boolean vis_completo, String vis_data_geracao, Usuarios vis_usu_iden, Filmes vis_fil_iden) {
        this.vis_iden = vis_iden;
        this.vis_completo =vis_completo;
        this.vis_data_geracao =  vis_data_geracao;
        this.vis_usu_iden = vis_usu_iden;
        this.vis_fil_iden = vis_fil_iden;
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- GET ----------------------------------------------------------------------------------------->
    //
    public int getVis_iden() {
        return vis_iden;
    }

    public boolean isVis_completo() {
        return vis_completo;
    }

    public String getVis_data_geracao() {
        return vis_data_geracao;
    }

    public Usuarios getVis_usu_iden() {
        return vis_usu_iden;
    }

    public Filmes getVis_fil_iden() {
        return vis_fil_iden;
    }
    //--- FIM GET -------------------------------------------------------------------------------------|
    //

    //--- SET ----------------------------------------------------------------------------------------->
    //
    public void setVis_iden(int vis_iden) {
        this.vis_iden = vis_iden;
    }

    public void setVis_completo(boolean vis_completo) {
        this.vis_completo = vis_completo;
    }

    public void setVis_data_geracao(String vis_data_geracao) {
        this.vis_data_geracao = vis_data_geracao;
    }

    public void setVis_usu_iden(Usuarios vis_usu_iden) {
        this.vis_usu_iden = vis_usu_iden;
    }
    
    public void setVis_fil_iden(Filmes vis_fil_iden) {
        this.vis_fil_iden = vis_fil_iden;
    }
    //--- FIM SET -------------------------------------------------------------------------------------|
    //
}
