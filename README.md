Clasa Student

Clasa Student reprezintă un student generic și include următoarele atribute: numele studentului, media, lista de preferințe de cursuri si cursul la care este înscris studentul.
Metode:
getNume(): Obține numele studentului.
getCurs(): Obține cursul la care este înscris studentul.
setCurs(Curs<?> curs): Setează cursul pentru student.
getProgram(): Obține programul studentului (returnează null în clasa generică).
setMedie(double medie): Setează media studentului.
getMedie(): Obține media studentului.
adaugaPreferinte(String... cursuri): Adaugă preferințe de cursuri pentru student.
getPreferinte(): Obține lista de preferințe de cursuri.
detaliiStudent(): Obține detalii despre student (nume, medie, curs la care este înscris).

Clasele StudentLicenta si StudentMaster extind clasa Student și includ funcționalități suplimentare:
getProgram(): Suprascriere pentru a returna programul pentru studenți, returnand "licenta" sau "master".
detaliiStudent(): Suprascriere pentru a include tipul de student în detalii.


Clasa Curs

Cursul poate accepta studenți de un anumit tip generic (T), care trebuie să extindă clasa Student. Clasa are urmatoarele atribute: numele cursului, capacitatea maximă de înscriere a studenților în curs, lista de studenți înscriși la curs si tipul cursului, corespunzător programului de studii al studenților.

Metode:
inscrieStudent(Student student): Înscrie un student în curs, facand conversia la tipul T. Studentul poate fi acceptat la curs daca apartine aceluiasi program de studii din care face parte cursul si daca mai sunt locuri disponibile. In cazul in care exista mai multi studenti cu aceeasi medie, toti vor fi inscrisi, chiar daca se depasteste capacitatea maxima.
getDenumire(): Returnează denumirea cursului.
acceptaStudent(T student): Verifică dacă cursul acceptă înscrierea unui student de tipul T (generic).
detaliiCurs(): Returnează detalii despre curs sub formă de șir de caractere. Include numele cursului, capacitatea maximă și detalii despre studenții înscriși, sortați alfabetic după nume.


Clasa Secretariat

Atribute:
studenti: Listă de studenți înscriși la secretariat.
cursuri: Listă de cursuri disponibile în cadrul secretariatului.

Metode:
adaugaStudent(String programStudii, String numeStudent):
Adaugă un student în funcție de programul de studii și nume. Verifică dacă studentul există deja și aruncă o excepție în caz afirmativ. Creează un student de tipul StudentLicenta sau StudentMaster în funcție de programul de studii.

adaugaCurs(String programStudii, String numeCurs, int capacitateMaxima):
Adaugă un curs în funcție de programul de studii, nume și capacitate maximă. Creează un curs de master sau licență în funcție de programul de studii.

citesteMedii(String numeTest):
Citeste mediile studenților din fișierele ce contin note. Parcurge fisierele de note din directorul asociat testului și actualizează mediile studenților.

posteazaMediile(String numeTest):
Publică mediile studenților într-un fișier de ieșire. Sortează studenții după medie și nume în ordine descrescătoare.

contestatieMedie(String numeStudent, double nouaMedie):
Actualizează media unui student după contestație.

adaugaPreferinte(String numeStudent, String... preferinte):
Adaugă preferințele unui student pentru cursuri.

repartizeazaCursuri():
Sorteaza studentii in ordine descrescatoare in functie de medie si ii repartizeaza la cursul din preferintele lor la care exista loc, folosind metoda inscrieStudent din clasa Curs.

posteazaStudent(String numeStudent, String numeTest):
Publică detaliile despre un student într-un fișier de ieșire.

posteazaCurs(String numeCurs, String numeTest): void
Publică detaliile despre un curs într-un fișier de ieșire.


Main

Programul citeste comenzile din fișierul de intrare asociat testului specificat. Mai apoi creaza obiectul 'secretariat' de tip Secretariat, cu ajutorul caruia se vor apela toate metodele ce vor rezolva comenzile.
Comenzile disponibile includ:
adauga_student: Adaugă un student la secretariat, tratand exceptia aruncata la adaugarea unui student duplicat, pe care o scrie in fisierul de iesire.
adauga_curs: Adaugă un curs la secretariat.
citeste_mediile: Citește mediile studenților din fișierele de note.
posteaza_mediile: Publică mediile într-un fișier.
contestatie: Contestă media unui student.
adauga_preferinte: Adaugă preferințele unui student pentru cursuri. Pentru a prelua preferintele din comanda data am concatenat toate argumentele incepand cu pozitia 4 si mai apoi le separ, punandu-le intr-un vector.
repartizeaza: Repartizează studenții la cursuri.
posteaza_curs: Publică detaliile despre un curs în fișierul de iesire.
posteaza_student: Publică detaliile despre un student în fișierul de iesire.
