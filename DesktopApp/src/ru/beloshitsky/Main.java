package ru.beloshitsky;

import javax.swing.*;

public class Main {
  public static InputForm inputForm;
  public static JFrame collapseFrame;
  public static CollapsedForm collapsedForm;
  public static JFrame expandFrame;

  public static void main(String[] args) {
    inputForm = new InputForm();
    collapseFrame = new JFrame();
    collapseFrame.setSize(400, 300);
    collapseFrame.add(inputForm.getMainPanel());
    collapseFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    collapseFrame.setLocationRelativeTo(null);
    collapseFrame.setVisible(true);

    collapsedForm = new CollapsedForm();
    expandFrame = new JFrame();
    expandFrame.setSize(400, 300);
    expandFrame.add(collapsedForm.getMainPanel());
    expandFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    expandFrame.setLocationRelativeTo(null);
    expandFrame.setVisible(false);
  }
}
