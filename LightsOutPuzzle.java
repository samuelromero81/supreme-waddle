import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LightsOutPuzzle extends JFrame {
    int N = 5;
    boolean[][] grid = new boolean[N][N];
    JButton[][] buttons = new JButton[N][N];
    public LightsOutPuzzle() {
        setTitle("Lights Out Puzzle");
        setSize(550, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(N,N));
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) {
                grid[i][j] = Math.random()>0.5;
                buttons[i][j] = new JButton();
                panel.add(buttons[i][j]);
                int x = i, y = j;
                buttons[i][j].addActionListener(e->{ press(x,y); });
            }
        updateButtons();
        add(panel, BorderLayout.CENTER);
        JButton reset = new JButton("Reset");
        reset.addActionListener(e->{
            for(int i=0;i<N;i++)for(int j=0;j<N;j++)grid[i][j]=Math.random()>0.5;
            updateButtons();
        });
        add(reset, BorderLayout.SOUTH);
        setVisible(true);
    }
    void press(int x, int y) {
        for (int dx=-1;dx<=1;dx++)
            for (int dy=-1;dy<=1;dy++)
                if (Math.abs(dx)+Math.abs(dy)==1) {
                    int nx=x+dx, ny=y+dy;
                    if(nx>=0&&nx<N&&ny>=0&&ny<N) grid[nx][ny]=!grid[nx][ny];
                }
        grid[x][y]=!grid[x][y];
        updateButtons();
        if (solved()) JOptionPane.showMessageDialog(this, "You solved it!");
    }
    void updateButtons() {
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++) {
                buttons[i][j].setBackground(grid[i][j]?Color.YELLOW:Color.GRAY);
            }
    }
    boolean solved() {
        for(int i=0;i<N;i++)for(int j=0;j<N;j++)if(grid[i][j])return false;
        return true;
    }
    public static void main(String[] args) { new LightsOutPuzzle(); }
}