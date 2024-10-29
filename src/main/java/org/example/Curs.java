package org.example;

import java.util.*;

public class Curs<T extends Student> {
    private String denumire;
    private int capacitateMaxima;
    private List<T exte> studentiInscrisi;
    private String tipCurs;

    // Constructor pentru clasa Curs
    public Curs(String denumire, int capacitateMaxima, String tipCurs) {
        this.denumire = denumire;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiInscrisi = new ArrayList<>();
        this.tipCurs = tipCurs;
    }

    // Metodă pentru înscrierea unui student la curs
    public int inscrieStudent(Student student) {
        T studentT = (T) student;  // Conversie la tipul generic T
        if (acceptaStudent(studentT)) {
            if (this.studentiInscrisi.size() < this.capacitateMaxima) {
                this.studentiInscrisi.add(studentT);
                return 1;  // Înscrierea a avut succes
            } else if (student.getMedie() == this.studentiInscrisi.get(this.studentiInscrisi.size() - 1).getMedie()) {
                this.studentiInscrisi.add(studentT);
                return 1;  // Înscrierea a avut succes, chiar dacă este în afara capacitații maxime
            }
        }
        return 0;  // Înscrierea a eșuat
    }

    // Metodă pentru obținerea denumirii cursului
    public String getDenumire() {
        return this.denumire;
    }

    // Metodă pentru verificarea dacă cursul acceptă un student de tipul T
    public boolean acceptaStudent(T student) {
        return student.getProgram().equals(tipCurs);
    }

    // Metodă pentru obținerea detaliilor despre curs sub formă de șir de caractere
    public String detaliiCurs() {
        StringBuilder detalii = new StringBuilder();
        detalii.append(denumire).append(" ")
                .append("(").append(this.capacitateMaxima).append(")\n");

        // Sortează studenții în funcție de nume
        Collections.sort(studentiInscrisi, Comparator.comparing(Student::getNume));

        // Adaugă detalii despre fiecare student înscris
        for (Student student : studentiInscrisi) {
            detalii.append(student.getNume()).append(" - ").append(student.getMedie()).append("\n");
        }

        return detalii.toString();
    }
}
