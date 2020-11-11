/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.torrentz.app;

import br.com.torrentz.bll.CategoriaBll;
import br.com.torrentz.bll.ContratosBll;
import br.com.torrentz.bll.CuponsBll;
import br.com.torrentz.bll.FilmesBll;
import br.com.torrentz.bll.PlanosBll;
import br.com.torrentz.bll.UsuariosBll;
import br.com.torrentz.bll.VisualizadosBll;
import br.com.torrentz.model.Categorias;
import br.com.torrentz.model.Contratos;
import br.com.torrentz.model.Cupons;
import br.com.torrentz.model.Filmes;
import br.com.torrentz.model.Planos;
import br.com.torrentz.model.Usuarios;
import br.com.torrentz.model.Visualizados;
import br.com.torrentz.util.Valida;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gustavo, Miguel, Jonathan
 */
public class TorrentzFilmes_Tela extends javax.swing.JFrame {

//--- BLL´S-------------------------------------------------------------------------------------------->
    private VisualizadosBll visualizadoBll = null;
    private ContratosBll contratosBll = null;
    private CategoriaBll categoriaBll = null;
    private PlanosBll planosBll = null;
    private FilmesBll filmesBll = null;
    private CuponsBll cupomBll = null;
    private UsuariosBll usuariosBll = null;

//--- CLASSES ----------------------------------------------------------------------------------------->
    private Contratos contrato = null;
    private Categorias categoria = null;
    private Planos planos = null;
    private Filmes filmes = null;
    private Usuarios usuario = null;
    private Cupons cupom = null;
    private Visualizados visualizado = null;

    /**
     * Creates new form TELA02
     */
    public TorrentzFilmes_Tela() {
        initComponents();
        try {

//--- INSTANCIAS BLL----------------------------------------------------------------------------------->        
            visualizadoBll = new VisualizadosBll();
            contratosBll = new ContratosBll();
            categoriaBll = new CategoriaBll();
            planosBll = new PlanosBll();
            filmesBll = new FilmesBll();
            usuariosBll = new UsuariosBll();
            cupomBll = new CuponsBll();

//--- INSTANCIAS CLASSES------------------------------------------------------------------------------->    
            visualizado = new Visualizados();
            contrato = new Contratos();
            categoria = new Categorias();
            planos = new Planos();
            filmes = new Filmes();
            usuario = new Usuarios();
            cupom = new Cupons();

//--- PREENCHER GIRD´S--------------------------------------------------------------------------------->    
            preencherGridContratos();
            preencherGridUsuarios();
            preencherGridCategoria();
            preencherGridPlanos();
            preencherGridFilmes();
            preencherGridVisualizados();

//--- PREENCHER COMBO´S-------------------------------------------------------------------------------->    
            preencherComboboxUsuario();
            preencherComboboxPlano();
            preencherComboboxCategoria();
            preencherComboboxFilmes();

//--- PREENCHER CAMPO´S-------------------------------------------------------------------------------->    
            preencherData();
            preencherDataAtualVisualizados();
            EnabledContratos();
            EnabledDataVisualizados();
            jComboBoxStatusContrato.setEnabled(false);

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
        this.setLocationRelativeTo(null);
    }

//--- CATEGORIA --------------------------------------------------------------------------------------->
    public void preencherGridCategoria() {

        try {
            DefaultTableModel tableCategoria = (DefaultTableModel) jTableCategoria.getModel();
            tableCategoria.setRowCount(0);

            Object[] linha = new Object[2];

            ArrayList<Categorias> categorias = new CategoriaBll().getConsulta();

            for (Categorias categoria1 : categorias) {
                linha[0] = categoria1.getCat_iden();
                linha[1] = categoria1.getCat_nome();
                tableCategoria.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherComboboxCategoria() throws Exception {
        ArrayList<Categorias> lista = categoriaBll.getConsulta();
        jComboBoxCategoriaFilmes.removeAllItems();
        jComboBoxCategoriaFilmes.addItem("<SELECIONE>");

        for (Categorias categorias : lista) {
            jComboBoxCategoriaFilmes.addItem(categorias.getCat_nome());
        }
    }

    public void preencherFormularioCategorias() {
        int id = Integer.parseInt(jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 0).toString());
        String nome = jTableCategoria.getValueAt(jTableCategoria.getSelectedRow(), 1).toString();

        jTextFieldNome.setText(nome);
        jTextFieldId.setText(id + "");
    }

    public void validarFormularioCategorias() {
        Valida.campoVazio(jTextFieldNome.getText(), "Digite o nome!");
        Valida.notSpecialCharacters(jTextFieldNome.getText(), "Campo não permite caracteres especiais!");
    }

//--- PLANOS ------------------------------------------------------------------------------------------>   
    public void preencherGridPlanos() {

        try {

            DefaultTableModel tablePlanos = (DefaultTableModel) jTablePlanos.getModel();
            tablePlanos.setRowCount(0);

            Object[] linha = new Object[4];

            ArrayList<Planos> planoss = new PlanosBll().getConsulta();

            for (Planos planos1 : planoss) {
                linha[0] = planos1.getPla_iden();
                linha[1] = planos1.getPla_acesso_simultaneo();
                linha[2] = planos1.getPla_nome();
                linha[3] = planos1.getPla_preco();
                tablePlanos.addRow(linha);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherComboboxPlano() throws Exception {
        ArrayList<Planos> lista = planosBll.getConsulta();
        jComboBoxPlanos.removeAllItems();
        jComboBoxPlanos.addItem("<SELECIONE>");

        for (Planos pla : lista) {
            jComboBoxPlanos.addItem(pla.getPla_nome());
        }
    }

    public void limparCamposPlanos() {
        jTextFieldAcessos.setText("");
        jTextFieldPlanosPrecos.setText("");
        jTextFieldPlanosNome.setText("");
        jTextFieldPlanosID.setText("");
    }

    public void preencherFormularioPlanos() {

        int id = Integer.parseInt(jTablePlanos.getValueAt(jTablePlanos.getSelectedRow(), 0).toString());
        int acessoSimultaneo = Integer.parseInt(jTablePlanos.getValueAt(jTablePlanos.getSelectedRow(), 1).toString());
        String nome = jTablePlanos.getValueAt(jTablePlanos.getSelectedRow(), 2).toString();
        float preco = Float.parseFloat(jTablePlanos.getValueAt(jTablePlanos.getSelectedRow(), 3).toString());

        jTextFieldAcessos.setText(acessoSimultaneo + "");
        jTextFieldPlanosNome.setText(nome);
        jTextFieldPlanosPrecos.setText(preco + "");
        jTextFieldPlanosID.setText(id + "");
    }

    public void ValidaFormularioPlanos() throws Exception {

        Valida.campoVazio(jTextFieldAcessos.getText(), "Digite a quantidade de acessos!");
        Valida.campoVazio(jTextFieldPlanosNome.getText(), "Digite o nome!");
        Valida.campoVazio(jTextFieldPlanosPrecos.getText(), "Digite o preço!");
        Valida.numberInteger(jTextFieldAcessos.getText(), "Somente números!");
        Valida.notNumber(jTextFieldPlanosNome.getText(), "Somente letras!");
        Valida.numberFloat(jTextFieldPlanosPrecos.getText(), "Somente números!");

    }

//--- FILMES ------------------------------------------------------------------------------------------>    
    public void preencherGridFilmes() {

        try {
            DefaultTableModel tableFilmes = (DefaultTableModel) jTableFilmes.getModel();
            tableFilmes.setRowCount(0);

            Object[] linha = new Object[5];

            ArrayList<Filmes> filmes = new FilmesBll().getConsulta();

            for (Filmes fil : filmes) {
                linha[0] = fil.getFil_iden();
                linha[1] = fil.getFil_titulo();
                linha[2] = fil.getFil_ano();
                linha[3] = fil.getFil_sinopse();
                linha[4] = fil.getFil_cat_iden().getCat_nome();
                tableFilmes.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO NA GRID FILMES!!!", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioFilmes() throws Exception, ClassNotFoundException {

        int id = Integer.parseInt(jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 0).toString());
        String titulo = jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 1).toString();
        int ano = Integer.parseInt(jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 2).toString());
        String sinopse = jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 3).toString();
        String Catego = jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 4).toString();

        jComboBoxCategoriaFilmes.setSelectedItem(Catego);
        jTextFieldFilmesID.setText(id + "");
        jTextFieldTituloFilmes.setText(titulo);
        jTextFieldAnoFilmes.setText(ano + "");
        jTextAreaSinopseFilmes.setText(sinopse);
    }

    public void preencherComboboxFilmes() throws Exception, ClassNotFoundException {
        ArrayList<Filmes> lista = filmesBll.getConsulta();
        jComboBoxVisFilmes.removeAllItems();
        jComboBoxVisFilmes.addItem("<SELECIONE>");

        for (Filmes filmes : lista) {
            jComboBoxVisFilmes.addItem(filmes.getFil_titulo());
        }
    }

    public void validaFormularioFilmes() {

        Valida.campoVazio(jTextFieldTituloFilmes.getText(), "Digite o titulo do filme!");
        Valida.campoVazio(jTextFieldAnoFilmes.getText(), "Digite o ano do filme!");
        Valida.campoVazio(jTextAreaSinopseFilmes.getText(), "Preencha a sinopse do filme!");
        Valida.numberInteger(jTextFieldAnoFilmes.getText(), "Somente números!");

    }

    public void limparCamposFilmes() {
        jTextAreaSinopseFilmes.setText("");
        jTextFieldTituloFilmes.setText("");
        jTextFieldAnoFilmes.setText("");
        jTextFieldFilmesID.setText("");
        jComboBoxCategoriaFilmes.setSelectedIndex(0);
    }

//--- CONTRATOS --------------------------------------------------------------------------------------->  
    public void preencherGridContratos() {

        try {

            DefaultTableModel tableContratos = (DefaultTableModel) jTableContratos.getModel();
            tableContratos.setRowCount(0);

            Object[] linha = new Object[6];

            ArrayList<Contratos> cont = new ContratosBll().getConsulta();

            for (Contratos con : cont) {

                linha[0] = con.getCon_iden();
                linha[1] = "" + con.getCon_usu_iden().getUsu_nome();
                linha[2] = "" + con.getCon_pla_iden().getPla_nome();
                linha[3] = "" + con.getCon_inicio();
                linha[4] = "" + con.getCon_fim();
                linha[5] = con.getCon_status();

                tableContratos.addRow(linha);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO NA GRID CONTRATOS!!!", "", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void preencherFormularioContratos() throws Exception, ClassNotFoundException {

        int id = Integer.parseInt(jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 0).toString());
        String idUsuario = contratosBll.getConsultaPorId(id).getCon_usu_iden().getUsu_nome();
        String idPlano = contratosBll.getConsultaPorId(id).getCon_pla_iden().getPla_nome();

        String dataInicio = jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 3).toString();
        String dataFim = jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 4).toString();

        String status = jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 5).toString();

        jComboBoxUsuarios.setSelectedItem(usuariosBll.getConsultaPorId(id).getUsu_nome());
        jComboBoxPlanos.setSelectedItem(planosBll.getConsultaPorId(id).getPla_nome());

        jTextFieldContratosID.setText(id + "");
        jComboBoxUsuarios.setSelectedItem(idUsuario);
        jComboBoxPlanos.setSelectedItem(idPlano);

        jTextFieldDataInicio.setText(dataInicio);
        jTextFieldDataFim.setText(dataFim);

       
        jComboBoxStatusContrato.setSelectedItem(status);

    }

    public void validaFormularioContratos() throws Exception {

//        ArrayList<Usuarios> lista = usuariosBll.getConsulta();
//        for (Usuarios uso2 : lista) {
//            if (uso2.getUsu_nome().equals(uso2.getUsu_nome())) {
//                throw new RuntimeException("Usuário já existente!");
//            }
//        }
        Valida.campoVazio(jTextFieldDataInicio.getText(), "Digite a Data de Início do Contrato!");
        Valida.campoVazio(jTextFieldDataFim.getText(), "Digite a Data do Término do Contrato!");
       

    }

    public void limparCamposContratos() {

          jComboBoxStatusContrato.setSelectedIndex(0);
          jComboBoxUsuarios.setSelectedIndex(0);
          jComboBoxPlanos.setSelectedIndex(0);
          jComboBoxStatusContrato.setEnabled(false);
          jComboBoxUsuarios.setEnabled(true);
//        jTextFieldStatus.setText("");
//        jTextFieldDataInicio.setText("");
//        jTextFieldDataFim.setText("");
    }

//--- USUÁRIOS ---------------------------------------------------------------------------------------->  
    public void preencherGridUsuarios() {

        try {
            DefaultTableModel TableUSUARIOS = (DefaultTableModel) jTable_usuarios.getModel();
            TableUSUARIOS.setRowCount(0);

            Object[] linha = new Object[5];

            ArrayList<Usuarios> usuarios = new UsuariosBll().getConsulta();

            for (Usuarios usu : usuarios) {
                linha[0] = usu.getUsu_iden();
                linha[1] = usu.getUsu_nome();
                linha[2] = usu.getUsu_cpf();
                linha[3] = usu.getUsu_email();
                linha[4] = "" + usu.getUsu_cup_iden().getCup_porcentagem() + " % ";
                TableUSUARIOS.addRow(linha);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO NA GRID USUÁRIOS!!!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void preencherFormularioUsuarios() throws Exception, ClassNotFoundException {

        int id = Integer.parseInt(jTable_usuarios.getValueAt(jTable_usuarios.getSelectedRow(), 0).toString());
        String nome = jTable_usuarios.getValueAt(jTable_usuarios.getSelectedRow(), 1).toString();
        String cpf = jTable_usuarios.getValueAt(jTable_usuarios.getSelectedRow(), 2).toString();
        String email = jTable_usuarios.getValueAt(jTable_usuarios.getSelectedRow(), 3).toString();
        usuario = usuariosBll.getConsultaPorId(id);

        jTextField_id_usu.setText(id + "");
        jTextField_Nome_USU.setText(nome);
        jTextField_Cpf_USU.setText(cpf);
        jTextField_Email_USU.setText(email);
        jPasswordField_Senha_USU.setText(usuario.getUsu_senha());
        jPasswordField_ConfirmSenha_USU.setText(usuario.getUsu_senha());

    }

    public void preencherComboboxUsuario() throws Exception {
        
       
        jComboBoxUsuarios.removeAllItems();
        jComboBoxVISusuario.removeAllItems();
        jComboBoxUsuarios.addItem("<SELECIONE>");
        jComboBoxVISusuario.addItem("<SELECIONE>");
        
        ArrayList<Usuarios> lista = usuariosBll.getConsulta();
         ArrayList<Contratos> listac = contratosBll.getConsulta();
           
            for (Contratos contrato : listac) {
                if (contrato.getCon_status().equals("ATIVO")) {
                    jComboBoxVISusuario.addItem(contrato.getCon_usu_iden().getUsu_nome());
                }
            }
        for (Usuarios uso : lista) {          
            jComboBoxUsuarios.addItem(uso.getUsu_nome());            
        }      
    }
    
    public void validaFormularioUsuarios() {

        Valida.campoVazio(jTextField_Nome_USU.getText(), "Digite o nome do Usuário!");
        Valida.campoVazio(jTextField_Cpf_USU.getText(), "Digite o cpf do Usuário!");
        Valida.campoVazio(jTextField_Email_USU.getText(), "Preencha o e-mail do Usuário!");

        char[] senha = jPasswordField_Senha_USU.getPassword();
        char[] senha2 = jPasswordField_ConfirmSenha_USU.getPassword();
        if (senha.length == 0) {
            throw new RuntimeException("Informe uma senha");
        }
        if (senha2.length == 0) {
            throw new RuntimeException("Confirme a senha");
        }

        Valida.notNumber(jTextField_Nome_USU.getText(), "Nome do usuário de conter ssomente letras!");
        Valida.numberInteger(jTextField_Cpf_USU.getText(), "Cpf deve conter somente números!");
        Valida.notSpecialCharacters(jTextField_Nome_USU.getText(), "Nome de usuário não pode conter caracteres especiais");
        Valida.notSpecialCharacters(jTextField_Cpf_USU.getText(), "Cpf do usuário não pode conter caracteres especiais");

    }

    public void limparCamposUsuarios() {
        jTextField_Nome_USU.setText("");
        jTextField_Cpf_USU.setText("");
        jTextField_Email_USU.setText("");
        jPasswordField_Senha_USU.setText("");
        jPasswordField_ConfirmSenha_USU.setText("");
        jTextField_id_usu.setText("");
    }

    public void ValidaCombo(String combo) throws Exception {
        if (combo.equals("<SELECIONE>")) {
            throw new Exception("Opção <SELECIONE> não é valida selecione uma das outras opções");
        }
    }

  

    public void EnabledContratos() {

        
        jTextFieldDataInicio.setEnabled(false);
        jTextFieldDataFim.setEnabled(false);

    }

    public void EnabledDataVisualizados() {

        jTextFieldDataAtualVisualizados.setEnabled(false);

    }

    public void preencherData() {

        DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd / MM / YYYY");
        LocalDate Inicio = LocalDate.now();
        jTextFieldDataInicio.setText(formate.format(Inicio));
        LocalDate Fim = Inicio.plusYears(1);
        jTextFieldDataFim.setText(formate.format(Fim));

    }

    public void preencherDataAtualVisualizados() {

        DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd / MM / YYYY");
        LocalDate Inicio = LocalDate.now();
        jTextFieldDataAtualVisualizados.setText("     Data Atual : " + formate.format(Inicio));

    }

    public boolean ValidaSenha(String senha, String confirma) {
        if (senha.equals(confirma)) {
            return true;
        }
        return false;
    }

    public void limparCampos() {
        jTextFieldId.setText("");
        jTextFieldNome.setText("");
    }

    public void preencherGridVisualizados() {

        try {
            DefaultTableModel tablevisu = (DefaultTableModel) jTable_Visualizados.getModel();
            tablevisu.setRowCount(0);

            Object[] linha = new Object[5];

            ArrayList<Visualizados> visu = new VisualizadosBll().getConsulta();

            for (Visualizados vi : visu) {
                linha[0] = vi.getVis_iden();
                linha[1] = vi.getVis_fil_iden().getFil_titulo();
                linha[2] = vi.getVis_usu_iden().getUsu_nome();
                if (vi.isVis_completo() == true) {
                    linha[3] = "SIM";
                } else {
                    linha[3] = "NÃO";
                }
                linha[4] = vi.getVis_data_geracao();
                tablevisu.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Grid visualizados", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limparVisualizados() {
        jComboBoxVISusuario.setSelectedIndex(0);
        jComboBoxVisFilmes.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify
     * this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Categorias = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextFieldId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCategoria = new javax.swing.JTable();
        jButtonAdicionarCategoria = new javax.swing.JButton();
        jButtonAlterarCategoria = new javax.swing.JButton();
        jButtonRemoverCategoria = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldPlanosID = new javax.swing.JTextField();
        jTextFieldAcessos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButtonAlterarPlano = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPlanosNome = new javax.swing.JTextField();
        jTextFieldPlanosPrecos = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePlanos = new javax.swing.JTable();
        jButtonAdicionarPlanos = new javax.swing.JButton();
        jButtonRemoverPlanos = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableFilmes = new javax.swing.JTable();
        jButtonAdicionarFilmes = new javax.swing.JButton();
        jButtonAlterarFilmes = new javax.swing.JButton();
        jButtonRemoverFilmes = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldTituloFilmes = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldAnoFilmes = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxCategoriaFilmes = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaSinopseFilmes = new javax.swing.JTextArea();
        jTextFieldFilmesID = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton_adicionar_usuarios = new javax.swing.JButton();
        jButton_alterar_usuario = new javax.swing.JButton();
        jButton_remover_usuario = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField_Senha_USU = new javax.swing.JPasswordField();
        jPasswordField_ConfirmSenha_USU = new javax.swing.JPasswordField();
        jTextField_Email_USU = new javax.swing.JTextField();
        jTextField_Cpf_USU = new javax.swing.JTextField();
        jTextField_Nome_USU = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField_id_usu = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_usuarios = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jComboBoxUsuarios = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldContratosID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxPlanos = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldDataFim = new javax.swing.JTextField();
        jTextFieldDataInicio = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTableContratos = new javax.swing.JTable();
        jButtonAlterarContrato = new javax.swing.JButton();
        jButtonAtivarContrato = new javax.swing.JButton();
        jComboBoxStatusContrato = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jButton_LimparCampoContrato = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBoxVISusuario = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jComboBoxVisFilmes = new javax.swing.JComboBox<>();
        jButtonVisualizacaoCompleta = new javax.swing.JButton();
        jTextFieldDataAtualVisualizados = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable_Visualizados = new javax.swing.JTable();
        jButton_deleteVisualizados = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("              Torrentz FIlmes");

        jTextFieldId.setEditable(false);

        jTableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCategoria);

        jButtonAdicionarCategoria.setText("ADICIONAR");
        jButtonAdicionarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarCategoriaActionPerformed(evt);
            }
        });

        jButtonAlterarCategoria.setText("ALTERAR");
        jButtonAlterarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarCategoriaActionPerformed(evt);
            }
        });

        jButtonRemoverCategoria.setText("REMOVER");
        jButtonRemoverCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverCategoriaActionPerformed(evt);
            }
        });

        jLabel1.setText("Categoria:");

        jLabel25.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonAdicionarCategoria)
                                .addGap(38, 38, 38)
                                .addComponent(jButtonAlterarCategoria)
                                .addGap(31, 31, 31)
                                .addComponent(jButtonRemoverCategoria)
                                .addGap(53, 53, 53))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldNome)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addGap(52, 52, 52))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButtonAdicionarCategoria)
                                    .addComponent(jButtonAlterarCategoria)
                                    .addComponent(jButtonRemoverCategoria))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(341, Short.MAX_VALUE))
        );

        Categorias.addTab("Categorias", jPanel1);

        jTextFieldPlanosID.setEditable(false);

        jLabel2.setText("Acessos Simultaneo:");

        jButtonAlterarPlano.setText("ALTERAR");
        jButtonAlterarPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarPlanoActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        jLabel4.setText("Preço:");

        jTablePlanos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Acessos Silmultaneo", "Nome", "Preço"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTablePlanos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePlanosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTablePlanos);

        jButtonAdicionarPlanos.setText("ADICIONAR");
        jButtonAdicionarPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarPlanosActionPerformed(evt);
            }
        });

        jButtonRemoverPlanos.setText("REMOVER");
        jButtonRemoverPlanos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverPlanosActionPerformed(evt);
            }
        });

        jLabel24.setText("ID");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldPlanosNome)
                            .addComponent(jTextFieldPlanosPrecos)
                            .addComponent(jTextFieldAcessos, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jButtonAdicionarPlanos)
                                .addGap(52, 52, 52)
                                .addComponent(jButtonAlterarPlano)
                                .addGap(60, 60, 60)
                                .addComponent(jButtonRemoverPlanos)
                                .addGap(0, 44, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldPlanosID, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldPlanosID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAcessos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextFieldPlanosNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextFieldPlanosPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAdicionarPlanos)
                            .addComponent(jButtonRemoverPlanos)
                            .addComponent(jButtonAlterarPlano))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(355, Short.MAX_VALUE))
        );

        Categorias.addTab("Planos", jPanel2);

        jTableFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "TITULO", "ANO", "SINOPSE", "CATEGORIA"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableFilmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableFilmesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableFilmes);

        jButtonAdicionarFilmes.setText("ADICIONAR");
        jButtonAdicionarFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarFilmesActionPerformed(evt);
            }
        });

        jButtonAlterarFilmes.setText("ALTERAR");
        jButtonAlterarFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarFilmesActionPerformed(evt);
            }
        });

        jButtonRemoverFilmes.setText("REMOVER");
        jButtonRemoverFilmes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverFilmesActionPerformed(evt);
            }
        });

        jLabel5.setText("Titulo:");

        jLabel6.setText("Ano");

        jLabel7.setText("Categoria");

        jLabel9.setText("Sinopse");

        jLabel10.setText("Campos");

        jTextAreaSinopseFilmes.setColumns(20);
        jTextAreaSinopseFilmes.setRows(5);
        jScrollPane4.setViewportView(jTextAreaSinopseFilmes);

        jTextFieldFilmesID.setEditable(false);

        jLabel23.setText("ID");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(16, 478, Short.MAX_VALUE)
                .addComponent(jButtonAdicionarFilmes)
                .addGap(33, 33, 33)
                .addComponent(jButtonAlterarFilmes)
                .addGap(30, 30, 30)
                .addComponent(jButtonRemoverFilmes)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldTituloFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldAnoFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxCategoriaFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(0, 178, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFilmesID, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionarFilmes)
                    .addComponent(jButtonAlterarFilmes)
                    .addComponent(jButtonRemoverFilmes))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldFilmesID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTituloFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldAnoFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBoxCategoriaFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                .addContainerGap())
        );

        Categorias.addTab("Filmes", jPanel3);

        jButton_adicionar_usuarios.setText("ADICIONAR");
        jButton_adicionar_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_adicionar_usuariosActionPerformed(evt);
            }
        });

        jButton_alterar_usuario.setText("ALTERAR");
        jButton_alterar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_alterar_usuarioActionPerformed(evt);
            }
        });

        jButton_remover_usuario.setText("REMOVER");
        jButton_remover_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_remover_usuarioActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jLabel8.setText("Nome:");

        jLabel11.setText("Cpf:");

        jLabel12.setText("E-mail:");

        jLabel13.setText("Senha:");

        jLabel14.setText("Confirmar Senha:");

        jLabel15.setText("ID");

        jTextField_id_usu.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField_ConfirmSenha_USU, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(jTextField_Cpf_USU, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_Nome_USU, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_Email_USU)
                    .addComponent(jPasswordField_Senha_USU))
                .addGap(523, 523, 523))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(16, 16, 16)
                .addComponent(jTextField_id_usu, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jTextField_Nome_USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(jTextField_id_usu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jTextField_Cpf_USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jTextField_Email_USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jPasswordField_Senha_USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jPasswordField_ConfirmSenha_USU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jTable_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "CPF", "E-MAIL", "CUPOM"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_usuariosMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTable_usuarios);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton_adicionar_usuarios)
                        .addGap(45, 45, 45)
                        .addComponent(jButton_alterar_usuario)
                        .addGap(40, 40, 40)
                        .addComponent(jButton_remover_usuario)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_adicionar_usuarios)
                    .addComponent(jButton_alterar_usuario)
                    .addComponent(jButton_remover_usuario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Categorias.addTab("Usuários", jPanel4);

        jLabel16.setText("Usuários");

        jTextFieldContratosID.setEditable(false);

        jLabel17.setText("Plano");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel18.setText("Data Início do Contrato");

        jLabel19.setText("Data Término do Contato");

        jLabel20.setText("Status");

        jTableContratos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "USUÁRIO", "PLANO", "DATA ÍNICIO", "DATA TERMINO", "STATUS"
            }
        ));
        jTableContratos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableContratosMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(jTableContratos);

        jButtonAlterarContrato.setText("ALTERAR");
        jButtonAlterarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarContratoActionPerformed(evt);
            }
        });

        jButtonAtivarContrato.setText("ATIVAR");
        jButtonAtivarContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtivarContratoActionPerformed(evt);
            }
        });

        jComboBoxStatusContrato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "< SELECIONE >", "ATIVO", "INATIVO", "SUSPENSO" }));

        jLabel26.setText("ID");

        jButton_LimparCampoContrato.setText("LIMPAR CAMPOS");
        jButton_LimparCampoContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LimparCampoContratoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxStatusContrato, 0, 227, Short.MAX_VALUE)
                            .addComponent(jComboBoxUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxPlanos, 0, 227, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(0, 815, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6)
                .addGap(6, 6, 6))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldContratosID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButtonAtivarContrato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAlterarContrato)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_LimparCampoContrato)))
                .addContainerGap(590, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldContratosID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jComboBoxUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jComboBoxPlanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextFieldDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextFieldDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jComboBoxStatusContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAtivarContrato)
                    .addComponent(jButtonAlterarContrato)
                    .addComponent(jButton_LimparCampoContrato))
                .addContainerGap(217, Short.MAX_VALUE))
        );

        Categorias.addTab("Contratos", jPanel5);

        jLabel21.setText("Usuário:");

        jLabel22.setText("Filmes:");

        jButtonVisualizacaoCompleta.setText("Visualização Completa");
        jButtonVisualizacaoCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVisualizacaoCompletaActionPerformed(evt);
            }
        });

        jTable_Visualizados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FILME", "USUARIO", "VISUALIZADO", "DATA"
            }
        ));
        jTable_Visualizados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_VisualizadosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable_Visualizados);

        jButton_deleteVisualizados.setText("Remover");
        jButton_deleteVisualizados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteVisualizadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(0, 168, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxVISusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxVisFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldDataAtualVisualizados, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(jButtonVisualizacaoCompleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(347, 347, 347))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton_deleteVisualizados)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jComboBoxVISusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(jComboBoxVisFilmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jTextFieldDataAtualVisualizados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonVisualizacaoCompleta, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton_deleteVisualizados)
                .addGap(182, 182, 182))
        );

        Categorias.addTab("Play Filmes ", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Categorias)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Categorias)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonVisualizacaoCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVisualizacaoCompletaActionPerformed

        try {

            String fil = jComboBoxVisFilmes.getSelectedItem().toString();
            String usu = jComboBoxVISusuario.getSelectedItem().toString();
            filmes = filmesBll.getConsultaNome(fil);
            usuario = usuariosBll.getUsuariosNome(usu);
            DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd / MM / YYYY");
            LocalDateTime Inicio = LocalDateTime.now();

            visualizado.setVis_fil_iden(filmes);
            visualizado.setVis_usu_iden(usuario);
            visualizado.setVis_completo(true);
            visualizado.setVis_data_geracao(formate.format(Inicio));
            visualizadoBll.Adicionar(visualizado);

            preencherGridVisualizados();

            JOptionPane.showMessageDialog(null, "Visualização Completa registrada no Banco de Dados com Sucesso ! Data : " + formate.format(Inicio));

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonVisualizacaoCompletaActionPerformed

    private void jButtonAtivarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtivarContratoActionPerformed
        try {

            validaFormularioContratos();

            String usu = jComboBoxUsuarios.getSelectedItem().toString();
            ValidaCombo(usu);
            usuario = usuariosBll.getUsuariosNome(usu);
            contrato.setCon_usu_iden(usuario);

            String pla = jComboBoxPlanos.getSelectedItem().toString();
            ValidaCombo(pla);
            planos = planosBll.getPlanosNome(pla);
            contrato.setCon_pla_iden(planos);

            DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd / MM / YYYY");
            LocalDateTime Inicio = LocalDateTime.now();
            LocalDateTime fim = Inicio.plusYears(1);
            contrato.setCon_inicio(formate.format(Inicio));
            contrato.setCon_fim(formate.format(fim));      
            contrato.setCon_status("ATIVO");
            contratosBll.Adicionar(contrato);
            
            preencherComboboxUsuario();
            preencherComboboxPlano();
            preencherGridContratos();
            limparCamposContratos();

          //   JOptionPane.showMessageDialog(null, "Contrato  ativado com Sucesso!");
            JOptionPane.showMessageDialog(null, usuario.getUsu_nome() + " você acaba de ativar o plano " + planos.getPla_nome()
                    + "\nNo valor de " + planos.getPla_preco() + " R$, Como você possui um cupom de " + usuario.getUsu_cup_iden().getCup_porcentagem()
                    + "% de desconto o preço a pagar será de: " + (planos.getPla_preco() - (planos.getPla_preco() * usuario.getUsu_cup_iden().getCup_porcentagem() / 100)) + "R$");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAtivarContratoActionPerformed

    private void jButtonAlterarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarContratoActionPerformed
        try {
            if (jTableContratos.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um contrato a ser alterado!");
            }

            validaFormularioContratos();

            String usu = jComboBoxUsuarios.getSelectedItem().toString();
            usuario = usuariosBll.getUsuariosNome(usu);
            contrato.setCon_usu_iden(usuario);

            String pla = jComboBoxPlanos.getSelectedItem().toString();
            planos = planosBll.getPlanosNome(pla);
            contrato.setCon_pla_iden(planos);

            int id = Integer.parseInt(jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 0).toString());
            Contratos con = contratosBll.getConsultaPorId(id);
            con.setCon_usu_iden(usuario);

            con.setCon_pla_iden(planos);

            DateTimeFormatter formate = DateTimeFormatter.ofPattern("dd / MM / YYYY");
            LocalDateTime Inicio = LocalDateTime.now();
            con.setCon_inicio(formate.format(Inicio));

            LocalDateTime fim = Inicio.plusYears(1);
            con.setCon_fim(formate.format(fim));
  
            con.setCon_status(jComboBoxStatusContrato.getSelectedItem().toString());
      
            contratosBll.Alterar(con);
            preencherComboboxUsuario();
            preencherGridContratos();
            limparCamposContratos();

            JOptionPane.showMessageDialog(null, "Contrato  alterado com Sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarContratoActionPerformed

    private void jButtonInativarContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInativarContratoActionPerformed
        try {
            if (jTableContratos.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um contrato a ser removido!");
            }

            contrato.setCon_iden(Integer.parseInt(jTextFieldContratosID.getText()));

            int id = Integer.parseInt(jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 0).toString());
            Contratos con = contratosBll.getConsultaPorId(id);
            con.setCon_status("Inativo");

            contratosBll.Alterar(con);
            jComboBoxVISusuario.removeItem(con.getCon_usu_iden().getUsu_nome());
            preencherGridContratos();
            preencherComboboxUsuario();
            preencherComboboxPlano();
            limparCamposContratos();

            JOptionPane.showMessageDialog(null, "Contrato inativado com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonInativarContratoActionPerformed


    private void jTableContratosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableContratosMouseClicked
        try {
            preencherFormularioContratos();
            jComboBoxStatusContrato.setEnabled(true);
            jComboBoxUsuarios.setEnabled(false);
            
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableContratosMouseClicked

    private void jTable_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_usuariosMouseClicked
        try {
            preencherFormularioUsuarios();

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTable_usuariosMouseClicked

    private void jButton_remover_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_remover_usuarioActionPerformed
        try {
            if (jTable_usuarios.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um usuário a ser removido!");
            }
            usuario.setUsu_iden(Integer.parseInt(jTextField_id_usu.getText()));
            usuariosBll.Remover(usuario);
            preencherGridUsuarios();
            preencherComboboxUsuario();
            limparCamposUsuarios();

            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_remover_usuarioActionPerformed

    private void jButton_alterar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_alterar_usuarioActionPerformed
        try {
            validaFormularioUsuarios();
            int id = Integer.parseInt(jTable_usuarios.getValueAt(jTable_usuarios.getSelectedRow(), 0).toString());
            Usuarios usuarioal = usuariosBll.getConsultaPorId(id);
            usuarioal.setUsu_nome(jTextField_Nome_USU.getText());
            usuarioal.setUsu_cpf(jTextField_Cpf_USU.getText());
            usuarioal.setUsu_email(jTextField_Email_USU.getText());
            char[] senha = jPasswordField_Senha_USU.getPassword();
            char[] senha2 = jPasswordField_ConfirmSenha_USU.getPassword();
            String Ssenha = new String(senha);
            String Ssenha2 = new String(senha2);
            if (ValidaSenha(Ssenha, Ssenha2)) {
                usuarioal.setUsu_senha(Ssenha);
            } else {
                throw new RuntimeException("As senhas não conferem, tente novamente");
            }
            usuariosBll.Alterar(usuarioal);

            preencherGridUsuarios();
            preencherComboboxUsuario();
            limparCamposUsuarios();

            JOptionPane.showMessageDialog(null, "Usuário alterado!");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_alterar_usuarioActionPerformed

    private void jButton_adicionar_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_adicionar_usuariosActionPerformed
        try {

            validaFormularioUsuarios();
            usuario.setUsu_nome(jTextField_Nome_USU.getText());
            usuario.setUsu_cpf(jTextField_Cpf_USU.getText());
            usuario.setUsu_email(jTextField_Email_USU.getText());
            char[] senha = jPasswordField_Senha_USU.getPassword();
            char[] senha2 = jPasswordField_ConfirmSenha_USU.getPassword();
            String Ssenha = new String(senha);
            String Ssenha2 = new String(senha2);

            if (ValidaSenha(Ssenha, Ssenha2)) {
                usuario.setUsu_senha(Ssenha);
            } else {
                throw new RuntimeException("As senhas não conferem, tente novamente");
            }

            cupom = new Cupons();
            cupomBll.Adicionar(cupom);
            usuario.setUsu_cup_iden(cupomBll.getLastCupom());
            usuariosBll.Adicionar(usuario);
            preencherComboboxUsuario();
            preencherGridUsuarios();
            JOptionPane.showMessageDialog(null, usuario.getUsu_nome() + " você foi cadastrado em nosso sistema\n"
                    + " acaba de ganhar " + usuario.getUsu_cup_iden().getCup_porcentagem() + "% de desconto no plano que for contratar!");
            limparCamposUsuarios();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_adicionar_usuariosActionPerformed

    private void jButtonRemoverFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverFilmesActionPerformed
        try {
            if (jTableFilmes.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um filme a ser removido!");
            }
            int id = Integer.parseInt(jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 0).toString());
            filmes = filmesBll.getConsultaPorId(id);
            filmesBll.Remover(filmes);
            preencherGridFilmes();
            preencherComboboxFilmes();
            limparCamposFilmes();

            JOptionPane.showMessageDialog(null, "Filme removido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverFilmesActionPerformed

    private void jButtonAlterarFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarFilmesActionPerformed
        try {
            if (jTableFilmes.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um filme a ser alterado!");
            }

            String nome = jComboBoxCategoriaFilmes.getSelectedItem().toString();
            categoria = categoriaBll.getCategoriaNome(nome);

            int id = Integer.parseInt(jTableFilmes.getValueAt(jTableFilmes.getSelectedRow(), 0).toString());
            Filmes filmesDal = filmesBll.getConsultaPorId(id);
            filmesDal.setFil_cat_iden(categoria);

            filmesDal.setFil_iden(Integer.parseInt(jTextFieldFilmesID.getText()));
            filmesDal.setFil_titulo(jTextFieldTituloFilmes.getText());
            filmesDal.setFil_ano(Integer.parseInt(jTextFieldAnoFilmes.getText()));
            filmesDal.setFil_sinopse(jTextAreaSinopseFilmes.getText());
            filmesBll.Alterar(filmesDal);

            validaFormularioFilmes();
            preencherComboboxFilmes();
            preencherGridFilmes();
            limparCamposFilmes();

            JOptionPane.showMessageDialog(null, "filme alterado!");
        } catch (Exception error) {

            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarFilmesActionPerformed

    private void jButtonAdicionarFilmesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarFilmesActionPerformed
        try {

            validaFormularioFilmes();
            String nome = jComboBoxCategoriaFilmes.getSelectedItem().toString();
            ValidaCombo(nome);
            categoria = categoriaBll.getCategoriaNome(nome);
            filmes.setFil_cat_iden(categoria);

            filmes.setFil_titulo(jTextFieldTituloFilmes.getText());
            filmes.setFil_ano(Integer.parseInt(jTextFieldAnoFilmes.getText()));
            filmes.setFil_sinopse(jTextAreaSinopseFilmes.getText());
            filmesBll.Adicionar(filmes);
            preencherGridFilmes();
            preencherComboboxFilmes();
            limparCamposFilmes();

            JOptionPane.showMessageDialog(null, "Filme incluido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAdicionarFilmesActionPerformed

    private void jTableFilmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableFilmesMouseClicked
        try {
            preencherFormularioFilmes();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableFilmesMouseClicked

    private void jButtonRemoverPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverPlanosActionPerformed
        try {
            if (jTablePlanos.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um plano a ser removido!");
            }
            planos.setPla_iden(Integer.parseInt(jTextFieldPlanosID.getText()));
            planosBll.Remover(planos);
            preencherGridPlanos();
            preencherComboboxPlano();
            limparCamposPlanos();

            JOptionPane.showMessageDialog(null, "Plano removido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverPlanosActionPerformed

    private void jButtonAdicionarPlanosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarPlanosActionPerformed
        try {
            ValidaFormularioPlanos();
            planos.setPla_acesso_simultaneo(Integer.parseInt(jTextFieldAcessos.getText()));
            planos.setPla_nome(jTextFieldPlanosNome.getText());
            planos.setPla_preco(Float.parseFloat(jTextFieldPlanosPrecos.getText()));
            planosBll.Adicionar(planos);
            preencherComboboxPlano();
            preencherGridPlanos();
            limparCamposPlanos();

            JOptionPane.showMessageDialog(null, "Plano incluido com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAdicionarPlanosActionPerformed

    private void jTablePlanosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePlanosMouseClicked
        try {
            preencherFormularioPlanos();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTablePlanosMouseClicked

    private void jButtonAlterarPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarPlanoActionPerformed
        try {
            if (jTablePlanos.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um plano a ser alterado!");
            }

            ValidaFormularioPlanos();
            planos.setPla_iden(Integer.parseInt(jTextFieldPlanosID.getText()));
            planos.setPla_acesso_simultaneo(Integer.parseInt(jTextFieldAcessos.getText()));
            planos.setPla_nome(jTextFieldPlanosNome.getText());
            planos.setPla_preco(Float.parseFloat(jTextFieldPlanosPrecos.getText()));
            planosBll.Alterar(planos);
            preencherGridPlanos();
            preencherComboboxPlano();
            limparCamposPlanos();

            JOptionPane.showMessageDialog(null, "Plano alterado!");
        } catch (Exception error) {

            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarPlanoActionPerformed

    private void jButtonRemoverCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverCategoriaActionPerformed
        try {
            if (jTableCategoria.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione a categoria a ser removida!");
            }
            categoria.setCat_iden(Integer.parseInt(jTextFieldId.getText()));
            categoriaBll.Remover(categoria);
            preencherGridCategoria();
            preencherComboboxCategoria();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Categoria removida com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonRemoverCategoriaActionPerformed

    private void jButtonAlterarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarCategoriaActionPerformed
        try {
            if (jTableCategoria.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione a categoria a ser alterada!");
            }
            validarFormularioCategorias();
            categoria.setCat_nome(jTextFieldNome.getText());
            categoria.setCat_iden(Integer.parseInt(jTextFieldId.getText()));
            categoriaBll.Alterar(categoria);
            preencherGridCategoria();
            preencherComboboxCategoria();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Categoria alterada!");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAlterarCategoriaActionPerformed

    private void jButtonAdicionarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarCategoriaActionPerformed

        try {
            validarFormularioCategorias();
            categoria.setCat_nome(jTextFieldNome.getText());
            categoriaBll.Adicionar(categoria);
            preencherGridCategoria();
            preencherComboboxCategoria();
            limparCampos();

            JOptionPane.showMessageDialog(null, "Categoria incluida com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonAdicionarCategoriaActionPerformed

    private void jTableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCategoriaMouseClicked
        try {
            preencherFormularioCategorias();
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jTableCategoriaMouseClicked

    private void jButton_deleteVisualizadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteVisualizadosActionPerformed
        try {
            if (jTable_Visualizados.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione uma visualização a ser removida!");
            }

            int id = Integer.parseInt(jTable_Visualizados.getValueAt(jTable_Visualizados.getSelectedRow(), 0).toString());
            visualizado = visualizadoBll.getConsultaPorId(id);
            visualizadoBll.Remover(visualizado);
            preencherGridVisualizados();
            limparVisualizados();
            JOptionPane.showMessageDialog(null, "Visualização removida com sucesso!");

        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton_deleteVisualizadosActionPerformed

    private void jTable_VisualizadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_VisualizadosMouseClicked

    }//GEN-LAST:event_jTable_VisualizadosMouseClicked


    private void jButtonSuspenderContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuspenderContratoActionPerformed
 try {
            if (jTableContratos.getSelectedRow() == -1) {
                throw new RuntimeException("Selecione um contrato a ser suspenso !");
            }

            contrato.setCon_iden(Integer.parseInt(jTextFieldContratosID.getText()));

            int id = Integer.parseInt(jTableContratos.getValueAt(jTableContratos.getSelectedRow(), 0).toString());
            Contratos con = contratosBll.getConsultaPorId(id);
            con.setCon_status("Suspenso");
            contratosBll.Alterar(con);
            jComboBoxVISusuario.removeItem(con.getCon_usu_iden().getUsu_nome());
            preencherGridContratos();
            preencherComboboxUsuario();
            preencherComboboxPlano();
            limparCamposContratos();

            JOptionPane.showMessageDialog(null, "Contrato suspenso com sucesso!");
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, error.getMessage(), "Menssagem", JOptionPane.ERROR_MESSAGE);
        }








        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSuspenderContratoActionPerformed

    private void jButton_LimparCampoContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LimparCampoContratoActionPerformed
        limparCamposContratos();
    }//GEN-LAST:event_jButton_LimparCampoContratoActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TorrentzFilmes_Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TorrentzFilmes_Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TorrentzFilmes_Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TorrentzFilmes_Tela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TorrentzFilmes_Tela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Categorias;
    private javax.swing.JButton jButtonAdicionarCategoria;
    private javax.swing.JButton jButtonAdicionarFilmes;
    private javax.swing.JButton jButtonAdicionarPlanos;
    private javax.swing.JButton jButtonAlterarCategoria;
    private javax.swing.JButton jButtonAlterarContrato;
    private javax.swing.JButton jButtonAlterarFilmes;
    private javax.swing.JButton jButtonAlterarPlano;
    private javax.swing.JButton jButtonAtivarContrato;
    private javax.swing.JButton jButtonRemoverCategoria;
    private javax.swing.JButton jButtonRemoverFilmes;
    private javax.swing.JButton jButtonRemoverPlanos;
    private javax.swing.JButton jButtonVisualizacaoCompleta;
    private javax.swing.JButton jButton_LimparCampoContrato;
    private javax.swing.JButton jButton_adicionar_usuarios;
    private javax.swing.JButton jButton_alterar_usuario;
    private javax.swing.JButton jButton_deleteVisualizados;
    private javax.swing.JButton jButton_remover_usuario;
    private javax.swing.JComboBox<String> jComboBoxCategoriaFilmes;
    private javax.swing.JComboBox<String> jComboBoxPlanos;
    private javax.swing.JComboBox<String> jComboBoxStatusContrato;
    private javax.swing.JComboBox<String> jComboBoxUsuarios;
    private javax.swing.JComboBox<String> jComboBoxVISusuario;
    private javax.swing.JComboBox<String> jComboBoxVisFilmes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPasswordField jPasswordField_ConfirmSenha_USU;
    private javax.swing.JPasswordField jPasswordField_Senha_USU;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableCategoria;
    private javax.swing.JTable jTableContratos;
    private javax.swing.JTable jTableFilmes;
    private javax.swing.JTable jTablePlanos;
    private javax.swing.JTable jTable_Visualizados;
    private javax.swing.JTable jTable_usuarios;
    private javax.swing.JTextArea jTextAreaSinopseFilmes;
    private javax.swing.JTextField jTextFieldAcessos;
    private javax.swing.JTextField jTextFieldAnoFilmes;
    private javax.swing.JTextField jTextFieldContratosID;
    private javax.swing.JTextField jTextFieldDataAtualVisualizados;
    private javax.swing.JTextField jTextFieldDataFim;
    private javax.swing.JTextField jTextFieldDataInicio;
    private javax.swing.JTextField jTextFieldFilmesID;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldPlanosID;
    private javax.swing.JTextField jTextFieldPlanosNome;
    private javax.swing.JTextField jTextFieldPlanosPrecos;
    private javax.swing.JTextField jTextFieldTituloFilmes;
    private javax.swing.JTextField jTextField_Cpf_USU;
    private javax.swing.JTextField jTextField_Email_USU;
    private javax.swing.JTextField jTextField_Nome_USU;
    private javax.swing.JTextField jTextField_id_usu;
    // End of variables declaration//GEN-END:variables
}
