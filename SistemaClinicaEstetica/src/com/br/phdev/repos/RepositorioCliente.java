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
import com.br.phdev.models.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class RepositorioCliente {
        
    private static RepositorioCliente instancia;        
    
    public static RepositorioCliente getInstancia(){
        if (instancia == null)
            instancia = new RepositorioCliente();
        return instancia;
    }
    
    public ArrayList<Cliente> listar() throws SQLException{
        
        Connection con = null;
        
        Conexao conexaobd = new Conexao();
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            con = conexaobd.conectar();
            
            String sql = "SELECT * FROM CLIENTE";
            PreparedStatement prepared = con.prepareStatement(sql);
            ResultSet resultado = prepared.executeQuery();
            
            while (resultado.next()) {
                Cliente tmpCliente = new Cliente(
                        resultado.getInt(sql), 
                        resultado.getString(sql),
                        resultado.getString(sql),
                        resultado.getString(sql));
                clientes.add(tmpCliente);
            }
        } catch (SQLException e) {
            Log.e(this, e.getMessage());
        } finally {
            conexaobd.desconectar(con);
            return clientes;
        }        
    }
    
    public void inserir(Cliente cliente) throws SQLException{
        Conexao conexaobd = new Conexao();
        Connection con = conexaobd.conectar();
        
        String sql = "INSERT INTO CLIENTE(CODIGO, NOME, ENDERECO, TELEFONE) VALUES (?,?,?,?)";
        PreparedStatement prepared =  con.prepareStatement(sql);
        prepared.setInt(0, cliente.getCodigo());
        prepared.setString(1, cliente.getNome());
        prepared.setString(2, cliente.getEndereco());
        prepared.setString(3, cliente.getTelefone());        
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
    public void remover(int codigoCliente) throws SQLException {
        Conexao conexaobd = new Conexao();        
        Connection con = conexaobd.conectar();
        
        String sql = "DELETE FROM CLIENTE WHERE CODIGO=" + codigoCliente;
        PreparedStatement prepared = con.prepareStatement(sql);
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
    public void atualizar(Cliente cliente) throws SQLException {
        Conexao conexaobd = new Conexao();
        Connection con = conexaobd.conectar();
        
        String sql = "UPDATE CLIENTE SET NOME=?, ENDERECO=?, TELEFONE=? WHERE CODIGO=?";
        PreparedStatement prepared = con.prepareStatement(sql);
        prepared.setString(0, cliente.getNome());
        prepared.setString(1, cliente.getEndereco());
        prepared.setString(2, cliente.getTelefone());
        prepared.setInt(3, cliente.getCodigo());
        prepared.execute();
        
        conexaobd.desconectar(con);
    }
    
}
