package org.example;

import java.util.*;

public class Student {
    private String nume;
    private double medie;
    private List<String> preferinte;
    private Curs<?> curs;

    // Constructor pentru clasa Student
    public Student(String nume) {
        this.nume = nume;
        this.medie = 0;
        this.preferinte = new ArrayList<>();
    }

    // Metodă pentru obținerea numelui studentului
    public String getNume() {
        return nume;
    }

    // Metodă pentru obținerea cursului la care este înscris studentul
    public Curs<?> getCurs() {
        return curs;
    }

    // Metodă pentru setarea cursului la care este înscris studentul
    public void setCurs(Curs<?> curs) {
        this.curs = curs;
    }

    // Metodă pentru obținerea programului de studii al studentului (returnează null în clasa generică)
    public String getProgram() {
        return null;
    }

    // Metodă pentru setarea mediei studentului
    public void setMedie(double medie) {
        this.medie = medie;
    }

    // Metodă pentru obținerea mediei studentului
    public double getMedie() {
        return medie;
    }

    // Metodă pentru adăugarea preferințelor de cursuri pentru student
    public void adaugaPreferinte(String... cursuri) {
        preferinte.addAll(Arrays.asList(cursuri));
    }

    // Metodă pentru obținerea listei de preferințe de cursuri
    public List<String> getPreferinte() {
        return preferinte;
    }

    // Metodă pentru obținerea detaliilor despre student (nume, medie, curs)
    public String detaliiStudent() {
        String s = "Student: " + this.nume + " - " + getMedie() + " - " + curs.getDenumire() + "\n";
        return s;
    }
}

// Clasa pentru studentul de licență
class StudentLicenta extends Student {
    // Constructor pentru clasa StudentLicenta
    public StudentLicenta(String nume) {
        super(nume);
    }

    // Metodă pentru obținerea programului studentului de licență
    @Override
    public String getProgram() {
        return "licenta";
    }

    // Suprascrierea metodei detaliiStudent pentru a include tipul de student
    @Override
    public String detaliiStudent() {
        String s = "Student Licenta: " + super.getNume() + " - " + super.getMedie() + " - " + super.getCurs().getDenumire() + "\n";
        return s;
    }
}

// Clasa pentru studentul de masterat
class StudentMaster extends Student {
    // Constructor pentru clasa StudentMaster
    public StudentMaster(String nume) {
        super(nume);
    }

    // Metodă pentru obținerea programului studentului de masterat
    @Override
    public String getProgram() {
        return "master";
    }

    // Suprascrierea metodei detaliiStudent pentru a include tipul de student
    @Override
    public String detaliiStudent() {
        String s = "Student Master: " + super.getNume() + " - " + super.getMedie() + " - " + super.getCurs().getDenumire() + "\n";
        return s;
    }
}
