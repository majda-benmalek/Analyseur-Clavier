import java.util.List;

public class Mouvement2 extends Mouvement {

    public Mouvement2(List<Touche> l, int o) {
        super(l, o);
    }

    @Override
    public void calculScore() {
        // Mouvement à minimisé : 
        // Mouvement à un seul doigt : "ju"(pas ouf) donc les touches qui sont dans le rayon du même doigt "je" => chaque touche est touché par un doigt diff
        // Ciseaux : Mouvement de la même mains mais rangée différente "ji" même main diff doigt mais pas la même rangé 
        // Mouvement a extension latéral : Touche qui ne sont pas sur la même colonne que la colonne de repods du doigt : "h" (6,2) index droit = (7,2)

        // Mouvement à maximisé :
        // Les alternanaces : 2 touches chaque touche est faite par une main 
        // Roulement : 2 doigts différent d'une même main 
    }

}
