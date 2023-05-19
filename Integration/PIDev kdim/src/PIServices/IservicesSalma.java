/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import java.util.List;

/**
 *
 * @author ASuS
 * @param <P>
 */
public interface IservicesSalma <P> {
    
    public void envoyer(P p);
    public void modifier(P p);
    public void supprimer(P p);
    public List<P> afficher();

}
