package Controller;


import Utils.CriptografiaSenha;
import Model.UserDAO;
import Model.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Marcelo
 */


public class AutenticacaoController {
    
    String username;
    String pass;
    
    public void comLogin(String username){
        this.username = username;
    }
    
    public void comSenha(String pass){
        this.pass = pass;
    }
    
    public boolean autenticar(){
        User user = new User();
        
        CriptografiaSenha cripto = new CriptografiaSenha();
        cripto.comSenha(this.pass);
        String passHex = cripto.criptografar();
        
        UserDAO uDao = new UserDAO();
        List<User> userList = uDao.getUsername(this.username);
        
        for (User user : userList) {
            if(user.getPassword().equals(passHex)){
                return true;
            }
            return false;
        }
        
        return false;
    }
    
}
