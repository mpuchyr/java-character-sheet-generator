package src.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.models.character_backgrounds.CoCCharacterBackground;
import src.main.models.characters.CoCCharacter;
import src.main.models.characters.GenericCharacter;
import src.main.models.characters.CoCCharacter.CharacterEra;
import src.main.models.characters.GenericCharacter.Sex;

public class Main implements ActionListener {

    public static JButton generateCoCCharacterButton;
    public static JTextArea cocCharacterInfo;
    public static JScrollPane scroll;

    public static void main(String[] args) {
        // CoCCharacter person = new CoCCharacter(CharacterEra.NINETEENTWENTIES, true);
        // System.out.println(person);

        // CoCCharacterBackground background = new CoCCharacterBackground();
        // System.out.println(background);

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

        cocCharacterInfo = new JTextArea("");
        scroll = new JScrollPane(cocCharacterInfo);
        scroll.setBounds(10, 110, 500, 300);
        panel.add(scroll);


        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CoCCharacter cocCharacter = new CoCCharacter(CharacterEra.NINETEENTWENTIES, true);
        cocCharacterInfo.setText(cocCharacter.toString());
    }


}