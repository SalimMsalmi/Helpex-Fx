/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import java.util.List;

/**
 *
 * @author HP
 */
public interface Iservice<T> 
{
    public void Ajouter(T t);
    public void Supprimer(T t);
    public void Modifier(T t);
    public List<T> afficher();
    public List<T> userListe();
    public List<T> TrieParUsername();
    public List<T> recherche(String Xusername);
    
    //public void PasswordOublié(T t);
    //public void Crypt(T t);
}
