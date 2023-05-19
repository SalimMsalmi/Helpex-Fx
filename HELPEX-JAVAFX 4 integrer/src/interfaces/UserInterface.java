/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.User;
import java.util.List;

/**
 *
 * @author Asus-PC
 */
public interface UserInterface<U> {
    List<U> afficherProUsers();
     List<U> afficherClients();
    void delete(U u);
    void updateProfile(U u);
    void registerPro(U u);
        void registerClient(U u);
        public void Enable(U u);

    
    
}
