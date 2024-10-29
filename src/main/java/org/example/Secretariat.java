package org.example;

import java.util.*;
import java.io.*;

public class Secretariat {
    private Map<String, Student> studenti;
    private Map<String, Curs<?>> cursuri;

    // Constructor pentru clasa Secretariat
    public Secretariat() {
        this.studenti = new HashMap<>();
        this.cursuri = new HashMap<>();
    }

    // Metodă pentru adăugarea unui student în funcție de programul de studii și nume
    public void adaugaStudent(String programStudii, String numeStudent) throws DuplicateStudentException {
        // Verifică dacă studentul există deja
        if (studenti.containsKey(numeStudent)) {
            throw new DuplicateStudentException("Studentul exista deja in lista!");
        } else {
            Student student = null;
            // Creează un student de licență sau master în funcție de programul de studii
            if ("master".equals(programStudii)) {
                student = new StudentMaster(numeStudent);
            } else if ("licenta".equals(programStudii)) {
                student = new StudentLicenta(numeStudent);
            }

            studenti.put(numeStudent, student);
        }
    }

    // Metodă pentru adăugarea unui curs în funcție de programul de studii, nume și capacitate maximă
    public void adaugaCurs(String programStudii, String numeCurs, int capacitateMaxima) {
        Curs<?> curs = null;
        // Creează un curs de master sau licență în funcție de programul de studii
        if ("master".equals(programStudii)) {
            curs = new Curs<>(numeCurs, capacitateMaxima, "master");
        } else if ("licenta".equals(programStudii)) {
            curs = new Curs<>(numeCurs, capacitateMaxima, "licenta");
        }

        cursuri.put(numeCurs, curs);
    }

    // Metodă pentru citirea mediilor din fișierele de note
    public void citesteMedii(String numeTest) {
        String caleDirectorNote = "src/main/resources/" + numeTest + "/";
        File directorNote = new File(caleDirectorNote);

        if (directorNote.exists() && directorNote.isDirectory()) {
            File[] fisiereNote = directorNote.listFiles((dir, numeFisier) -> numeFisier.startsWith("note_"));
            if (fisiereNote != null) {
                for (File fisierNote : fisiereNote) {
                    try {
                        List<String> linii = FileUtils.citesteLinii(fisierNote);
                        linii.forEach(this::procesareLinieMedie);
                    } catch (IOException e) {
                        System.err.println("Eroare la citirea fișierului " + fisierNote.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }

    // Metodă pentru procesarea unei linii de medie
    private void procesareLinieMedie(String linie) {
        String[] informatiiMedie = linie.split("\\s*-\\s*");
        if (informatiiMedie.length == 2) {
            String numeStudent = informatiiMedie[0].trim();
            try {
                double medie = Double.parseDouble(informatiiMedie[1].trim());
                Student student = studenti.get(numeStudent);
                if (student != null) {
                    student.setMedie(medie);
                }
            } catch (NumberFormatException e) {
                System.err.println("Eroare: Format medie invalid pentru studentul " + informatiiMedie[0] + ": " + informatiiMedie[1]);
            }
        }
    }

    // Metodă pentru publicarea mediilor într-un fișier
    public void posteazaMediile(String numeTest) {
        String caleFisier = "src/main/resources/" + numeTest + "/" + numeTest + ".out";
        List<String> linii = new ArrayList<>();

        TreeMap<Double, List<Student>> sortedStudents = new TreeMap<>(Collections.reverseOrder());
        for (Student student : studenti.values()) {
            sortedStudents.computeIfAbsent(student.getMedie(), k -> new ArrayList<>()).add(student);
        }

        linii.add("***");
        for (List<Student> studentsWithSameGrade : sortedStudents.values()) {
            studentsWithSameGrade.sort(Comparator.comparing(Student::getNume));
            for (Student student : studentsWithSameGrade) {
                linii.add(student.getNume() + " - " + student.getMedie());
            }
        }

        try {
            FileUtils.scrieLinii(new File(caleFisier), linii);
        } catch (IOException e) {
            System.err.println("Eroare la scrierea mediilor: " + e.getMessage());
        }
    }

    public void posteazaStudent(String numeStudent, String numeTest) {
        Student student = studenti.get(numeStudent);
        if (student != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + numeTest + "/" + numeTest + ".out", true))) {
                bw.append("***\n");
                bw.append(student.detaliiStudent());
            } catch (IOException e) {
                System.err.println("Eroare la scrierea detaliilor despre student: " + e.getMessage());
            }
        }
    }

    public void posteazaCurs(String numeCurs, String numeTest) {
        Curs<?> curs = cursuri.get(numeCurs);
        if (curs != null) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/" + numeTest + "/" + numeTest + ".out", true))) {
                bw.append("***\n");
                bw.append(curs.detaliiCurs());
            } catch (IOException e) {
                System.err.println("Eroare la scrierea detaliilor despre curs: " + e.getMessage());
            }
        }
    }
    // Metodă pentru actualizarea mediei unui student după contestație
    public void contestatieMedie(String numeStudent, double nouaMedie) {
        Student student = studenti.get(numeStudent);
        if (student != null) {
            student.setMedie(nouaMedie);
        }
    }

    // Metodă pentru adăugarea preferințelor unui student
    public void adaugaPreferinte(String numeStudent, String... preferinte) {
        Student student = studenti.get(numeStudent);
        if (student != null) {
            student.adaugaPreferinte(preferinte);
        }
    }

    // Metodă pentru repartizarea studenților la cursuri în funcție de preferințe și medii
    public void repartizeazaCursuri() {
        TreeMap<Double, List<Student>> sortedStudents = new TreeMap<>(Collections.reverseOrder());

        for (Student student : studenti.values()) {
            sortedStudents.computeIfAbsent(student.getMedie(), k -> new ArrayList<>()).add(student);
        }

        for (Map.Entry<Double, List<Student>> entry : sortedStudents.entrySet()) {
            List<Student> studentsWithSameGrade = entry.getValue();

            for (Student student : studentsWithSameGrade) {
                for (String preferinta : student.getPreferinte()) {
                    Curs<?> curs = cursuri.get(preferinta);
                    if (curs != null) {
                        int ok = curs.inscrieStudent(student);
                        if (ok == 1) {
                            student.setCurs(curs);
                            break;
                        }
                    }
                }
            }
        }
    }
}
