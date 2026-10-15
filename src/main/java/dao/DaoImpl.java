package dao;

/**
 * Implémentation concrète standard de l'interface IDao.
 * Simule la récupération de données depuis une base de données relationnelle.
 */
public class DaoImpl implements IDao {

    @Override
    public double getValue() {
        System.out.println("--------------------------------------------------");
        System.out.println("[DAO - Version Base de Données]");
        System.out.println("-> Connexion au serveur SQL / PostgreSQL...");
        System.out.println("-> Requête : SELECT valeur_mesure FROM capteurs WHERE active = true LIMIT 1;");
        
        // Simule une donnée de télémétrie (ex: température ou consommation de base)
        double data = 40.0;
        
        System.out.println("-> Valeur extraite de la BD : " + data);
        System.out.println("--------------------------------------------------");
        return data;
    }
}
