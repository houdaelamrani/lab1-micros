package dao;

/**
 * DEUXIÈME EXTENSION de l'interface IDao.
 * Simule la récupération de données en temps réel via un Web Service REST / Capteur IoT Cloud.
 * Illustre le principe Ouvert/Fermé (OCP) : extension sans modification du code existant.
 */
public class DaoImpl2 implements IDao {

    @Override
    public double getValue() {
        System.out.println("--------------------------------------------------");
        System.out.println("[DAO - Extension 2 : Web Service REST / Capteur IoT]");
        System.out.println("-> Appel HTTP GET https://api.iot-cloud.net/v1/sensors/live-metric");
        System.out.println("-> Code HTTP 200 OK - Flux JSON reçu avec succès");
        
        // Simule une donnée reçue en temps réel depuis le capteur IoT
        double data = 85.5;
        
        System.out.println("-> Valeur télémétrique temps réel : " + data);
        System.out.println("--------------------------------------------------");
        return data;
    }
}
