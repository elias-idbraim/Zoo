package zoo.modele;

public class Enfant extends Visiteur {
    private static final double PRIX_BILLET = 10.0;

    public Enfant(String nom, String prenom, int age) {
        super(nom, prenom, age, PRIX_BILLET);
        if (age >= 16) {
            throw new IllegalArgumentException("Un enfant doit avoir moins de 16 ans");
        }
    }

    @Override
    public String reagir(Animal animal) {
        String reaction = super.reagir(animal);
        return reaction + " et saute d'excitation !";
    }

    @Override
    public String toString() {
        return super.toString() + " - Enfant - Billet: " + getPrixBillet() + "€";
    }
}