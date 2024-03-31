package controllers.listeners;

import helpers.GameTimer;
import models.Model;
import views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ButtonSend implements ActionListener {
    private final Model model;
    private final View view;


    public ButtonSend(Model model, View view) {
        this.model = model;
        this.view = view;
        //GameTimer gameTime = view.getGameTime();
        //System.out.println("Constructor gameTime object: " + this.gameTime);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        view.getTxtChar().requestFocus(); // After pressing New Game, the input box becomes active
        String enteredChar = view.getTxtChar().getText().toUpperCase();
        if (enteredChar.isEmpty()) {
            return;
        }

        char guessedLetter = enteredChar.charAt(0);
        String guessWord = model.getWordToGuess();
        String[] guessList = guessWord.split("");
        System.out.println("GuessList: " + Arrays.toString(guessList));

        checkGuess(guessList, guessedLetter, enteredChar);

        view.getTxtChar().setText("");
        checkGameStatus();
    }

    /**
     * Kontrollib, kas sisestatud täht on õige või vale
     */
    public void checkGuess(String[] guessList, char guessedLetter, String enteredChar) {
        boolean correct = false;
        for (int i = 0; i < guessList.length; i++) {
            if (guessList[i].equals(enteredChar)) {
                model.getHiddenWord().setCharAt(i, guessedLetter);
                view.getLblResult().setText(model.addSpaceBetween(model.getHiddenWord().toString()));
                correct = true;
            }
        }
        handleIncorrectGuess(enteredChar, correct);
        model.setCountMissedWords(model.getMissedLetters().size());
        view.getLblError().setText("Valesti: " + model.getCountMissedWords() + " täht(e) " + model.getMissedLetters());
        view.getGameImages().updateImage();
    }

    /**
     * Kontrollib, kas sisestatud täht on õige või vale
     */

    private void handleIncorrectGuess(String enteredChar, boolean correct) {
        if (!correct) {
            model.getMissedLetters().add(enteredChar);
            view.getLblError().setForeground(Color.BLACK);
            model.setCountMissedWords(model.getCountMissedWords() + 1);
            view.getGameImages().updateImage();
        } else {
            view.getLblError().setForeground(Color.BLACK);
        }
    }

    /**
     * Kontrollib, kas mängija võitis või kaotas
     */
    private void checkGameStatus() {
        if (!model.getHiddenWord().toString().contains("_")) {

            JOptionPane.showMessageDialog(null, "Võitsid mängu", "Mäng läbi", JOptionPane.PLAIN_MESSAGE);
            //System.out.println("checkGameStatus gameTime object: " + gameTime);
            view.getGameTime().stopTimer();
            int playedTimeInSeconds = view.getGameTime().getPlayedTimeInSeconds();
            model.setGametime(playedTimeInSeconds);

            model.askPlayerName();
            model.getPlayerName();
            model.insertScoreToTable();

            view.setEndGame();
            return;

        }
        if (!(model.getCountMissedWords() < 11)) {
            JOptionPane.showMessageDialog(null, "Kaotasid mängu", "Mäng läbi", JOptionPane.PLAIN_MESSAGE);
            view.setEndGame();
        }
    }
}
