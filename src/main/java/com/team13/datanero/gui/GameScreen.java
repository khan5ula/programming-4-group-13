package com.team13.datanero.gui;

import javax.swing.*;

import com.team13.datanero.backend.Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.border.Border;


public class GameScreen extends JPanel {
    private MainFrame mainFrame;
    private Game game;
    private JButton[] answerButtons;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JTextArea questionTextArea;
    private JLabel mascotLabel;

    public GameScreen(MainFrame mainFrame, Game game) {
        this.mainFrame = mainFrame;
        this.game = game;
        this.mascotLabel = new JLabel();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        Insets padding = new Insets(10, 10, 10, 10);

        /* Set an empty border with with a padding around the panel */
        Border borderPadding = BorderFactory.createEmptyBorder(160, 100, 100, 100);
        setBorder(borderPadding);

        // Load the custom font from the file
        File font_file = new File("src/main/java/com/team13/datanero/fonts/FiraCode-Light.ttf");
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the backup font
        Font backupFont = new Font("Arial", Font.BOLD, 48);

        // Derive the custom font with the desired style and size (if font is not null)
        Font customFont = font != null ? font.deriveFont(Font.BOLD, 48) : null;

        /* Create text area for the question */
        questionTextArea = new JTextArea(game.getCurrentQuestion());
        questionTextArea.setFont(font != null ? customFont : backupFont); // Set the custom font or backup font
        questionTextArea.setLineWrap(true);
        questionTextArea.setWrapStyleWord(true);
        questionTextArea.setEditable(false);
        questionTextArea.setBackground(null);
        questionTextArea.setOpaque(false);
        questionTextArea.setFocusable(false);


        /* Create answer buttons */
        answerButtons = new JButton[4];
        String[] answers = game.getAnswersForCurrentQuestion();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i] = new CustomButton(answers[i], Color.BLUE);
            answerButtons[i].addActionListener(new AnswerButtonListener(i));
        }

        /* Create a list of the answer buttons and shuffle it */
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(answerButtons[0]);
        buttons.add(answerButtons[1]);
        buttons.add(answerButtons[2]);
        buttons.add(answerButtons[3]);

        /* Shuffle the buttons */
        Collections.shuffle(buttons);

        /* Create labels for score and lives */
        scoreLabel = new JLabel("Pisteet: " + game.getScore());
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 32));
        livesLabel = new JLabel("Elämät: " + game.getLives());
        livesLabel.setFont(new Font("Arial", Font.BOLD, 32));

        /* Create panel for score and lives */
        JPanel scoreAndLivesPanel = new JPanel(new GridBagLayout());
        GridBagConstraints scoreAndLivesPanelConstraints = new GridBagConstraints();

        /* Add score label */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 0;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(scoreLabel, scoreAndLivesPanelConstraints);

        /* Add lives label */
        scoreAndLivesPanelConstraints.gridx = 0;
        scoreAndLivesPanelConstraints.gridy = 1;
        scoreAndLivesPanelConstraints.anchor = GridBagConstraints.NORTHWEST;
        scoreAndLivesPanelConstraints.insets = padding;
        scoreAndLivesPanel.add(livesLabel, scoreAndLivesPanelConstraints);

        /* Add score & lives label to the grid */
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span 2 columns
        gbc.gridheight = 2; // Span 2 rows
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = padding;
        add(scoreAndLivesPanel, gbc);
        
        /* Add the question label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 9; // Span the entire width
        gbc.gridheight = 3; // Occupy the top 4 rows
        gbc.weightx = 1.0;
        gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH; // Make the component fill the grid cell
        gbc.insets = padding;
        add(questionTextArea, gbc);

        /* Add answer buttons to grid */
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        for (int i = 0; i < buttons.size(); i++) {
            gbc.gridx = 3 + (i % 2) * 3; // Calculate the gridx value for each button
            gbc.gridy = 4 + (i / 2) * 2; // Place the buttons below the question
            add(buttons.get(i), gbc);
        }

        /* Create exit button */
        JButton lopetaButton = new CustomButton("Lopeta", new Color(239, 71, 111));
        lopetaButton.setActionCommand("Lopeta");

        /* Define action for exit button */
        ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        lopetaButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 9;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = padding;
        add(lopetaButton, gbc);

        /* Load the mascot image */
        ImageIcon mascotIcon = new ImageIcon("src/main/java/com/team13/datanero/images/mascot_happy.png");

        /* Create JLabel to hold the image, and set the icon */
        this.mascotLabel.setIcon(mascotIcon);

        /* Add the Mascot JLabel to grid */
        gbc.gridx = 9; // Adjust the x-coordinate as needed
        gbc.gridy = 0; // Adjust the y-coordinate as needed
        gbc.gridwidth = 3; // You can adjust the width as needed
        gbc.gridheight = 10; // You can adjust the height as needed
        gbc.anchor = GridBagConstraints.EAST; // Center the mascot image
        gbc.insets = padding;
        add(mascotLabel, gbc);
    }

    /**
     * Method that updates the game screen elements.
     * Should be called after every turn to initialize a new turn.
     */
    private void updateGameDisplay() {
        String[] answers = game.getAnswersForCurrentQuestion();
        questionTextArea.setText(game.getCurrentQuestion());
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(answers[i]);
        }
        scoreLabel.setText("Pisteet: " + game.getScore());
        livesLabel.setText("Elämät: " + game.getLives());
        updateMascot();
    }

    /**
     * Method that updates the game mascot based on the success of the player.
     */
    private void updateMascot() {
        String imagePath;
        switch (game.getLives()) {
            case 3:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_happy.png";
                break;
            case 2:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_worried.png";
                break;
            case 1:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_terrified.png";
                break;
            case 0:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_terminated.png";
                break;
            default:
                imagePath = "src/main/java/com/team13/datanero/images/mascot_happy.png";
        }

        ImageIcon newMascotIcon = new ImageIcon(imagePath);
        this.mascotLabel.setIcon(newMascotIcon);
    }
    

    /** Private class used by Game Class.
     * Contains the actions for pressing buttons.
     */
    private class AnswerButtonListener implements ActionListener {
        private int answerIndex;

        public AnswerButtonListener(int answerIndex) {
            this.answerIndex = answerIndex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            boolean correct = game.submitAnswer(answerIndex);
            if (correct) {
                game.incrementScore();
            } else {
                game.decrementLives();
            }
    

            if (game.getLives() <= 0 || !game.areQuestionsAvailable()) {
                if (game.getLives() == 0) {
                    updateMascot();
                }
                /* Show the GameOverDialog with the score */
                GameOverScreen gameOverDialog = new GameOverScreen(mainFrame, game.getScore());
                gameOverDialog.setVisible(true);

                /* Switch back to the main menu and reset the game */
                mainFrame.switchTo("mainMenu");
                Game.resetInstance();
                mainFrame.setGame(Game.getInstance());
            } else {
                /* There are questions available, continue game */
                game.getNewQuestion();
                updateGameDisplay();
            }
        }
    }
}