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
import br.com.torrentz.dal.CuponsDal;
import br.com.torrentz.model.Cupons;

/**
 *
 * @author Gustavo Gabriel
 */
public class CuponsBll {

    private CuponsDal cuponDal;

    public CuponsBll() throws Exception, ClassNotFoundException {
        cuponDal = new CuponsDal();
    }

    public void Adicionar(Cupons cupon) throws Exception {

        try {
            cuponDal.addCupons(cupon);
        } catch (Exception error) {
            throw error;
        }
    }

    public void Alterar(Cupons cupon) throws Exception {

        try {
            cuponDal.updateCupons(cupon);
        } catch (Exception error) {
            throw error;
        }
    }

    public void Remover(Cupons cupon) throws Exception {
        try {
            cuponDal.deleteCupons(cupon.getCup_iden());
        } catch (Exception error) {
            throw error;
        }
    }

    public Cupons getConsultaId(int cup_iden) throws Exception {
        try {
            return cuponDal.getCupomByID(cup_iden);
        } catch (Exception error) {
            throw error;
        }
    }

    public Cupons getLastCupom() throws Exception {
        try {
            return cuponDal.getLastCupons();
        } catch (Exception error) {
            throw error;
        }
    }
}

