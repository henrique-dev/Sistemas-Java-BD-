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
        
        Conexao conexao = new Conexao();
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try {
            con = conexao.conectar();
            
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
            conexao.desconectar(con);
            return clientes;
        }        
    }
    
}
