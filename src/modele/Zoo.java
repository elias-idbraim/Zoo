package zoo.modele;

import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private String nom;
    private List<Animal> animaux;
    private List<Soigneur> soigneurs;
    private List<Visiteur> visiteurs;
    private List<Enclos> enclos;
    private List<Spectacle> spectacles;
    private double caisse;
    private int jourActuel;

    public Zoo(String nom) {
        this.nom = nom;
        this.animaux = new ArrayList<>();
        this.soigneurs = new ArrayList<>();
        this.visiteurs = new ArrayList<>();
        this.enclos = new ArrayList<>();
        this.spectacles = new ArrayList<>();
        this.caisse = 0.0;
        this.jourActuel = 1;
    }

    public String getNom() {
        return nom;
    }

    public int getJourActuel() {
        return jourActuel;
    }

    public void nouveauJour() {
        jourActuel++;

        // Réinitialiser l'occupation des soigneurs
        for (Soigneur soigneur : soigneurs) {
            soigneur.setOccupeAujourdHui(false);
        }

        // Dégrader la propreté des enclos
        for (Enclos enclos : enclos) {
            enclos.nouveauJour();
        }

        System.out.println("=== JOUR " + jourActuel + " ===");
        System.out.println("Tous les enclos ont été dégradés d'un niveau de propreté.");

        // Vérifier les enclos à nettoyer
        List<Enclos> enclosANettoyer = new ArrayList<>();
        for (Enclos enclos : enclos) {
            if (enclos.aNettoyer()) {
                enclosANettoyer.add(enclos);
            }
        }

        if (!enclosANettoyer.isEmpty()) {
            System.out.println("Enclos à nettoyer: ");
            for (Enclos enclos : enclosANettoyer) {
                System.out.println("- " + enclos.getNom());
            }
        }
    }

    public void ajouterAnimal(Animal animal) {
        animaux.add(animal);
    }

    public boolean retirerAnimal(Animal animal) {
        // Retirer l'animal des enclos
        for (Enclos enclos : enclos) {
            enclos.retirerAnimal(animal);
        }

        // Retirer l'animal des spectacles
        for (Spectacle spectacle : spectacles) {
            spectacle.ajouterAnimal(animal); // Cette méthode devrait être modifiée pour permettre le retrait
        }

        return animaux.remove(animal);
    }

    public void ajouterSoigneur(Soigneur soigneur) {
        soigneurs.add(soigneur);
    }

    public void ajouterVisiteur(Visiteur visiteur) {
        visiteurs.add(visiteur);
        caisse += visiteur.getPrixBillet();
    }

    public void ajouterEnclos(Enclos enclos) {
        this.enclos.add(enclos);
    }

    public void ajouterSpectacle(Spectacle spectacle) {
        spectacles.add(spectacle);
    }

    public List<Animal> getAnimaux() {
        return new ArrayList<>(animaux);
    }

    public List<Soigneur> getSoigneurs() {
        return new ArrayList<>(soigneurs);
    }

    public List<Visiteur> getVisiteurs() {
        return new ArrayList<>(visiteurs);
    }

    public List<Enclos> getEnclos() {
        return new ArrayList<>(enclos);
    }

    public List<Spectacle> getSpectacles() {
        return new ArrayList<>(spectacles);
    }

    public double getCaisse() {
        return caisse;
    }

    public String getStatistiques() {
        StringBuilder stats = new StringBuilder();
        stats.append("=== STATISTIQUES DU ZOO ===\n");
        stats.append("Nom: " + nom + "\n");
        stats.append("Jour actuel: " + jourActuel + "\n");
        stats.append("Nombre d'animaux: " + animaux.size() + "\n");
        stats.append("Nombre de soigneurs: " + soigneurs.size() + "\n");
        stats.append("Nombre de visiteurs: " + visiteurs.size() + "\n");
        stats.append("Nombre d'enclos: " + enclos.size() + "\n");
        stats.append("Nombre de spectacles: " + spectacles.size() + "\n");
        stats.append("Caisse: " + caisse + "€\n");

        return stats.toString();
    }
}