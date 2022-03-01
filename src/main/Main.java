package src.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.models.characters.CoCCharacter;
import src.main.models.characters.CoCCharacter.CharacterEra;
import src.main.models.characters.GenericCharacter.Sex;


public class Main implements ActionListener {

    public static JButton generateCoCCharacterButton;
    public static JTextArea cocCharacterInfo;
    public static JScrollPane scroll;
    public static JTextField nameField;

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        generateCoCCharacterButton = new JButton("CoC");
        generateCoCCharacterButton.setBounds(10, 10, 100, 25);
        generateCoCCharacterButton.addActionListener(new Main());
        panel.add(generateCoCCharacterButton);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 50, 50, 50);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(75, 50, 50, 25);
        panel.add(nameField);

        cocCharacterInfo = new JTextArea("");
        scroll = new JScrollPane(cocCharacterInfo);
        scroll.setBounds(10, 110, 500, 300);
        panel.add(scroll);


        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String charName = nameField.getText();
        if (charName.isBlank() || charName == null) {
            CoCCharacter cocCharacter = new CoCCharacter(CharacterEra.NINETEENTWENTIES, true);
            cocCharacterInfo.setText(cocCharacter.toString());
        } else {
            CoCCharacter cocCharacter = new CoCCharacter(CharacterEra.NINETEENTWENTIES, charName, Sex.MALE, true);
            cocCharacterInfo.setText(cocCharacter.toString());
        }

    }


}