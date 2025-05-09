package zoo.modele;

import java.util.ArrayList;
import java.util.List;

public class Enclos {
    private String nom;
    private int capaciteMax;
    private int niveauProprete; // 0 = propre, plus le nombre est élevé, plus c'est sale
    private List<Animal> animaux;

    public Enclos(String nom, int capaciteMax) {
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.niveauProprete = 0;
        this.animaux = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public int getNiveauProprete() {
        return niveauProprete;
    }

    public List<Animal> getAnimaux() {
        return new ArrayList<>(animaux);
    }

    public boolean ajouterAnimal(Animal animal) {
        if (animaux.size() >= capaciteMax) {
            return false;
        }

        if (animaux.isEmpty() || animaux.get(0).getEspece().equals(animal.getEspece())) {
            animaux.add(animal);
            return true;
        }

        return false;
    }

    public boolean retirerAnimal(Animal animal) {
        return animaux.remove(animal);
    }

    public void nouveauJour() {
        niveauProprete++;
    }

    public void nettoyer() {
        niveauProprete = 0;
    }

    public boolean aNettoyer() {
        return niveauProprete >= 3;
    }

    public int getNombreAnimaux() {
        return animaux.size();
    }

    public String getEtatProprete() {
        switch (niveauProprete) {
            case 0:
                return "Propre";
            case 1:
                return "un peu sale";
            case 2:
                return "Sale";
            default:
                return "Très sale ";
        }
    }

    @Override
    public String toString() {
        return "Enclos " + nom + " - Capacité: " + animaux.size() + "/" + capaciteMax +
                " - État: " + getEtatProprete() + " - Type: " +
                (animaux.isEmpty() ? "Vide" : animaux.get(0).getEspece());
    }
}