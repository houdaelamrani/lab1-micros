package metier;

import dao.IDao;

/**
 * Implémentation standard du microservice de calcul métier (Version 1).
 * Respecte le principe d'inversion des dépendances (DIP) : dépend de l'abstraction IDao.
 */
public class MetierImpl implements IMetier {

    // Couplage faible : référence uniquement vers l'interface
    private IDao dao;

    public MetierImpl() {
    }

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    /**
     * Méthode d'injection de la dépendance DAO (Setter Injection).
     *
     * @param dao L'instance de IDao injectée dynamiquement.
     */
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

        System.out.println("[MÉTIER - Version 1 Standard] Démarrage du calcul prédictif...");
        
        // Récupération de la donnée brute via l'abstraction IDao
        double valeurBrute = dao.getValue();

        // Calcul métier : application d'un coefficient d'amplification (x 2.5) et ajustement (+ 15)
        double resultat = (valeurBrute * 2.5) + 15.0;

        System.out.println("[MÉTIER - Version 1 Standard] Formule : (valeurBrute * 2.5) + 15");
        System.out.println("[MÉTIER - Version 1 Standard] Résultat intermédiaire : " + resultat);
        return resultat;
    }
}