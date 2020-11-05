package Main;

import Exceptions.EmptyStringException;
import Exceptions.StudentNotFoundException;

import static java.lang.System.*;

import java.util.Scanner;

public class LabClassUI {

    LabClass labClass;

    public LabClassUI() {

        labClass = new LabClass();
        LabClassDriver driver = new LabClassDriver(labClass);
        Scanner in = new Scanner(System.in);
        boolean created = false;

        out.print("Хотите ли вы создать новый список? Введите 'Да', если хотите или 'Нет', если желаете загрузить старый список: ");

        String input = (in.nextLine()).toLowerCase();
        if (input.equals("Да")) {
            driver.createLabClass();
            created = true;
        } else if (input.equals("Нет")) {
            driver.fillLabClass();
        } else {
            err.print("Некорректный ввод. Запись недоступна, список будет заполнен из памяти!");
            out.println('\n');
            driver.fillLabClass();
        }

        if (created) {
            out.println("Хотите ли вы сохранить список? Да или Нет: ");
            input = (in.next()).toLowerCase();
            if (input.equals("Да")) {
                driver.saveLabCLass();
            }
            driver.fillLabClass();
        }
    }

    public void findStudent() {

        Scanner in = new Scanner(System.in);
        out.print("Введите имя студента: ");
        String name;

        try {
            name = (in.nextLine()).toLowerCase();
            if (name.isEmpty()) {

                throw new EmptyStringException("Вы ввели пустую строку!");
            }
        } catch (EmptyStringException e) {
            err.println(e.getMessage());
            out.println();
            findStudent();
            return;
        }

        try {
            out.println(labClass.find(name).toString());
        } catch (StudentNotFoundException e) {
            err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {

        LabClassUI app = new LabClassUI();
        app.findStudent();
    }
}
