package src.main;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

import src.main.models.characters.CoCCharacter;
import src.main.models.characters.CoCCharacter.CharacterEra;
import src.main.models.characters.GenericCharacter.Sex;
import src.main.models.helpers.CharacterExporter;


public class Main implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;

    public static JButton generateCoCCharacterButton;
    public static JTextArea cocCharacterInfo;
    public static JScrollPane scroll;
    public static JTextField nameField;
    public static JRadioButton maleButton;
    public static JRadioButton femaleButton;
    public static JRadioButton anyGenderButton;
    public static Sex characterSex = Sex.ANY;
    public static boolean randomCharacterGender = true;
    public static JRadioButton era1920s;
    public static JRadioButton eraModern;
    public static CharacterEra characterEra = CharacterEra.NINETEENTWENTIES;
    public static JTextField ageField;
    public static JComboBox<String> professionChoice;
    public static ArrayList<Object> characterList = new ArrayList<Object>();
    public static int componentCount;
    public static JPanel prevCharPanel;
    public static JScrollPane previousCharacterScroll;
    public static JTextField skillLimitField;
    public static JTextField occupationSkillLimitField;
    public static JCheckBox generateBackground;

    public static void main(String[] args) {

        frame = new JFrame();
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
        displayCharacterAgeSelection();
        displayProfessionOptions();
        displaySkillLimitEntry();
        displayGenerateBackgroundCheckbox();

        cocCharacterInfo = new JTextArea("");
        scroll = new JScrollPane(cocCharacterInfo);
        scroll.setBounds(300, 50, 500, 400);
        panel.add(scroll);

        JButton saveCharacterButton = new JButton("Save Characters");
        saveCharacterButton.setBounds(10, 450, 100, 25);
        saveCharacterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CharacterExporter.exportCharacters(characterList);
            }
        });
        panel.add(saveCharacterButton);

        JButton clearCharactersButton = new JButton("Clear Character List");
        clearCharactersButton.setBounds(300, 450, 175, 25);
        // Removes all extra components based on original component count
        // and clears the characterList
        clearCharactersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterList.clear();
                prevCharPanel.removeAll();
                cocCharacterInfo.setText("");
                panel.repaint();
                
                
            }
        });


        prevCharPanel = new JPanel();
        prevCharPanel.setLayout(new BoxLayout(prevCharPanel, BoxLayout.Y_AXIS));
        previousCharacterScroll = new JScrollPane(prevCharPanel);
        previousCharacterScroll.setBounds(850, 50, 210, 400);
        panel.add(previousCharacterScroll);

        panel.add(clearCharactersButton);
        componentCount = panel.getComponentCount();




        frame.setVisible(true);
        
    }

    @Override
    // Creates the character based off of information entered
    public void actionPerformed(ActionEvent e) {
        String charName = nameField.getText();
        String ageFieldValue = ageField.getText();
        String skillLimitValue = skillLimitField.getText();
        String occupationSkillLimitValue = occupationSkillLimitField.getText();
        int charAge = 0;
        int skillLimit = 0;
        int occupationalSkillLimit = 0;
        String chosenProfession = (String)professionChoice.getSelectedItem();


        if (!(ageFieldValue.isBlank() || ageField == null)) {
            try {
                charAge = Integer.parseInt(ageFieldValue);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                charAge = 0;
            }  
        }

        if (!(skillLimitValue.isBlank() || skillLimitValue == null)) {
            try {
                skillLimit = Integer.parseInt(skillLimitValue);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                skillLimit = 0;
            }
        }

        if (!(occupationSkillLimitValue.isBlank() || occupationSkillLimitValue == null)) {
            try {
                occupationalSkillLimit = Integer.parseInt(occupationSkillLimitValue);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                occupationalSkillLimit = 0;
            }
        }


        CoCCharacter cocCharacter = new CoCCharacter(characterEra, charName, charAge, characterSex, chosenProfession, true, generateBackground.isSelected(), skillLimit, occupationalSkillLimit);
        cocCharacterInfo.setText(cocCharacter.toString());
        cocCharacterInfo.setCaretPosition(0);

        characterList.add(cocCharacter);
        prevCharPanel.removeAll();
        showPreviousCharacters();
    
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
        anyGenderButton = new JRadioButton("Any");
        anyGenderButton.setSelected(true);
        anyGenderButton.setBounds(10,75, 75, 25);
        anyGenderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterSex = Sex.ANY;
                randomCharacterGender = true;
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

        maleButton = new JRadioButton("Male");
        maleButton.setBounds(10, 125, 75, 25);
        maleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterSex = Sex.MALE;
                randomCharacterGender = false;
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
                panel.remove(professionChoice);
                displayProfessionOptions();
            }
        });

        eraModern = new JRadioButton("Modern");
        eraModern.setBounds(10, 175, 75, 25);
        eraModern.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                characterEra = CharacterEra.MODERN;
                panel.remove(professionChoice);
                displayProfessionOptions();
            }
        });

        ButtonGroup eraGroup = new ButtonGroup();
        eraGroup.add(era1920s);
        eraGroup.add(eraModern);

        panel.add(era1920s);
        panel.add(eraModern);
    }

    private static void displayCharacterAgeSelection() {
        JLabel ageLabel = new JLabel("Age");
        ageLabel.setBounds(10, 200, 50, 25);
        panel.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(50, 200, 50, 25);
        panel.add(ageField);
    }

    private static void displayProfessionOptions() {
        ArrayList<String> professionOptions = new ArrayList<String>();
        professionOptions.add("none");

        try {
            FileInputStream fis = new FileInputStream("src/main/models/data/CoCoccupations.txt");
            Scanner scan = new Scanner(fis);
            while(scan.hasNextLine()) {
                String line;
                if (era1920s.isSelected() && scan.nextLine() != "Hacker") {
                    line = scan.nextLine();
                } else if (eraModern.isSelected()) {
                    line = scan.nextLine();
                } else {
                    continue;
                }
                professionOptions.add(line);
            }
            String[] professionOptionsArray = new String[professionOptions.size()];
            for (int i = 0; i < professionOptions.size(); i++) {
                professionOptionsArray[i] = professionOptions.get(i);
            }
            professionChoice = new JComboBox<>(professionOptionsArray);
            professionChoice.setBounds(10, 235, 150, 25);
            panel.add(professionChoice);
            professionChoice.setSelectedIndex(0);
            scan.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }  

        
    }

    private static void showPreviousCharacters() {
        
        int xPosition = 0;
        int yPosition = 0;

        for (int i = 0; i < characterList.size(); i++) {
            CoCCharacter character = (CoCCharacter)characterList.get(i);
            JButton characterButton = new JButton(character.getName());

            characterButton.setBounds(xPosition, yPosition, 200, 25);
            

            final int x = i;
            characterButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cocCharacterInfo.setText(characterList.get(x).toString());
                    cocCharacterInfo.setCaretPosition(0);
                }
            });

            prevCharPanel.add(characterButton);
            yPosition += 30;
        }

        prevCharPanel.revalidate();
        prevCharPanel.repaint();
        panel.revalidate();
        panel.repaint();
    }

    private static void displaySkillLimitEntry() {
        JLabel skillLimitLabel = new JLabel("Skill Limit");
        skillLimitLabel.setBounds(10, 270, 150, 25);
        panel.add(skillLimitLabel);

        skillLimitField = new JTextField();
        skillLimitField.setBounds(75, 270, 50, 25);
        panel.add(skillLimitField);

        JLabel occupationalLimitLabel = new JLabel("Occupational Skill Limit");
        occupationalLimitLabel.setBounds(10, 300, 200, 25);
        panel.add(occupationalLimitLabel);

        occupationSkillLimitField = new JTextField();
        occupationSkillLimitField.setBounds(150, 300, 50, 25);
        panel.add(occupationSkillLimitField);

    }

    private static void displayGenerateBackgroundCheckbox() {
        generateBackground = new JCheckBox("Generate Background", true);
        generateBackground.setBounds(10, 325, 200, 50);
        panel.add(generateBackground);
    }
}