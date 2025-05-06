package zoo.view;

import zoo.modele.*;

import java.util.List;

public class zooVue {

    public void afficherBienvenue(String nomZoo) {
        System.out.println("      BIENVENUE DANS LE SYSTÈME DE GESTION       ");
        System.out.println("                   DU " + nomZoo.toUpperCase() + "                  ");
        System.out.println();
    }

    public void afficherAuRevoir() {
        System.out.println("\nMerci d'avoir utilisé le système de gestion du zoo. À bientôt !");
    }

    public void afficherMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gérer les animaux");
        System.out.println("2. Gérer les soigneurs");
        System.out.println("3. Gérer les visiteurs");
        System.out.println("4. Gérer les enclos");
        System.out.println("5. Gérer les spectacles");
        System.out.println("6. Afficher les statistiques du zoo");
        System.out.println("7. Passer au jour suivant");
        System.out.println("0. Quitter");
    }

    public void afficherMenuAnimaux() {
        System.out.println("\n=== GESTION DES ANIMAUX ===");
        System.out.println("1. Afficher tous les animaux");
        System.out.println("2. Ajouter un animal");
        System.out.println("3. Supprimer un animal");
        System.out.println("4. Interagir avec un animal");
        System.out.println("0. Retour au menu principal");
    }

    public void afficherMenuTypeAnimal() {
        System.out.println("\nType d'animal:");
        System.out.println("1. Lion");
        System.out.println("2. Oiseau");
        System.out.println("3. Serpent");
    }

    public void afficherMenuInteractionAnimal() {
        System.out.println("\nAction:");
        System.out.println("1. Faire du bruit");
        System.out.println("2. Se déplacer");
        System.out.println("3. Manger");
        System.out.println("4. Faire réagir un visiteur");
    }

    public void afficherMenuSoigneurs() {
        System.out.println("\n=== GESTION DES SOIGNEURS ===");
        System.out.println("1. Afficher tous les soigneurs");
        System.out.println("2. Ajouter un soigneur");
        System.out.println("3. Diagnostiquer un animal");
        System.out.println("4. Soigner un animal");
        System.out.println("5. Nettoyer un enclos");
        System.out.println("0. Retour au menu principal");
    }

    public void afficherMenuSpecialite() {
        System.out.println("\nSpécialité:");
        System.out.println("1. Lion");
        System.out.println("2. Oiseau");
        System.out.println("3. Serpent");
    }

    public void afficherMenuVisiteurs() {
        System.out.println("\n=== GESTION DES VISITEURS ===");
        System.out.println("1. Afficher tous les visiteurs");
        System.out.println("2. Ajouter un visiteur");
        System.out.println("3. Faire réagir un visiteur");
        System.out.println("0. Retour au menu principal");
    }

    public void afficherMenuEnclos() {
        System.out.println("\n=== GESTION DES ENCLOS ===");
        System.out.println("1. Afficher tous les enclos");
        System.out.println("2. Ajouter un enclos");
        System.out.println("3. Ajouter un animal à un enclos");
        System.out.println("4. Retirer un animal d'un enclos");
        System.out.println("0. Retour au menu principal");
    }

    public void afficherMenuSpectacles() {
        System.out.println("\n=== GESTION DES SPECTACLES ===");
        System.out.println("1. Afficher tous les spectacles");
        System.out.println("2. Créer un spectacle");
        System.out.println("3. Démarrer un spectacle");
        System.out.println("0. Retour au menu principal");
        System.out.println("=============================");
    }

    public void afficherListeAnimaux(List<Animal> animaux) {
        System.out.println("\n=== LISTE DES ANIMAUX ===");
        int i = 1;
        for (Animal animal : animaux) {
            System.out.println(i + ". " + animal);
            i++;
        }
        System.out.println("========================");
    }

    public void afficherListeSoigneurs(List<Soigneur> soigneurs) {
        System.out.println("\n=== LISTE DES SOIGNEURS ===");
        int i = 1;
        for (Soigneur soigneur : soigneurs) {
            System.out.println(i + ". " + soigneur);
            i++;
        }
        System.out.println("==========================");
    }

    public void afficherListeVisiteurs(List<Visiteur> visiteurs) {
        System.out.println("\n=== LISTE DES VISITEURS ===");
        int i = 1;
        for (Visiteur visiteur : visiteurs) {
            System.out.println(i + ". " + visiteur);
            i++;
        }
        System.out.println("==========================");
    }

    public void afficherListeEnclos(List<Enclos> enclos) {
        System.out.println("\n=== LISTE DES ENCLOS ===");
        int i = 1;
        for (Enclos enclo : enclos) {
            System.out.println(i + ". " + enclo);
            i++;
        }
        System.out.println("========================");
    }

    public void afficherListeSpectacles(List<Spectacle> spectacles) {
        System.out.println("\n=== LISTE DES SPECTACLES ===");
        int i = 1;
        for (Spectacle spectacle : spectacles) {
            System.out.println(i + ". " + spectacle);
            i++;
        }
        System.out.println("===========================");
    }

    public void afficherMessage(String message) {
        System.out.println("\n" + message);
    }

    public void afficherErreur(String message) {
        System.out.println("\nERREUR: " + message);
    }
}