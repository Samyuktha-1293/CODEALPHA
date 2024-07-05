package CODEALPHA;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Wordcounter {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Word Counter");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(50, 50, 300, 100);
        frame.add(textArea);

        JLabel wordCountLabel = new JLabel("Word Count: 0");
        wordCountLabel.setBounds(50, 160, 300, 30);
        frame.add(wordCountLabel);

        JButton countButton = new JButton("Count Words");
        countButton.setBounds(150, 200, 120, 30);
        frame.add(countButton);

        countButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textArea.getText();
                int wordCount = countWords(text);
                wordCountLabel.setText("Word Count: " + wordCount);
            }
        });
        
        frame.setVisible(true);
    }

    public static int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        String[] words = text.trim().split("\\s+");
        return words.length;
    }
}
