package Interfaces;


import entites.Item;
import java.util.ArrayList;

public interface InterfaceItem {
    public void AjouterItem(Item item) ;
    public  void EditerItem( Item item) ;
    public  void SupprimerItem(int id);
    public ArrayList<Item> listerItems ();

}
