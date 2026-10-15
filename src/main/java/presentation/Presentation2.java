package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * Présentation 2 : Conteneur IoC & Moteur de Chargement Dynamique (Couplage Faible).
 * Utilise la réflexion Java et un fichier de configuration externe (config.txt).
 * Respecte le principe OCP : on change de comportement sans recompiler le code !
 */
public class Presentation2 {

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("    DÉMONSTRATION 2 : CHARGEMENT DYNAMIQUE (IoC)   ");
        System.out.println("         (Couplage Faible & Réflexion Java)       ");
        System.out.println("==================================================");

        try {
            // 1. Chargement des noms de classes depuis config.txt
            String[] classNames = loadClassNames("config.txt");
            System.out.println("[IoC] Lecture de la configuration :");
            System.out.println("      Classe DAO    : " + classNames[0]);
            System.out.println("      Classe Métier : " + classNames[1]);
            System.out.println("--------------------------------------------------");

            // 2. Instanciation dynamique du composant DAO
            IDao dao = (IDao) instantiateClass(classNames[0]);
            System.out.println("[IoC] DAO instancié avec succès (" + dao.getClass().getName() + ")");

            // 3. Instanciation dynamique du composant Métier
            IMetier metier = (IMetier) instantiateClass(classNames[1]);
            System.out.println("[IoC] Métier instancié avec succès (" + metier.getClass().getName() + ")");

            // 4. Injection dynamique de la dépendance DAO dans le Métier via réflexion
            injectDependency(metier, "setDao", dao);
            System.out.println("[IoC] Dépendance injectée avec succès via setDao(IDao)");

            // 5. Exécution du calcul métier
            System.out.println("--------------------------------------------------");
            double resultat = metier.calcul();
            System.out.println("==================================================");
            System.out.println(">>> Résultat final (Dynamique) = " + resultat);
            System.out.println("==================================================");

        } catch (Exception e) {
            System.err.println("[ERREUR] Échec du chargement dynamique : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Lit les classes configurées dans config.txt (racine ou src/).
     */
    private static String[] loadClassNames(String fileName) throws IOException {
        String[] classNames = new String[2];
        File file = new File(fileName);
        if (!file.exists()) {
            file = new File("src/" + fileName);
        }
        if (!file.exists()) {
            throw new IOException("Le fichier de configuration '" + fileName + "' est introuvable !");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int count = 0;
            String line;
            while ((line = reader.readLine()) != null && count < 2) {
                line = line.trim();
                // Ignorer les commentaires éventuels (# ou //) et les lignes vides
                if (!line.isEmpty() && !line.startsWith("#") && !line.startsWith("//")) {
                    classNames[count++] = line;
                }
            }
            if (count < 2) {
                throw new IOException("Le fichier de configuration doit contenir au moins 2 noms de classes valides.");
            }
        }
        return classNames;
    }

    /**
     * Instancie une classe dynamiquement à partir de son nom qualifié complet.
     */
    private static Object instantiateClass(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        return constructor.newInstance();
    }

    /**
     * Injecte une dépendance dans un objet cible via introspection (méthode setter).
     */
    private static void injectDependency(Object target, String setterName, IDao dependency) throws Exception {
        Class<?> targetClass = target.getClass();
        Method setterMethod = targetClass.getMethod(setterName, IDao.class);
        setterMethod.invoke(target, dependency);
    }
}
