package presentation;

import dao.DaoImpl;
import metier.MetierImpl;

/**
 * Présentation 1 : Instanciation Statique (Couplage Fort).
 * Démontre la limitation du couplage direct :
 * Pour changer d'implémentation (ex: passer à DaoImpl2), il faut MODIFIER ce code et RECOMPILER.
 */
public class Presentation1 {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("     DÉMONSTRATION 1 : INSTANCIATION STATIQUE     ");
        System.out.println("            (Couplage Fort - Rigide)              ");
        System.out.println("==================================================");

        // Instanciation statique directe avec l'opérateur 'new'
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();

        // Injection manuelle de dépendance via le setter
        metier.setDao(dao);

        // Exécution du calcul
        double res = metier.calcul();
        System.out.println(">>> Résultat final (Statique) = " + res);
        System.out.println("==================================================");
    }
}
