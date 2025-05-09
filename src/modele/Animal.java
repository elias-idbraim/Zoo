package zoo.modele;

public abstract class Animal {
    private String nom;
    private int age;
    private boolean estMalade;
    private String espece;

    public Animal(String nom, int age, String espece) {
        this.nom = nom;
        this.age = age;
        this.espece = espece;
        this.estMalade = false;
    }

    public abstract String faireDuBruit();
    public abstract String seDeplacer();
    public abstract String manger();
    public abstract String faireSpectacle();

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean estMalade() {
        return estMalade;
    }

    public void setEstMalade(boolean estMalade) {
        this.estMalade = estMalade;
    }

    public String getEspece() {
        return espece;
    }

    @Override
    public String toString() {
        return espece + " - " + nom + " (" + age + " ans)";
    }
}