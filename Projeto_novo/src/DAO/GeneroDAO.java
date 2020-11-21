
package DAO;

import DTO.GeneroDTO;
import DTO.UsuarioDTO;
import VIEW.DashBoard;
import VIEW.GenerosFavoritos;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class GeneroDAO {

    
   
Connection con;
PreparedStatement pstm;
ArrayList<GeneroDTO> lista = new ArrayList<>();    
    /*ESSE MÉTODO IRÁ FAZER UMA CONSULTA NO BANCO PARA LISTAR OS GÊNEROS NA TABELA.*/
    public ArrayList<GeneroDTO> PesquisarGenero(){
        String sql = "select * from genero";
        con = new ConexaoDAO().conectaBD();
        
         
        
        try {
            pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            /*ENQUANTO A TABELA DO ResultSet ESTIVER COM VALORES CHAMA O MÉTODO SetNome_genero e SetId
            E ARMAZENA NA VARIÁVEL QUE ESTÁ NA CLASSE GeneroDTO.*/
            while(rs.next()){
               GeneroDTO objgeneroDTO = new GeneroDTO();
               objgeneroDTO.setNome_genero(rs.getString("nome_genero"));
               objgeneroDTO.setId(rs.getInt("genderId"));
               
               /*ARMAZENA GÊNERO E O SEU ID NUM ARRYLIST.*/
               lista.add(objgeneroDTO);
            }
            
        } 
        
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: "+erro);
        }
        
        return lista;
        
    }
    
    
    
    public  ArrayList<GeneroDTO> PesquisarGenerosAdicionados(int id){
      /*RECEBEMOS COMO PARÂMETRO O ID DO USUÁRIO PARA APENAS LISTAR OS GÊNEROS FAVORTITOS DO USUÁRIO LOGADO.*/  
      
      String sql = "select * from favorito WHERE usuario = ?";
        con = new ConexaoDAO().conectaBD();
        UsuarioDTO objusuarioDTO = new UsuarioDTO();
        
        try {
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
               GeneroDTO objgeneroDTO = new GeneroDTO();
               objgeneroDTO.setNome_genero(rs.getString("genero"));
              
               lista.add(objgeneroDTO);
            } 
        } 
        
        catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "FuncionarioDAO Pesquisar: "+erro);
        }
        
        return lista;    
    }    
}        
         
