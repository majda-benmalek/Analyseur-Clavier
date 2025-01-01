import java.util.List;
public class Mouvement3 extends Mouvement{

    public Mouvement3(List<Touche> l, int o) {
        super(l, o);
    }
    boolean notRedirection(){
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean versInterieur = t1.getCoord().getX() < t2.getCoord().getX() && t2.getCoord().getX() < t3.getCoord().getX() ;
        boolean versExterieur = t1.getCoord().getX() > t2.getCoord().getX() && t2.getCoord().getX() > t3.getCoord().getX();
        
        return versInterieur || versExterieur ;
    }

    boolean redirectionSansIndex(){
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean b1 = t1.getDoigt() == Doigt.INDEX_D || t1.getDoigt() == Doigt.INDEX_G;
        boolean b2 = t2.getDoigt() == Doigt.INDEX_D || t2.getDoigt() == Doigt.INDEX_G;
        boolean b3 = t3.getDoigt() == Doigt.INDEX_D || t3.getDoigt() == Doigt.INDEX_G;
        return b1 || b2 || b3;
    }

    boolean skipgramme(){
        Touche t1 = this.getSqTouches().get(0);
        Touche t2 = this.getSqTouches().get(1);
        Touche t3 = this.getSqTouches().get(2);
        boolean memeDoigt = t1.getDoigt() == t3.getDoigt() && t1.getDoigt().getMain() == t3.getDoigt().getMain();
        boolean mainDiff = t2.getDoigt().getMain() != t1.getDoigt().getMain() && t2.getDoigt().getMain() != t3.getDoigt().getMain();

        return memeDoigt && mainDiff;
    }    
}




// public class Mouvement3 extends Mouvement{

//     public Mouvement3(List<Touche> l, int o) {
//         super(l, o);
//     }


//     // boolean notRedirection(Touche t1 , Touche t2 , Touche t3){
//     //     boolean versInterieur = t1.getCoord().getY() < t2.getCoord().getY() && t2.getCoord().getY() < t3.getCoord().getY() ;
//     //     boolean versExterieur = t1.getCoord().getY() > t2.getCoord().getY() && t2.getCoord().getY() > t3.getCoord().getY();
        
//     //     return versInterieur || versExterieur ;
//     // }

//     boolean notRedirection(Touche t1 , Touche t2 , Touche t3){
//         boolean versInterieur = t1.getCoord().getX() < t2.getCoord().getX() && t2.getCoord().getX() < t3.getCoord().getX() ;
//         boolean versExterieur = t1.getCoord().getX() > t2.getCoord().getX() && t2.getCoord().getX() > t3.getCoord().getX();
        
//         return versInterieur || versExterieur ;
//     }

//     boolean redirectionSansIndex(Touche t1 , Touche t2 , Touche t3){
//         boolean b1 = t1.getDoigt() == Doigt.INDEX_D || t1.getDoigt() == Doigt.INDEX_G;
//         boolean b2 = t2.getDoigt() == Doigt.INDEX_D || t2.getDoigt() == Doigt.INDEX_G;
//         boolean b3 = t3.getDoigt() == Doigt.INDEX_D || t3.getDoigt() == Doigt.INDEX_G;

//         // System.out.println("t1 = "+t1.getDoigt());
//         // System.out.println("t2 = "+t2.getDoigt());
//         // System.out.println("t3 = "+t3.getDoigt());

//         return b1 || b2 || b3;
//     }

//     boolean skipgramme(Touche t1 , Touche t2 , Touche t3){
//         boolean memeDoigt = t1.getDoigt() == t3.getDoigt() && t1.getDoigt().getMain() == t3.getDoigt().getMain();
//         boolean mainDiff = t2.getDoigt().getMain() != t1.getDoigt().getMain() && t2.getDoigt().getMain() != t3.getDoigt().getMain();

//         return memeDoigt && mainDiff;
//     }

    // @Override
    // public void calculScore() {
    //     Touche t1 = this.getSqTouches().get(0);
    //     Touche t2 = this.getSqTouches().get(1);
    //     Touche t3 = this.getSqTouches().get(2);
    //     double score = 0.0;
    //     if (notRedirection(t1, t2, t3)){
    //         score = score + 1.0;
    //         System.out.println("pas redirection");
    //     }
    //     else{
    //         System.out.println("redirection");
    //         // score = score - 1.0;
    //         if (redirectionSansIndex(t1, t2, t3) == false){
    //             score = score - 2.0;
    //             System.out.println("pas index");
    //         }
    //         else{
    //             System.out.println("index");
    //         }
    //         // else{
    //         //     score = score + 1.0;
    //         // }
    //     }
    //     if (skipgramme(t1, t2, t3)){
    //         score = score - 1.0;
    //         System.out.println("skipgramme ");
    //     }
    //     else{
    //         score = score + 1.0;
    //         System.out.println("pas skipgramme");
    //     }
    //     score = score*getOccurrences();
    //     this.setScore(score);
    // }
    
// }
