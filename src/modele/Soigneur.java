package zoo.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Soigneur extends Personne {
    private String specialite;
    private List<Animal> animauxSoignes;
    private boolean occupeAujourdHui;

    public Soigneur(String nom, String prenom, int age, String specialite) {
        super(nom, prenom, age);
        this.specialite = specialite;
        this.animauxSoignes = new ArrayList<>();
        this.occupeAujourdHui = false;
    }

    public String getSpecialite() {
        return specialite;
    }

    public boolean estOccupeAujourdHui() {
        return occupeAujourdHui;
    }

    public void setOccupeAujourdHui(boolean occupe) {
        this.occupeAujourdHui = occupe;
    }

    public void ajouterAnimal(Animal animal) {
        if (peutSoigner(animal)) {
            animauxSoignes.add(animal);
            System.out.println(getPrenom() + " est maintenant responsable de " + animal.getNom());
        } else {
            System.out.println(getPrenom() + " ne peut pas s'occuper de cet animal car sa spécialité est " + specialite);
        }
    }

    public boolean peutSoigner(Animal animal) {
        return animal.getEspece().equalsIgnoreCase(specialite);
    }

    public String diagnostiquer(Animal animal) {
        if (!peutSoigner(animal)) {
            return getPrenom() + " ne peut pas diagnostiquer cet animal car sa spécialité est " + specialite;
        }

        if (occupeAujourdHui) {
            return getPrenom() + " est occupé aujourd'hui et ne peut pas diagnostiquer d'animal.";
        }

        Random random = new Random();
        boolean malade = random.nextBoolean();

        animal.setEstMalade(malade);

        if (malade) {
            return getPrenom() + " a diagnostiqué que " + animal.getNom() + " est malade.";
        } else {
            return getPrenom() + " a diagnostiqué que " + animal.getNom() + " est en bonne santé.";
        }
    }

    public String soigner(Animal animal) {
        if (!peutSoigner(animal)) {
            return getPrenom() + " ne peut pas soigner cet animal car sa spécialité est " + specialite;
        }

        if (occupeAujourdHui) {
            return getPrenom() + " est occupé aujourd'hui et ne peut pas soigner d'animal.";
        }

        if (!animal.estMalade()) {
            return animal.getNom() + " n'est pas malade et n'a pas besoin d'être soigné.";
        }

        animal.setEstMalade(false);
        return getPrenom() + " a soigné " + animal.getNom() + " qui est maintenant en bonne santé.";
    }

    public String nettoyerEnclos(Enclos enclos) {
        if (occupeAujourdHui) {
            return getPrenom() + " est déjà occupé aujourd'hui et ne peut pas nettoyer d'enclos.";
        }

        occupeAujourdHui = true;
        enclos.nettoyer();
        return getPrenom() + " a nettoyé l'enclos " + enclos.getNom() + ".";
    }

    public List<Animal> getAnimauxSoignes() {
        return new ArrayList<>(animauxSoignes);
    }

    @Override
    public String toString() {
        return super.toString() + " - Spécialité: " + specialite + " - Animaux: " + animauxSoignes.size();
    }
}