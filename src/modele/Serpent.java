package zoo.modele;

public class Serpent extends Animal {
    private boolean estVenimeux;

    public Serpent(String nom, int age, boolean estVenimeux) {
        super(nom, age, "Serpent");
        this.estVenimeux = estVenimeux;
    }

    public boolean estVenimeux() {
        return estVenimeux;
    }

    @Override
    public String faireDuBruit() {
        return "Le serpent " + getNom() + " siffle doucement !";
    }

    @Override
    public String seDeplacer() {
        return "Le serpent " + getNom() + " rampe silencieusement sur le sol.";
    }

    @Override
    public String manger() {
        return "Le serpent " + getNom() + " avale sa proie entière.";
    }

    @Override
    public String faireSpectacle() {
        return "Le serpent " + getNom() + " s'enroule autour du bras du soigneur et se dresse !";
    }

    @Override
    public String toString() {
        return super.toString() + (estVenimeux ? " (Venimeux)" : " (Non venimeux)");
    }
}