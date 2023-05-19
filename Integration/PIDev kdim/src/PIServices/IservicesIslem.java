/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PIServices;

import java.util.List;
import java.util.ArrayList;
import PIClass.Ev;

/**
 *
 * @author Islem
 *  @param <A>
 */
public interface IservicesIslem <A>{
  public void ajouter( A t);
  public void supprimer( A t);
  public void modifier( A t);
  public List<A> afficher();

    
}
