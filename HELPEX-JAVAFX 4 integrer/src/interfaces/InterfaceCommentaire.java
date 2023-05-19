/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Commentaire;
import entities.Poste;
import java.util.List;

/**
 *
 * @author USER
 */
public interface InterfaceCommentaire {
        public void ajouterCommentaire(Commentaire c,Poste P);
        public void modifierCommentaire(Commentaire c,String description);
        public void supprimerCommentaire(Commentaire c);
        public List<Commentaire> findbyposte(int id);



}
