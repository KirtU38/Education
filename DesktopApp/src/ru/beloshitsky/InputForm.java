package ru.beloshitsky;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class InputForm {
  private JPanel mainPanel;
  private JPanel buttonPanel;
  private JButton collapseButton;
  private JPanel textFields;
  private JTextArea lastName;
  private JTextArea firstName;
  private JTextArea patronymic;
  private JLabel lastNameLabel;
  private JLabel firstNameLabel;
  private JLabel patronymicLabel;

  public InputForm() {
    collapseButton.addActionListener(
        new Action() {
          @Override
          public Object getValue(String key) {
            return null;
          }

          @Override
          public void putValue(String key, Object value) {}

          @Override
          public void setEnabled(boolean b) {}

          @Override
          public boolean isEnabled() {
            return false;
          }

          @Override
          public void addPropertyChangeListener(PropertyChangeListener listener) {}

          @Override
          public void removePropertyChangeListener(PropertyChangeListener listener) {}

          @Override
          public void actionPerformed(ActionEvent e) {
            if (lastName.getText().equals("") || firstName.getText().equals("")) {
              JOptionPane.showMessageDialog(mainPanel, "Фамилия и Имя обязательны");
              return;
            }

            Main.expandFrame.setLocation(Main.collapseFrame.getLocation());
            Main.collapseFrame.setVisible(false);
            Main.expandFrame.setVisible(true);
            Main.collapsedForm.setFullNameLabel(
                String.format(
                    "%s %s %s", lastName.getText(), firstName.getText(), patronymic.getText()));
          }
        });
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }
}
