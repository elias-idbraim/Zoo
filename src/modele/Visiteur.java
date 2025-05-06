package zoo.modele;

import java.util.Random;

public abstract class Visiteur extends Personne {
    private double prixBillet;

    public Visiteur(String nom, String prenom, int age, double prixBillet) {
        super(nom, prenom, age);
        this.prixBillet = prixBillet;
    }

    public double getPrixBillet() {
        return prixBillet;
    }

    public String reagir(Animal animal) {
        String[] reactions = {
                "Wow, c'est impressionnant !",
                "Oh, comme il est mignon !",
                "Incroyable !",
                "Je n'avais jamais vu ça avant !",
                "Magnifique créature !"
        };

        Random random = new Random();
        int index = random.nextInt(reactions.length);

        return getPrenom() + " dit: \"" + reactions[index] + "\" en voyant " + animal.getNom();
    }

    public String reagirAuSpectacle(String performance) {
        String[] reactions = {
                "Applaudit avec enthousiasme !",
                "Pousse des cris d'admiration !",
                "Prend des photos !",
                "Reste bouche bée !",
                "Sourit et applaudit !"
        };

        Random random = new Random();
        int index = random.nextInt(reactions.length);

        return getPrenom() + " " + reactions[index];
    }
}