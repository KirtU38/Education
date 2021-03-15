package ru.beloshitsky;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class CollapsedForm {
  private JPanel mainPanel;
  private JPanel buttonPanel;
  private JButton expandButton;
  private JLabel fullNameLabel;

  public CollapsedForm() {
    expandButton.addActionListener(
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
            Main.collapseFrame.setLocation(Main.expandFrame.getLocation());
            Main.expandFrame.setVisible(false);
            Main.collapseFrame.setVisible(true);
          }
        });
  }

  public void setFullNameLabel(String name) {
    fullNameLabel.setText(name);
  }

  public JPanel getMainPanel() {
    return mainPanel;
  }
}
