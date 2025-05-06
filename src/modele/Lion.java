package zoo.modele;

public class Lion extends Animal {

    public Lion(String nom, int age) {
        super(nom, age, "Lion");
    }

    @Override
    public String faireDuBruit() {
        return "Le lion " + getNom() + " rugit puissamment !";
    }

    @Override
    public String seDeplacer() {
        return "Le lion " + getNom() + " se déplace majestueusement dans la savane.";
    }

    @Override
    public String manger() {
        return "Le lion " + getNom() + " dévore un morceau de viande.";
    }

    @Override
    public String faireSpectacle() {
        return "Le lion " + getNom() + " saute à travers un cerceau enflammé !";
    }
}