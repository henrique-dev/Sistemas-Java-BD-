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
package com.br.phdev.forms;

import com.br.phdev.log.Log;
import com.br.phdev.repos.RepositorioFuncionario;
import com.sun.glass.ui.Cursor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.security.auth.login.LoginException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Paulo Henrique Gonçalves Bacelar
 */
public class FormLogin extends JFrame{
    
    private JLabel labelInfo;
    private JLabel labelUsuario;
    private JLabel labelSenha;
    private JTextField textFieldUsuario;
    private JPasswordField pwdFieldSenha;
    private JButton buttonEntrar;
    
    public FormLogin() {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        
        
        init();                
        pack();
        
        setVisible(true);        
    }
    
    private void init() {
        
        Container ct1 = getContentPane();
        ct1.setLayout(new GridLayout(3, 2));
                
        FlowLayout layout = new FlowLayout(FlowLayout.TRAILING, 10, 20);        
        
        
        JPanel p1 = new JPanel(layout);                                          
        JPanel p2 = new JPanel(layout);                                          
                
        //labelInfo = new JLabel("Informe suas credenciais");        
        //ct1.add(BorderLayout.PAGE_START, labelInfo);                       
        
        labelUsuario = new JLabel("Usuario: ", JLabel.RIGHT);                                         
        p1.add(labelUsuario);                               
        
        textFieldUsuario = new JTextField(20);        
        p1.add(textFieldUsuario);
        
        labelSenha = new JLabel("Senha: ", JLabel.RIGHT);                
        p2.add(labelSenha);                
        
        pwdFieldSenha = new JPasswordField(20);        
        p2.add(pwdFieldSenha);                                
                        
        ct1.add(p1);        
        ct1.add(p2);      
        
        buttonEntrar = new JButton("Entrar");
        buttonEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            buttonEntrar.setEnabled(false);
                            setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                            RepositorioFuncionario.getInstancia().login(textFieldUsuario.getText(), pwdFieldSenha.getText());                                              
                        } catch (SQLException sqle) {                            
                            Log.e(this, sqle.getMessage(), false);                            
                        } catch (LoginException loge) {
                            Log.e(this, loge.getMessage(), false);
                        } 
                        buttonEntrar.setEnabled(true);
                        setCursor(null);
                    }
                }.start();                
            }
        });
        
        ct1.add(buttonEntrar);
                        
    }
    
    public static void main(String[] args) {
        new FormLogin();
    }
    
}
