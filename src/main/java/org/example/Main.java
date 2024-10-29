package org.example;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }

        String numeTest = args[0];
        String caleIntrare = "src/main/resources/" + numeTest + "/" + numeTest + ".in";
        String caleIesire = "src/main/resources/" + numeTest + "/" + numeTest + ".out";

        Secretariat secretariat = new Secretariat();

        try (BufferedReader br = new BufferedReader(new FileReader(caleIntrare));
             FileWriter ignored = new FileWriter(caleIesire)) {

            String linie;
            while ((linie = br.readLine()) != null) {
                proceseazaComanda(secretariat, linie, numeTest, caleIesire);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void proceseazaComanda(Secretariat secretariat, String linie, String numeTest, String caleIesire) {
        String[] comanda = linie.split("\\s+");

        switch (comanda[0]) {
            case "adauga_student":
                adaugaStudent(secretariat, comanda, caleIesire);
                break;

            case "adauga_curs":
                adaugaCurs(secretariat, comanda);
                break;

            case "citeste_mediile":
                secretariat.citesteMedii(numeTest);
                break;

            case "posteaza_mediile":
                secretariat.posteazaMediile(numeTest);
                break;

            case "contestatie":
                contestatieMedie(secretariat, comanda);
                break;

            case "adauga_preferinte":
                adaugaPreferinte(secretariat, comanda);
                break;

            case "repartizeaza":
                secretariat.repartizeazaCursuri();
                break;

            case "posteaza_curs":
                secretariat.posteazaCurs(comanda[2], numeTest);
                break;

            case "posteaza_student":
                secretariat.posteazaStudent(comanda[2], numeTest);
                break;
        }
    }

    private static void adaugaStudent(Secretariat secretariat, String[] comanda, String caleIesire) {
        try {
            secretariat.adaugaStudent(comanda[2], comanda[4]);
        } catch (Exception e) {
            scrieMesajEroare(caleIesire, "Student duplicat: " + comanda[4]);
        }
    }

    private static void adaugaCurs(Secretariat secretariat, String[] comanda) {
        secretariat.adaugaCurs(comanda[2], comanda[4], Integer.parseInt(comanda[6]));
    }

    private static void contestatieMedie(Secretariat secretariat, String[] comanda) {
        secretariat.contestatieMedie(comanda[2], Double.parseDouble(comanda[4]));
    }

    private static void adaugaPreferinte(Secretariat secretariat, String[] comanda) {
        String numeStudent = comanda[2].trim();
        String preferinteString = String.join(" ", Arrays.copyOfRange(comanda, 4, comanda.length));
        String[] preferinte = preferinteString.split("\\s*-\\s*");
        secretariat.adaugaPreferinte(numeStudent, preferinte);
    }

    private static void scrieMesajEroare(String caleIesire, String mesaj) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(caleIesire, true))) {
            bw.write("***");
            bw.newLine();
            bw.write(mesaj);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
