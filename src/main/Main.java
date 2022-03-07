package src.main;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.SwingPropertyChangeSupport;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.chrono.Era;

import src.main.models.characters.CoCCharacter;
import src.main.models.characters.CoCCharacter.CharacterEra;
import src.main.models.characters.GenericCharacter.Sex;
import src.main.models.helpers.Helpers;


public class Main implements ActionListener {

    private static JPanel panel;

    public static JButton generateCoCCharacterButton;
    public static JTextArea cocCharacterInfo;
    public static JScrollPane scroll;
    public static JTextField nameField;
    public static JRadioButton maleButton;
    public static JRadioButton femaleButton;
    public static JRadioButton anyGenderButton;
    public static Sex characterSex = Sex.MALE;
    public static boolean randomCharacterGender = true;
    public static JRadioButton era1920s;
    public static JRadioButton eraModern;
    public static CharacterEra characterEra = CharacterEra.NINETEENTWENTIES;



    public static void main(String[] args) {

        JFrame frame = new JFrame();
        panel = new JPanel();
        
        frame.setSize(1200, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        generateCoCCharacterButton = new JButton("CoC");
        generateCoCCharacterButton.setBounds(10, 10, 100, 25);
        generateCoCCharacterButton.addActionListener(new Main());
        panel.add(generateCoCCharacterButton);


        displayCharacterNameGroup();
        displayCharacterGenderOptions();
        displayCharacterEraOptions();

        cocCharacterInfo = new JTextArea("");
        scroll = new JScrollPane(cocCharacterInfo);
        scroll.setBounds(300, 50, 500, 300);
        panel.add(scroll);


        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String charName = nameField.getText();
        
        if ((charName.isBlank() || charName == null) && randomCharacterGender) {
            CoCCharacter cocCharacter = new CoCCharacter(characterEra, true);
            cocCharacterInfo.setText(cocCharacter.toString());
        } else if ((charName.isBlank() || charName == null) && !randomCharacterGender){
            CoCCharacter cocCharacter = new CoCCharacter(characterEra, characterSex, true);
            cocCharacterInfo.setText(cocCharacter.toString());
        }else {
            CoCCharacter cocCharacter = new CoCCharacter(characterEra, charName, characterSex, true);
            cocCharacterInfo.setText(cocCharacter.toString());
        }

    }

    private static void displayCharacterNameGroup() {
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 50, 50, 25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(50, 50, 200, 25);
        panel.add(nameField);
    }

    private static void displayCharacterGenderOptions() {
        maleButton = new JRadioButton("Male");
        maleButton.setBounds(10, 75, 75, 25);
        maleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterSex = Sex.MALE;
                randomCharacterGender = false;
            }
        });

        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(10, 100, 75, 25);
        femaleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterSex = Sex.FEMALE;
                randomCharacterGender = false;
            }
        });

        anyGenderButton = new JRadioButton("Any");
        anyGenderButton.setSelected(true);
        anyGenderButton.setBounds(10, 125, 75, 25);
        anyGenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                randomCharacterGender = true;
            }
        });

        ButtonGroup group = new ButtonGroup();
        group.add(maleButton);
        group.add(femaleButton);
        group.add(anyGenderButton);


        panel.add(maleButton);
        panel.add(femaleButton);
        panel.add(anyGenderButton);
    }

    private static void displayCharacterEraOptions() {
        era1920s = new JRadioButton("1920s");
        era1920s.setSelected(true);
        era1920s.setBounds(10, 150, 75, 25);
        era1920s.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterEra = CharacterEra.NINETEENTWENTIES;
            }
        });

        eraModern = new JRadioButton("Modern");
        eraModern.setBounds(10, 175, 75, 25);
        eraModern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterEra = CharacterEra.MODERN;
            }
        });

        ButtonGroup eraGroup = new ButtonGroup();
        eraGroup.add(era1920s);
        eraGroup.add(eraModern);

        panel.add(era1920s);
        panel.add(eraModern);
    }


}