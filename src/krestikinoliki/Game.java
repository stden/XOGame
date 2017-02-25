package krestikinoliki;

import javax.swing.JButton;

/**
 * Хранит состояние игры, проверяет чей ход, кто выйграл и т.д.
 */
public class Game {
    final int SIZE = 3;
    
    public char move = 'X';

    public String result = "";
    
    // Счёт
    public int scoreX = 0, scoreO = 0;

    public JButton[][] cells = new JButton[3][3];

    void newGame() {
        for (JButton[] row : cells) {
            for (JButton cell : row) {
                cell.setText(" ");
                cell.setEnabled(true);
            }
        }
        move = 'X';
        result = "";
    }

    void doMove(int row, int col) {
        cells[row][col].setText("" + move);
        cells[row][col].setEnabled(false);

        char[][] field = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = cells[i][j].getText().charAt(0);
            }
        }
        // ' ' - пустая ячейка
        // 'X' или 'O' - крестики или нолики

        // Проверка на выйгрыш или пройгрыш
        // Проверка горизонтальной линии
        if (field[row][0] == field[row][1]
                && field[row][1] == field[row][2]) {
            win();
            return;
        }
        // Проверка вертикальной линии 
        if (field[0][col] == field[1][col]
                && field[1][col] == field[2][col]) {
            win();
            return;
        }
        // Проверка диагоналей
        // ----------------
        //  0,0 | 0,1 | 0,2
        // ----------------
        //  1,0 | 1,1 | 1,2
        // ----------------
        //  2,0 | 2,1 | 2,2
        // ---------------- 
        // Главная диагональ
        if (row == col) // Мы походили на диагональ
        {
            if (field[0][0] == field[1][1]
                    && field[1][1] == field[2][2]) {
                win();
                
                return;
            }
        }
        // Обратная диагональ
        if((row + col) == SIZE - 1){
            if (field[0][2] == field[1][1]
                    && field[1][1] == field[2][0]) {
                win();
                return;
            }
        }

        // Проверка на ничью 
        // Если все клетки заняты, то наступила ничья
        boolean draw = true; // Сначала предположим что ничья
          // А если найдём хоть одну пустую клетку, то сбросим эту переменную 
          // в false
        for (int i = 0; i < SIZE; i++) 
            for (int j = 0; j < SIZE; j++)
                if(field[i][j] == ' '){
                    draw = false; // Я нашёл пустую клетку!
                    break;
                }
        if(draw){ // Если всё же ничья, т.е. мы не нашли ни одной пустой клетки
            gameOver("Ничья!");
            return;
        }
                 
        
        if (move == 'X') {
            move = 'O';
        } else {
            move = 'X';
        }

    }

    private void win() {
        gameOver("Выйграли " + move);
        if(move == 'X')
            scoreX++;
        if(move == 'O')
            scoreO++;
    }

    private void gameOver(String res) {
        result = res;
        // Блокируем все поля
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 cells[i][j].setEnabled(false);
            }
        }
    }
}
