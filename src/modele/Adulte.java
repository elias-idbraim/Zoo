package zoo.modele;

public class Adulte extends Visiteur {
    private static final double PRIX_BILLET = 20.0;

    public Adulte(String nom, String prenom, int age) {
        super(nom, prenom, age, PRIX_BILLET);
        if (age < 16) {
            throw new IllegalArgumentException("Un adulte doit avoir au moins 16 ans");
        }
    }

    @Override
    public String toString() {
        return super.toString() + " - Adulte - Billet: " + getPrixBillet() + "€";
    }
}