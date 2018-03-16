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
package com.br.phdev.bd;

import com.br.phdev.log.Log;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Conexao {
    
    public Connection conectar(){
        
        Connection con = null;
        String url = "jdbc:postgresql://localhost:5432/<nome-bd";
        String usuario = "nome-usuario";
        String senha = "senha-usuario";
        
        try {                                    
            
            con = DriverManager.getConnection(url, usuario, senha);                        
            
        } catch (SQLException e) {
            Log.e(this, e.getMessage());
        } finally {
            return con;
        }                
    }
    
    public void desconectar(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            Log.e(this, e.getMessage());
        }
    }
    
}
