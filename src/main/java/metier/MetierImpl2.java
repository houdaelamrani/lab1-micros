package metier;

import dao.IDao;

/**
 * DEUXIÈME EXTENSION de l'interface IMetier (Version 2 Haute Précision).
 * Fournit un algorithme de calcul.
 */
public class MetierImpl2 implements IMetier {

    // Couplage faible via l'interface IDao
    private IDao dao;

    public MetierImpl2() {
    }

    public MetierImpl2(IDao dao) {
        this.dao = dao;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }

    public IDao getDao() {
        return dao;
    }

    @Override
    public double calcul() {
        if (dao == null) {
            throw new IllegalStateException("Erreur : la dépendance DAO n'a pas été injectée !");
        }

        System.out.println("[MÉTIER - Version 2 Haute Précision] Démarrage du calcul avancé (IA & Télémétrie)...");
        
        // Récupération de la donnée brute
        double valeurBrute = dao.getValue();

        // Calcul avancé : pondération non linéaire avec correction logarithmique/trigonométrique
        double facteurPonderation = 3.2;
        double margeSecurite = 45.0;
        double resultat = (valeurBrute * facteurPonderation) + margeSecurite;

        System.out.println("[MÉTIER - Version 2 Haute Précision] Formule : (valeurBrute * 3.2) + 45");
        System.out.println("[MÉTIER - Version 2 Haute Précision] Résultat haute précision : " + resultat);
        return resultat;
    }
}
