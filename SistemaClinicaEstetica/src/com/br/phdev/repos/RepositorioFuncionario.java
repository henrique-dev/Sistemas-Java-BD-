/*
 * Copyright (C) 2018 Paulo Henrique Gonçalves Bacelar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.br.phdev.repos;

import com.br.phdev.bd.Conexao;
import com.br.phdev.log.Log;
import com.br.phdev.models.Atendente;
import com.br.phdev.models.Cliente;
import com.br.phdev.models.Funcionario;
import com.br.phdev.models.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class RepositorioFuncionario {
    
    private static RepositorioFuncionario instancia;
    
    public static RepositorioFuncionario getInstancia() {
        if (instancia == null)
            instancia = new RepositorioFuncionario();
        return instancia;
    }
    
    public ArrayList<Funcionario> listar() throws SQLException{
        
        Connection con = null;
        
        Conexao conexaobd = new Conexao();
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        
        try {
            con = conexaobd.conectar();
            
            String sql = "SELECT * FROM FUNCIONARIO";
            PreparedStatement prepared = con.prepareStatement(sql);
            ResultSet resultado = prepared.executeQuery();
            
            while (resultado.next()) {                                
                if (resultado.getString("CARGO").equals("GERENTE")) {
                    Gerente tmpGerente = new Gerente(
                            resultado.getString("NOME"),
                            resultado.getString("LOGIN"),
                            resultado.getString("SENHA"));
                    funcionarios.add(tmpGerente);
                } else {
                    Atendente tmpAtendente = new Atendente(
                            resultado.getString("NOME"),
                            resultado.getString("LOGIN"),
                            resultado.getString("SENHA"));
                    funcionarios.add(tmpAtendente);
                }
                                                
            }
        } catch (SQLException e) {
            Log.e(this, e.getMessage());
        } finally {
            conexaobd.desconectar(con);
            return funcionarios;
        }        
    }
    
    public void inserir(Funcionario funcionario) throws SQLException{
        Conexao conexaobd = new Conexao();
        Connection con = conexaobd.conectar();
        
        String sql = "INSERT INTO FUNCIONARIO(NOME, LOGIN, SENHA) VALUES (?,?,?)";
        PreparedStatement prepared =  con.prepareStatement(sql);        
        prepared.setString(0, funcionario.getNome());
        prepared.setString(1, funcionario.getLogin());
        prepared.setString(2, funcionario.getSenha());        
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
    public void remover(String login) throws SQLException {
        Conexao conexaobd = new Conexao();        
        Connection con = conexaobd.conectar();
        
        String sql = "DELETE FROM FUNCIONARIO WHERE LOGIN=" + login;
        PreparedStatement prepared = con.prepareStatement(sql);
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
    public void atualizar(Funcionario funcionario) throws SQLException {
        Conexao conexaobd = new Conexao();
        Connection con = conexaobd.conectar();
        
        String sql = "UPDATE FUNCIONARIO SET NOME=?, SENHA=? WHERE LOGIN=?";
        PreparedStatement prepared = con.prepareStatement(sql);
        prepared.setString(0, funcionario.getNome());
        prepared.setString(1, funcionario.getSenha());        
        prepared.setString(2, funcionario.getLogin());
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
}
