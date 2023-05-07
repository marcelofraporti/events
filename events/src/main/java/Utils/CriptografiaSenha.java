package Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.security.MessageDigest;

/**
 *
 * @author Arthur
 */
public class CriptografiaSenha {
    String senha;
    public void comSenha(String senha){
        this.senha = senha;
    }
    
    public String criptografar(){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String senhaAux = senha;
            byte messageDigest[] = md.digest(senha.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for(byte b : messageDigest){
                sb.append(String.format("%02X", 0xFF & b));
            }

            String senhaHex = sb.toString();
            return senhaHex;
            
       }catch(Exception e){
           System.out.println("Erro ao salvar a senha!");
       }
       
       return null;
        
    }
    
}
