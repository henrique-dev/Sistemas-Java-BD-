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
package com.br.phdev.log;

import javax.security.auth.login.LoginException;
import javax.swing.JOptionPane;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class Log {
    
    public static Log instancia;
    
    private Log(){        
        this.instancia = new Log();
    }
    
    public static void d(Object obj, String msg) {
        System.out.println(obj.getClass() + "/D: " + msg);
    }
    
    public static void e(Object obj, String msg) {
        System.err.println(obj.getClass() + "/D: " + msg);
    }        
    
    public static void e(Object obj, String msg, boolean exibirMensagemSomenteNoConsole) {        
        if (!exibirMensagemSomenteNoConsole)
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), msg);
    }
    
}
