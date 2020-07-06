package com.company;

import ru.vsu.cs.util.JTableUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class MainFrame extends JFrame {

    private InputArray inputArray = new InputArray("window");
    private WorkWithFile workWithFile = new WorkWithFile();
    private List<Student> listStudent = new ArrayList<>();

    private JPanel Panel;
    private JPanel mainPanel;
    private JButton runButton;
    private JButton saveOutputButton;
    private JButton saveInputButton;
    private JButton loadButton;
    private JTable inputTable;
    private JTable outputTable;

    public MainFrame() {
        this.setTitle("SortStudent");
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        JTableUtils.initJTableForArray(inputTable, 100, false, false, true, false);
        JTableUtils.initJTableForArray(outputTable, 100, false, false, true, false);

        inputTable.setRowHeight(25);

        outputTable.setRowHeight(25);

        JTableUtils.writeArrayToJTable(inputTable, new String[][]{
                {"1", "2", "Андреев", "Игорь"}, {"2", "3", "Киселев", "Владимир"}, {"1", "1", "Лемза", "Сергей"}, {"2", "3", "Круглов", "Виталий"}, {"1", "2", "Андреев", "Андрей"}

        });

        JTableUtils.writeArrayToJTable(outputTable, new String[][]{{}});
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileOpen = new JFileChooser();
                fileOpen.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("File(.txt)", "txt");
                fileOpen.addChoosableFileFilter(filter);
                int ret = fileOpen.showDialog(null, "Открыть файл");

                try {
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileOpen.getSelectedFile();
                        String nameFile = file.getPath();
                        List<List<String>> workingListString = inputArray.toTwoDimensionalListString(workWithFile.fileToString(nameFile));
                        String[][] workingArrayString = InputArray.converterListStringToArrayString(workingListString);
                        JTableUtils.writeArrayToJTable(inputTable, workingArrayString);

                    }
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } /*catch (Exception e) {
                SwingUtils.showErrorMessageBox(e);
                } */ catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Error Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }
            }

        });

        saveInputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(Panel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);

                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        workWithFile.saveArrayToFile(arrayString, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });
        saveOutputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooserSave = new JFileChooser();
                if (fileChooserSave.showSaveDialog(Panel) == JFileChooser.APPROVE_OPTION) {
                    try {
                        String[][] arrayString = JTableUtils.readStringMatrixFromJTable(outputTable);

                        listStudent = inputArray.converterListStringToListStudent(inputArray.ArrayToList(arrayString));

                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                        workWithFile.saveArrayToFile(arrayString, file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            }
        });

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    String[][] arrayString = JTableUtils.readStringMatrixFromJTable(inputTable);
                    listStudent = inputArray.converterListStringToListStudent(inputArray.ArrayToList(arrayString));

                    listStudent.sort(new sortStudentCourse().thenComparing(new sortStudentGroup().thenComparing(new sortStudentSurname().thenComparing(new sortStudentName()))));

                    JTableUtils.writeArrayToJTable(outputTable, InputArray.converterListStringToArrayString(inputArray.converterListStudentToListString(listStudent)));

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ошибка! Проверьте правильность введённых данных", "Output", JOptionPane.PLAIN_MESSAGE);
                }


            }
        });

    }

}
