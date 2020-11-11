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

import br.com.torrentz.dal.FilmesDal;
import br.com.torrentz.model.Filmes;

import java.util.ArrayList;

/**
 *
 * @author JHONATHAN
 */
public class FilmesBll {

    //--- ATRIBUTOS ----------------------------------------------------------------------------------->
    //
    private FilmesDal filmesDal;
    //--- FIM ATRIBUTOS -------------------------------------------------------------------------------|
    //

    //--- CONSTRUTORES -------------------------------------------------------------------------------->
    //
    public FilmesBll() throws Exception, ClassNotFoundException {
        filmesDal = new FilmesDal();
    }
    //--- FIM CONSTRUTORES ----------------------------------------------------------------------------|
    //

    //--- CREATE -------------------------------------------------------------------------------------->
    //
    public void Adicionar(Filmes filmes) throws Exception {
        try {
            if (filmes.getFil_titulo().length() < 2) 
                throw new RuntimeException("Nome do filme inválido\nNo mínimo 2 caracteres!");
            
            if (filmes.getFil_titulo().length() > 50) 
                throw new RuntimeException("Nome do filme inválido\nMáximo de caracteres excedido!");
            
            if (filmes.getFil_ano() > 2020)
                throw new RuntimeException("Ano não pode ser maior que ano atual");
            
            if(filmes.getFil_ano() < 1895) 
            throw new RuntimeException("Ano não pode ser menor que o ano de criação do primeiro filme criado em 1895");

            filmesDal.addFilmes(filmes);
        } catch (Exception error) {
            
            throw error;
        }
    }
    //--- FIM CREATE ----------------------------------------------------------------------------------|
    //

    //--- DELETE -------------------------------------------------------------------------------------->
    //
    public void Remover(Filmes filmes) throws Exception {
        
        try {
            filmesDal.deleteFilmes(filmes.getFil_iden());
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM DELETE ----------------------------------------------------------------------------------|
    //

    //--- UPDATE -------------------------------------------------------------------------------------->
    //
    public void Alterar(Filmes filmes) throws Exception {
        try {

            if (filmes.getFil_titulo().length() < 2) 
                throw new RuntimeException("Nome do filme inválido\nNo mínimo 2 caracteres!");
            
            if (filmes.getFil_titulo().length() > 50)
                throw new RuntimeException("Nome do filme inválido\nMáximo de caracteres excedido!");
            
            if (filmes.getFil_ano() > 2020)
                throw new RuntimeException("Ano não pode ser maior que ano atual");
            
            if(filmes.getFil_ano() < 1895) 
            throw new RuntimeException("Ano não pode ser menor que o ano de criação do primeiro filme criado em 1895");
            
            filmesDal.updateFilmes(filmes);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM UPDATE ----------------------------------------------------------------------------------|
    //
        
    //--- READ ---------------------------------------------------------------------------------------->
    //
    public ArrayList<Filmes> getConsulta() throws Exception, ClassNotFoundException {
        
        try {
            return filmesDal.getAllFilmes();
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Filmes getConsultaPorId(int fil_iden) throws Exception, ClassNotFoundException {
        try {
            return filmesDal.getFilmesById(fil_iden);
        } catch (Exception error) {
            throw error;
        }
    }
    
    public Filmes getConsultaNome(String nome) throws Exception, ClassNotFoundException {
        try {
            return filmesDal.getFilmesNome(nome);
        } catch (Exception error) {
            throw error;
        }
    }
    //--- FIM READ ------------------------------------------------------------------------------------|
    //
}
