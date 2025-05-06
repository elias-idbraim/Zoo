package zoo.modele;

public class Oiseau extends Animal {
    private String typeOiseau;

    public Oiseau(String nom, int age, String typeOiseau) {
        super(nom, age, "Oiseau");
        this.typeOiseau = typeOiseau;
    }

    public String getTypeOiseau() {
        return typeOiseau;
    }

    @Override
    public String faireDuBruit() {
        return "L'oiseau " + getNom() + " chante mélodieusement !";
    }

    @Override
    public String seDeplacer() {
        return "L'oiseau " + getNom() + " vole gracieusement dans les airs.";
    }

    @Override
    public String manger() {
        return "L'oiseau " + getNom() + " picore des graines.";
    }

    @Override
    public String faireSpectacle() {
        return "L'oiseau " + getNom() + " exécute une série d'acrobaties aériennes impressionnantes !";
    }

    @Override
    public String toString() {
        return super.toString() + " - Type: " + typeOiseau;
    }
}