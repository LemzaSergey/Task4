/*30.Отсортировать студентов так, чтобы сначала сортировка шла по курсу,
затем по группе и только потом по фамилии (для этого необходимо соответствующим образом
реализовать интерфейс Comparable<Student> для класса Student).
*/

package com.company;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);

        if (args.length > 0) {
            switch (args[0]) {
                case "-window":
                    new MainFrame();
                    break;

                default:
                    System.exit(-13);
            }
        } else {
            System.exit(-15);
        }

    }
}
