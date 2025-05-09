package zoo.modele;

import java.util.ArrayList;
import java.util.List;

public class Spectacle {
    private String nom;
    private Soigneur soigneur;
    private List<Animal> animaux;
    private List<Visiteur> spectateurs;

    public Spectacle(String nom, Soigneur soigneur) {
        this.nom = nom;
        this.soigneur = soigneur;
        this.animaux = new ArrayList<>();
        this.spectateurs = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public Soigneur getSoigneur() {
        return soigneur;
    }

    public void ajouterAnimal(Animal animal) {
        if (soigneur.peutSoigner(animal)) {
            animaux.add(animal);
        } else {
            System.out.println("Le soigneur " + soigneur.getPrenom() + " ne peut pas présenter cet animal dans le spectacle.");
        }
    }

    public void ajouterSpectateur(Visiteur visiteur) {
        spectateurs.add(visiteur);
    }

    public String demarrer() {
        if (animaux.isEmpty()) {
            return "Impossible de démarrer le spectacle sans animaux.";
        }

        if (spectateurs.isEmpty()) {
            return "Impossible de démarrer le spectacle sans spectateurs.";
        }

        if (soigneur.estOccupeAujourdHui()) {
            return "Le soigneur " + soigneur.getPrenom() + " est occupé aujourd'hui et ne peut pas présenter le spectacle.";
        }

        StringBuilder resultat = new StringBuilder();
        resultat.append("=== DÉBUT DU SPECTACLE: " + nom + " ===\n");
        resultat.append("Présenté par " + soigneur.getPrenom() + " " + soigneur.getNom() + "\n");
        resultat.append("Nombre de spectateurs: " + spectateurs.size() + "\n\n");

        for (Animal animal : animaux) {
            resultat.append("Le soigneur présente " + animal.getNom() + " et son numéro spécial.\n");
            String performance = animal.faireSpectacle();
            resultat.append(performance + "\n");

            for (Visiteur spectateur : spectateurs) {
                resultat.append(spectateur.reagirAuSpectacle(performance) + "\n");
            }
            resultat.append("\n");
        }

        resultat.append("=== FIN DU SPECTACLE ===");
        return resultat.toString();
    }

    @Override
    public String toString() {
        return "Spectacle: " + nom + " - Présenté par: " + soigneur.getPrenom() +
                " - Animaux: " + animaux.size() + " - Spectateurs: " + spectateurs.size();
    }
}