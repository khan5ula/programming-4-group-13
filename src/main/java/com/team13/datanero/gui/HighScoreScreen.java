package com.team13.datanero.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.team13.datanero.backend.HighScore;
import com.team13.datanero.gui.Theme.FontStyle;

public class HighScoreScreen extends JPanel {
    private final MainFrame mainFrame;
    private JLabel header;
    private final Theme theme;
    private JButton exitButton;

    public HighScoreScreen(final MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.theme = Theme.getInstance();
        setBackground(theme.getScreenBackGroundColor());
        init();
    }

    /**
     * Method that initializes the High Score screen elements.
     */
    private void init() {
        setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();

        /* Add padding to the screen */
        Border borderPadding = BorderFactory.createEmptyBorder(300, 100, 0, 100);

        setBorder(borderPadding);
        setHeader(gbc);
        setHighScoreButtons(gbc);
        setExitButton(gbc);
    }

    /**
     * Method that creates the header for the High Score Screen.
     * 
     * @param gbc GridBagConstraints used for High Score Screen elements.
     */
    private void setHeader(final GridBagConstraints gbc) {
        /* Create Header label */
        this.header = new JLabel("Parhaat tulokset");
        header.setFont(theme.getCustomFont(FontStyle.BOLD, 84));

        /* Add Header label to the grid */
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 200, 10);
        add(header, gbc);
    }

    /**
     * Method that creates an exit button that allows the player to move to main
     * menu.
     * 
     * @param gbc GridBagConstraints used on the High Score screen.
     */
    private void setExitButton(final GridBagConstraints gbc) {
        /* Create exit button */
        this.exitButton = new CustomButton("Palaa päävalikkoon", theme.getQuitGameButtonColor(), 32, FontStyle.BOLD);
        this.exitButton.setActionCommand("Palaa päävalikkoon");
        exitButton.setPreferredSize(new Dimension(500, 120));
        exitButton.setMaximumSize(new Dimension(500, 120));

        /* Define action for exit button */
        final ButtonActions buttonActions = new ButtonActions(this.mainFrame);
        exitButton.addActionListener(buttonActions);

        /* Add the exit button to the grid */
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(exitButton, gbc);
    }

    private void setHighScoreButtons(GridBagConstraints gbc) {
        for (int i = 0; i < 5; i++) {
            /* Create button for nickname */
            JButton nicknameButton = new CustomButton("Nickname" + (i + 1), Color.WHITE, 32,
                    FontStyle.RETINA);
            nicknameButton.setForeground(Color.BLACK);
            nicknameButton.setPreferredSize(new Dimension(400, 60));
            nicknameButton.setMaximumSize(new Dimension(400, 60));

            /* Create button for score */
            JButton scoreButton = new CustomButton("Score " + (i + 1), Color.WHITE, 32,
                    FontStyle.RETINA);
            scoreButton.setForeground(Color.BLACK);
            scoreButton.setPreferredSize(new Dimension(200, 60));
            scoreButton.setMaximumSize(new Dimension(200, 60));

            /* Set gbc and add nickname button */
            gbc.gridx = 0;
            gbc.gridy = i + 1;
            gbc.gridwidth = 1;
            gbc.weightx = 1.0;
            gbc.weighty = 0.0;
            gbc.anchor = GridBagConstraints.LINE_END;
            gbc.insets = new Insets(10, 10, 10, 10);
            add(nicknameButton, gbc);

            /* Set gbc and add score button */
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.insets = new Insets(10, 0, 10, 10);
            add(scoreButton, gbc);
        }
    }

    /**
     * Method that sets theme for High Score screen.
     */
    public void setTheme() {
        setBackground(theme.getScreenBackGroundColor());
        this.header.setForeground(theme.getGeneralTextColor());
        this.exitButton.setBackground(theme.getQuitGameButtonColor());
    }

}