/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.User;
import Model.UserDAO;
import Utils.CriptografiaSenha;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Marcelo
 */
public class RegistroUserController {
    private String nome;
    private String email;
    private String senha;
    private String last_name;
    private String first_name;
    
    public void comNome(String nome){
        this.nome = nome;
    }
    
    public void comEmail(String email){
        this.email = email;
    }
    
    public void comSenha(String senha){
        this.senha = senha;
    }
    
    public void comLast_name(String last_name){
        this.last_name = last_name;
    }
    
    public void comFirst_name(String first_name){
        this.first_name = first_name;
    }
    
   public String executar() throws ParseException{
       String campoObrigatorio = this.validarObrigatorios();
       
       if(campoObrigatorio.isEmpty()){
           System.out.println(this.nome);
           User user = new User();
           user.setUsername(this.nome);
           user.setEmail(this.email);
           user.setLast_name(this.last_name);
           user.setFirst_name(this.first_name);
           
           CriptografiaSenha criptografia = new CriptografiaSenha();
           criptografia.comSenha(this.senha);
           String senhaHex = criptografia.criptografar();
           
           user.setPassword(senhaHex);       
           
           UserDAO uDao = new UserDAO();
           uDao.registrar(user);
           
       }
       
       System.err.println(campoObrigatorio);
       return campoObrigatorio;
   }
   
   
   public String validarObrigatorios(){
       
       if(this.nome.isEmpty()){
           return "nome";
       }
       
       else if(this.email.isEmpty()){
           return "email";
       }
       
       else if(this.senha.isEmpty()){
           return "senha";
       }
       
       else if(this.last_name.isEmpty() || this.last_name.trim().length() < 14){
           return "last_name";
       }
       
       else if(this.first_name.isEmpty() || this.first_name.trim().length() < 14){
           return "first_name";
       }
  
    return "";
    
   }  
  
    
}

