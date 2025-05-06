package zoo.gestion;

import zoo.modele.*;

import java.util.List;
import java.util.Scanner;

public class zooGestion {
    private Zoo zoo;
    private zoo.view.zooVue view;
    private Scanner scanner;

    public zooGestion() {
        this.zoo = new Zoo("Zoooo");
        this.view = new zoo.view.zooVue();
        this.scanner = new Scanner(System.in);
        initialiserZoo();
    }

    private void initialiserZoo() {
        Enclos enclosLions = new Enclos("Savane", 5);
        Enclos enclosOiseaux = new Enclos("Volière", 10);
        Enclos enclosSerpents = new Enclos("Terrarium", 8);

        zoo.ajouterEnclos(enclosLions);
        zoo.ajouterEnclos(enclosOiseaux);
        zoo.ajouterEnclos(enclosSerpents);

        Lion mufasa = new Lion("Mufasa", 5);
        Lion rico = new Lion("Rico", 4);
        Oiseau reo = new Oiseau("Reo", 3, "Perroquet");
        Oiseau kakuzu = new Oiseau("Kakuzu", 6, "Calao");
        Serpent shaala = new Serpent("Shaala", 8, false);
        Serpent nagi = new Serpent("Nagi", 10, true);

        zoo.ajouterAnimal(Mufasa);
        zoo.ajouterAnimal(Rico);
        zoo.ajouterAnimal(Reo);
        zoo.ajouterAnimal(Kakuzu);
        zoo.ajouterAnimal(Shaala);
        zoo.ajouterAnimal(Nagi);

        enclosLions.ajouterAnimal(mufasa);
        enclosLions.ajouterAnimal(rico);
        enclosOiseaux.ajouterAnimal(reo);
        enclosOiseaux.ajouterAnimal(kakuzu);
        enclosSerpents.ajouterAnimal(shaala);
        enclosSerpents.ajouterAnimal(nagi);


        Soigneur rick = new Soigneur("Alment", "Rick", 35, "Lion");
        Soigneur juliette = new Soigneur("Papte", "Juliette", 28, "Oiseau");
        Soigneur tim = new Soigneur("Legrand", "Tim", 42, "Serpent");

        zoo.ajouterSoigneur(rick);
        zoo.ajouterSoigneur(juliette);
        zoo.ajouterSoigneur(tim);

        rick.ajouterAnimal(mufasa);
        rick.ajouterAnimal(rico);
        juliette.ajouterAnimal(reo);
        juliette.ajouterAnimal(kakuzu);
        tim.ajouterAnimal(shaala);
        tim.ajouterAnimal(nagi);

        Adulte ali = new Adulte("White", "Ali", 30);
        Adulte alise = new Adulte("Rain", "Alise", 25);
        Enfant pierre = new Enfant("Marceau", "Pierre", 10);
        Enfant monica = new Enfant("Watson", "Monica", 8);

        zoo.ajouterVisiteur(ali);
        zoo.ajouterVisiteur(alise);
        zoo.ajouterVisiteur(pierre);
        zoo.ajouterVisiteur(monica);

        Spectacle spectacleLions = new Spectacle("Le Roi Lion", rick);
        spectacleLions.ajouterAnimal(mufasa);
        spectacleLions.ajouterAnimal(rico);
        spectacleLions.ajouterSpectateur(ali);
        spectacleLions.ajouterSpectateur(alise);
        spectacleLions.ajouterSpectateur(pierre);
        spectacleLions.ajouterSpectateur(monica);

        zoo.ajouterSpectacle(spectacleLions);
    }

    public void start() {
        boolean continuer = true;

        view.afficherBienvenue(zoo.getNom());

        while (continuer) {
            view.afficherMenuPrincipal();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    gererAnimaux();
                    break;
                case 2:
                    gererSoigneurs();
                    break;
                case 3:
                    gererVisiteurs();
                    break;
                case 4:
                    gererEnclos();
                    break;
                case 5:
                    gererSpectacles();
                    break;
                case 6:
                    view.afficherMessage(zoo.getStatistiques());
                    break;
                case 7:
                    zoo.nouveauJour();
                    break;
                case 0:
                    continuer = false;
                    view.afficherAuRevoir();
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }

        scanner.close();
    }

    private void gererAnimaux() {
        boolean retour = false;

        while (!retour) {
            view.afficherMenuAnimaux();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    afficherAnimaux();
                    break;
                case 2:
                    ajouterAnimal();
                    break;
                case 3:
                    supprimerAnimal();
                    break;
                case 4:
                    interagirAvecAnimal();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherAnimaux() {
        List<Animal> animaux = zoo.getAnimaux();

        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal dans le zoo.");
            return;
        }

        view.afficherListeAnimaux(animaux);
    }

    private void ajouterAnimal() {
        view.afficherMenuTypeAnimal();
        int typeAnimal = lireEntier("Type d'animal: ");

        String nom = lireChaine("Nom: ");
        int age = lireEntier("Âge: ");

        Animal nouvelAnimal = null;

        switch (typeAnimal) {
            case 1:
                nouvelAnimal = new Lion(nom, age);
                break;
            case 2:
                String typeOiseau = lireChaine("Type d'oiseau: ");
                nouvelAnimal = new Oiseau(nom, age, typeOiseau);
                break;
            case 3:
                boolean estVenimeux = lireBoolean("Est venimeux (oui/non): ");
                nouvelAnimal = new Serpent(nom, age, estVenimeux);
                break;
            default:
                view.afficherErreur("Type d'animal invalide.");
                return;
        }

        zoo.ajouterAnimal(nouvelAnimal);

        // Ajouter l'animal à un enclos
        List<Enclos> enclosCompatibles = zoo.getEnclos();
        if (!enclosCompatibles.isEmpty()) {
            view.afficherListeEnclos(enclosCompatibles);
            int choixEnclos = lireEntier("Choisir un enclos (0 pour aucun): ");

            if (choixEnclos > 0 && choixEnclos <= enclosCompatibles.size()) {
                Enclos enclosChoisi = enclosCompatibles.get(choixEnclos - 1);
                boolean ajoutReussi = enclosChoisi.ajouterAnimal(nouvelAnimal);

                if (ajoutReussi) {
                    view.afficherMessage("Animal ajouté à l'enclos " + enclosChoisi.getNom() + ".");
                } else {
                    view.afficherErreur("Impossible d'ajouter l'animal à cet enclos (plein ou incompatible).");
                }
            }
        }

        List<Soigneur> soigneursCompatibles = zoo.getSoigneurs();
        if (!soigneursCompatibles.isEmpty()) {
            view.afficherListeSoigneurs(soigneursCompatibles);
            int choixSoigneur = lireEntier("Choisir un soigneur (0 pour aucun): ");

            if (choixSoigneur > 0 && choixSoigneur <= soigneursCompatibles.size()) {
                Soigneur soigneurChoisi = soigneursCompatibles.get(choixSoigneur - 1);
                soigneurChoisi.ajouterAnimal(nouvelAnimal);
            }
        }

        view.afficherMessage("Animal ajouté avec succès: " + nouvelAnimal);
    }

    private void supprimerAnimal() {
        List<Animal> animaux = zoo.getAnimaux();

        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal à supprimer.");
            return;
        }

        view.afficherListeAnimaux(animaux);
        int choix = lireEntier("Choisir un animal à supprimer (0 pour annuler): ");

        if (choix > 0 && choix <= animaux.size()) {
            Animal animalASupprimer = animaux.get(choix - 1);
            boolean supprime = zoo.retirerAnimal(animalASupprimer);

            if (supprime) {
                view.afficherMessage("Animal supprimé avec succès: " + animalASupprimer);
            } else {
                view.afficherErreur("Erreur lors de la suppression de l'animal.");
            }
        }
    }

    private void interagirAvecAnimal() {
        List<Animal> animaux = zoo.getAnimaux();

        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal avec qui interagir.");
            return;
        }

        view.afficherListeAnimaux(animaux);
        int choixAnimal = lireEntier("Choisir un animal (0 pour annuler): ");

        if (choixAnimal <= 0 || choixAnimal > animaux.size()) {
            return;
        }

        Animal animalChoisi = animaux.get(choixAnimal - 1);

        view.afficherMenuInteractionAnimal();
        int choixAction = lireEntier("Choisir une action: ");

        switch (choixAction) {
            case 1:
                view.afficherMessage(animalChoisi.faireDuBruit());
                break;
            case 2:
                view.afficherMessage(animalChoisi.seDeplacer());
                break;
            case 3:
                view.afficherMessage(animalChoisi.manger());
                break;
            case 4:
                // Faire réagir un visiteur
                List<Visiteur> visiteurs = zoo.getVisiteurs();
                if (visiteurs.isEmpty()) {
                    view.afficherMessage("Aucun visiteur pour réagir.");
                    return;
                }

                view.afficherListeVisiteurs(visiteurs);
                int choixVisiteur = lireEntier("Choisir un visiteur: ");

                if (choixVisiteur > 0 && choixVisiteur <= visiteurs.size()) {
                    Visiteur visiteurChoisi = visiteurs.get(choixVisiteur - 1);
                    view.afficherMessage(visiteurChoisi.reagir(animalChoisi));
                }
                break;
            default:
                view.afficherErreur("Action invalide.");
        }
    }

    private void gererSoigneurs() {
        boolean retour = false;

        while (!retour) {
            view.afficherMenuSoigneurs();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    afficherSoigneurs();
                    break;
                case 2:
                    ajouterSoigneur();
                    break;
                case 3:
                    diagnostiquerAnimal();
                    break;
                case 4:
                    soignerAnimal();
                    break;
                case 5:
                    nettoyerEnclos();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherSoigneurs() {
        List<Soigneur> soigneurs = zoo.getSoigneurs();

        if (soigneurs.isEmpty()) {
            view.afficherMessage("Aucun soigneur dans le zoo.");
            return;
        }

        view.afficherListeSoigneurs(soigneurs);
    }

    private void ajouterSoigneur() {
        String nom = lireChaine("Nom: ");
        String prenom = lireChaine("Prénom: ");
        int age = lireEntier("Âge: ");

        view.afficherMenuSpecialite();
        int choixSpecialite = lireEntier("Spécialité: ");

        String specialite;
        switch (choixSpecialite) {
            case 1:
                specialite = "Lion";
                break;
            case 2:
                specialite = "Oiseau";
                break;
            case 3:
                specialite = "Serpent";
                break;
            default:
                view.afficherErreur("Spécialité invalide.");
                return;
        }

        Soigneur nouveauSoigneur = new Soigneur(nom, prenom, age, specialite);
        zoo.ajouterSoigneur(nouveauSoigneur);

        view.afficherMessage("Soigneur ajouté avec succès: " + nouveauSoigneur);
    }

    private void diagnostiquerAnimal() {
        List<Soigneur> soigneurs = zoo.getSoigneurs();

        if (soigneurs.isEmpty()) {
            view.afficherMessage("Aucun soigneur disponible.");
            return;
        }

        view.afficherListeSoigneurs(soigneurs);
        int choixSoigneur = lireEntier("Choisir un soigneur: ");

        if (choixSoigneur <= 0 || choixSoigneur > soigneurs.size()) {
            return;
        }

        Soigneur soigneurChoisi = soigneurs.get(choixSoigneur - 1);

        List<Animal> animaux = zoo.getAnimaux();
        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal à diagnostiquer.");
            return;
        }

        view.afficherListeAnimaux(animaux);
        int choixAnimal = lireEntier("Choisir un animal: ");

        if (choixAnimal <= 0 || choixAnimal > animaux.size()) {
            return;
        }

        Animal animalChoisi = animaux.get(choixAnimal - 1);

        String resultat = soigneurChoisi.diagnostiquer(animalChoisi);
        view.afficherMessage(resultat);
    }

    private void soignerAnimal() {
        List<Soigneur> soigneurs = zoo.getSoigneurs();

        if (soigneurs.isEmpty()) {
            view.afficherMessage("Aucun soigneur disponible.");
            return;
        }

        view.afficherListeSoigneurs(soigneurs);
        int choixSoigneur = lireEntier("Choisir un soigneur: ");

        if (choixSoigneur <= 0 || choixSoigneur > soigneurs.size()) {
            return;
        }

        Soigneur soigneurChoisi = soigneurs.get(choixSoigneur - 1);

        List<Animal> animaux = zoo.getAnimaux();
        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal à soigner.");
            return;
        }

        view.afficherListeAnimaux(animaux);
        int choixAnimal = lireEntier("Choisir un animal: ");

        if (choixAnimal <= 0 || choixAnimal > animaux.size()) {
            return;
        }

        Animal animalChoisi = animaux.get(choixAnimal - 1);

        String resultat = soigneurChoisi.soigner(animalChoisi);
        view.afficherMessage(resultat);
    }

    private void nettoyerEnclos() {
        List<Soigneur> soigneurs = zoo.getSoigneurs();

        if (soigneurs.isEmpty()) {
            view.afficherMessage("Aucun soigneur disponible.");
            return;
        }

        view.afficherListeSoigneurs(soigneurs);
        int choixSoigneur = lireEntier("Choisir un soigneur: ");

        if (choixSoigneur <= 0 || choixSoigneur > soigneurs.size()) {
            return;
        }

        Soigneur soigneurChoisi = soigneurs.get(choixSoigneur - 1);

        List<Enclos> enclos = zoo.getEnclos();
        if (enclos.isEmpty()) {
            view.afficherMessage("Aucun enclos à nettoyer.");
            return;
        }

        view.afficherListeEnclos(enclos);
        int choixEnclos = lireEntier("Choisir un enclos: ");

        if (choixEnclos <= 0 || choixEnclos > enclos.size()) {
            return;
        }

        Enclos enclosChoisi = enclos.get(choixEnclos - 1);

        String resultat = soigneurChoisi.nettoyerEnclos(enclosChoisi);
        view.afficherMessage(resultat);
    }

    private void gererVisiteurs() {
        boolean retour = false;

        while (!retour) {
            view.afficherMenuVisiteurs();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    afficherVisiteurs();
                    break;
                case 2:
                    ajouterVisiteur();
                    break;
                case 3:
                    faireReagirVisiteur();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherVisiteurs() {
        List<Visiteur> visiteurs = zoo.getVisiteurs();

        if (visiteurs.isEmpty()) {
            view.afficherMessage("Aucun visiteur dans le zoo.");
            return;
        }

        view.afficherListeVisiteurs(visiteurs);
        view.afficherMessage("Total des ventes de billets: " + zoo.getCaisse() + "€");
    }

    private void ajouterVisiteur() {
        String nom = lireChaine("Nom: ");
        String prenom = lireChaine("Prénom: ");
        int age = lireEntier("Âge: ");

        Visiteur nouveauVisiteur;

        try {
            if (age < 16) {
                nouveauVisiteur = new Enfant(nom, prenom, age);
            } else {
                nouveauVisiteur = new Adulte(nom, prenom, age);
            }

            zoo.ajouterVisiteur(nouveauVisiteur);
            view.afficherMessage("Visiteur ajouté avec succès: " + nouveauVisiteur);

        } catch (IllegalArgumentException e) {
            view.afficherErreur("Erreur: " + e.getMessage());
        }
    }

    private void faireReagirVisiteur() {
        List<Visiteur> visiteurs = zoo.getVisiteurs();

        if (visiteurs.isEmpty()) {
            view.afficherMessage("Aucun visiteur disponible.");
            return;
        }

        view.afficherListeVisiteurs(visiteurs);
        int choixVisiteur = lireEntier("Choisir un visiteur: ");

        if (choixVisiteur <= 0 || choixVisiteur > visiteurs.size()) {
            return;
        }

        Visiteur visiteurChoisi = visiteurs.get(choixVisiteur - 1);

        List<Animal> animaux = zoo.getAnimaux();
        if (animaux.isEmpty()) {
            view.afficherMessage("Aucun animal pour faire réagir le visiteur.");
            return;
        }

        view.afficherListeAnimaux(animaux);
        int choixAnimal = lireEntier("Choisir un animal: ");

        if (choixAnimal <= 0 || choixAnimal > animaux.size()) {
            return;
        }

        Animal animalChoisi = animaux.get(choixAnimal - 1);

        String reaction = visiteurChoisi.reagir(animalChoisi);
        view.afficherMessage(reaction);
    }

    private void gererEnclos() {
        boolean retour = false;

        while (!retour) {
            view.afficherMenuEnclos();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    afficherEnclos();
                    break;
                case 2:
                    ajouterEnclos();
                    break;
                case 3:
                    ajouterAnimalAEnclos();
                    break;
                case 4:
                    retirerAnimalDEnclos();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherEnclos() {
        List<Enclos> enclos = zoo.getEnclos();

        if (enclos.isEmpty()) {
            view.afficherMessage("Aucun enclos dans le zoo.");
            return;
        }

        view.afficherListeEnclos(enclos);
    }

    private void ajouterEnclos() {
        String nom = lireChaine("Nom de l'enclos: ");
        int capacite = lireEntier("Capacité maximale: ");

        Enclos nouvelEnclos = new Enclos(nom, capacite);
        zoo.ajouterEnclos(nouvelEnclos);

        view.afficherMessage("Enclos ajouté avec succès: " + nouvelEnclos);
    }

    private void ajouterAnimalAEnclos() {
        List<Enclos> enclos = zoo.getEnclos();

        if (enclos.isEmpty()) {
            view.afficherMessage("Aucun enclos disponible.");
            return;
        }

        view.afficherListeEnclos(enclos);
        int choixEnclos = lireEntier("Choisir un enclos: ");

        if (choixEnclos <= 0 || choixEnclos > enclos.size()) {
            return;
        }

        Enclos enclosChoisi = enclos.get(choixEnclos - 1);

        List<Animal> animauxDisponibles = zoo.getAnimaux();
        if (animauxDisponibles.isEmpty()) {
            view.afficherMessage("Aucun animal disponible.");
            return;
        }

        view.afficherListeAnimaux(animauxDisponibles);
        int choixAnimal = lireEntier("Choisir un animal: ");

        if (choixAnimal <= 0 || choixAnimal > animauxDisponibles.size()) {
            return;
        }

        Animal animalChoisi = animauxDisponibles.get(choixAnimal - 1);

        boolean ajoutReussi = enclosChoisi.ajouterAnimal(animalChoisi);

        if (ajoutReussi) {
            view.afficherMessage("Animal ajouté à l'enclos avec succès.");
        } else {
            view.afficherErreur("Impossible d'ajouter l'animal à cet enclos (plein ou incompatible).");
        }
    }

    private void retirerAnimalDEnclos() {
        List<Enclos> enclos = zoo.getEnclos();

        if (enclos.isEmpty()) {
            view.afficherMessage("Aucun enclos disponible.");
            return;
        }

        view.afficherListeEnclos(enclos);
        int choixEnclos = lireEntier("Choisir un enclos: ");

        if (choixEnclos <= 0 || choixEnclos > enclos.size()) {
            return;
        }

        Enclos enclosChoisi = enclos.get(choixEnclos - 1);
        List<Animal> animauxDansEnclos = enclosChoisi.getAnimaux();

        if (animauxDansEnclos.isEmpty()) {
            view.afficherMessage("Aucun animal dans cet enclos.");
            return;
        }

        view.afficherListeAnimaux(animauxDansEnclos);
        int choixAnimal = lireEntier("Choisir un animal à retirer: ");

        if (choixAnimal <= 0 || choixAnimal > animauxDansEnclos.size()) {
            return;
        }

        Animal animalChoisi = animauxDansEnclos.get(choixAnimal - 1);

        boolean retraitReussi = enclosChoisi.retirerAnimal(animalChoisi);

        if (retraitReussi) {
            view.afficherMessage("Animal retiré de l'enclos avec succès.");
        } else {
            view.afficherErreur("Erreur lors du retrait de l'animal de l'enclos.");
        }
    }

    private void gererSpectacles() {
        boolean retour = false;

        while (!retour) {
            view.afficherMenuSpectacles();
            int choix = lireEntier("Votre choix: ");

            switch (choix) {
                case 1:
                    afficherSpectacles();
                    break;
                case 2:
                    creerSpectacle();
                    break;
                case 3:
                    demarrerSpectacle();
                    break;
                case 0:
                    retour = true;
                    break;
                default:
                    view.afficherErreur("Choix invalide. Veuillez réessayer.");
            }
        }
    }

    private void afficherSpectacles() {
        List<Spectacle> spectacles = zoo.getSpectacles();

        if (spectacles.isEmpty()) {
            view.afficherMessage("Aucun spectacle dans le zoo.");
            return;
        }

        view.afficherListeSpectacles(spectacles);
    }

    private void creerSpectacle() {
        String nom = lireChaine("Nom du spectacle: ");

        List<Soigneur> soigneurs = zoo.getSoigneurs();
        if (soigneurs.isEmpty()) {
            view.afficherMessage("Aucun soigneur disponible pour présenter le spectacle.");
            return;
        }

        view.afficherListeSoigneurs(soigneurs);
        int choixSoigneur = lireEntier("Choisir un soigneur: ");

        if (choixSoigneur <= 0 || choixSoigneur > soigneurs.size()) {
            return;
        }

        Soigneur soigneurChoisi = soigneurs.get(choixSoigneur - 1);

        Spectacle nouveauSpectacle = new Spectacle(nom, soigneurChoisi);

        // Ajouter des animaux au spectacle
        boolean ajouterAnimaux = true;
        while (ajouterAnimaux) {
            List<Animal> animauxCompatibles = new ArrayList<>();

            for (Animal animal : zoo.getAnimaux()) {
                if (soigneurChoisi.peutSoigner(animal)) {
                    animauxCompatibles.add(animal);
                }
            }

            if (animauxCompatibles.isEmpty()) {
                view.afficherMessage("Aucun animal compatible avec la spécialité du soigneur.");
                break;
            }

            view.afficherListeAnimaux(animauxCompatibles);
            int choixAnimal = lireEntier("Choisir un animal (0 pour terminer): ");

            if (choixAnimal == 0) {
                ajouterAnimaux = false;
            } else if (choixAnimal > 0 && choixAnimal <= animauxCompatibles.size()) {
                Animal animalChoisi = animauxCompatibles.get(choixAnimal - 1);
                nouveauSpectacle.ajouterAnimal(animalChoisi);
                view.afficherMessage("Animal ajouté au spectacle: " + animalChoisi);
            }
        }

        // Ajouter des spectateurs au spectacle
        boolean ajouterSpectateurs = true;
        while (ajouterSpectateurs) {
            List<Visiteur> visiteurs = zoo.getVisiteurs();

            if (visiteurs.isEmpty()) {
                view.afficherMessage("Aucun visiteur disponible pour assister au spectacle.");
                break;
            }

            view.afficherListeVisiteurs(visiteurs);
            int choixVisiteur = lireEntier("Choisir un visiteur (0 pour terminer): ");

            if (choixVisiteur == 0) {
                ajouterSpectateurs = false;
            } else if (choixVisiteur > 0 && choixVisiteur <= visiteurs.size()) {
                Visiteur visiteurChoisi = visiteurs.get(choixVisiteur - 1);
                nouveauSpectacle.ajouterSpectateur(visiteurChoisi);
                view.afficherMessage("Visiteur ajouté au spectacle: " + visiteurChoisi);
            }
        }

        zoo.ajouterSpectacle(nouveauSpectacle);
        view.afficherMessage("Spectacle créé avec succès: " + nouveauSpectacle);
    }

    private void demarrerSpectacle() {
        List<Spectacle> spectacles = zoo.getSpectacles();

        if (spectacles.isEmpty()) {
            view.afficherMessage("Aucun spectacle disponible.");
            return;
        }

        view.afficherListeSpectacles(spectacles);
        int choixSpectacle = lireEntier("Choisir un spectacle: ");

        if (choixSpectacle <= 0 || choixSpectacle > spectacles.size()) {
            return;
        }

        Spectacle spectacleChoisi = spectacles.get(choixSpectacle - 1);

        String resultat = spectacleChoisi.demarrer();
        view.afficherMessage(resultat);
    }

    private int lireEntier(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Veuillez entrer un nombre entier: ");
        }
        int valeur = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne
        return valeur;
    }

    private String lireChaine(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private boolean lireBoolean(String message) {
        System.out.print(message);
        String reponse = scanner.nextLine().toLowerCase();
        return reponse.equals("oui") || reponse.equals("o") || reponse.equals("yes") || reponse.equals("y") || reponse.equals("true");
    }
}