package Main;

import java.util.Scanner;

import java.io.FileWriter;

import java.io.FileReader;

import java.io.FileNotFoundException;

import static java.lang.System.*;

import static java.lang.System.err;

import static java.lang.System.out;

//////////////////////////////////////////////////////

public class LabClassDriver {
    LabClass labClass;
    String file = "ru.mirea.ikbo2019.sibgatulov.lab9/src/Main/note.txt";

    LabClassDriver(LabClass labClass) {
        this.labClass = labClass;
    }

    public void createLabClass() {
        out.print("Для того, чтобы добавить студента введите его имя и оценку, чтобы выйти из программы введите 'Выход': ");
        String name;
        int grade;
        Scanner in = new Scanner(System.in);
        do {
            name = (in.next()).toLowerCase();
            if (!name.equals("Выход")) {
                grade = in.nextInt();
                labClass.add(new Student(name, grade));
            }
        } while (!name.equals("Выход"));
    }

    public void saveLabCLass() {

        try {
            FileWriter writer = new FileWriter(file);

            while (!labClass.isEmpty()) {
                writer.write(labClass.remove().toString() + "\n");
            }
            writer.flush();
        } catch (Exception e) {
            err.println("Такой файл не существует");
        }

    }

    public void fillLabClass() {
        try {
            FileReader reader = new FileReader(file);
            Scanner in = new Scanner(reader);
            while (in.hasNextLine()) {
                String string = in.nextLine();
                int FirstIndex = string.indexOf("Имя='") + 6;
                int LastIndex = string.lastIndexOf('\'');
                String name = string.substring(FirstIndex, LastIndex);

                FirstIndex = string.indexOf("Оценка=") + 6;
                LastIndex = string.lastIndexOf('}');
                int grade = Integer.parseInt(string.substring(FirstIndex, LastIndex));

                labClass.add(new Student(name, grade));
            }
        } catch (FileNotFoundException e) {
            err.println("Такой файл не существует");
        }

    }
}
