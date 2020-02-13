import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> cpuPositions = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+','-', '+','-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+','-', '+','-'},
                {' ', '|', ' ', '|', ' '}
        };

        boolean gameOver = false, validMove = false;
        String winner = "";

        printGameBoard(gameBoard);

        Scanner sc = new Scanner(System.in);
        while(!gameOver) {
            System.out.println("Please enter a placement point (1-9)");
            int placement = sc.nextInt();
            placePiece(placement, gameBoard, "player");
            printGameBoard(gameBoard);

            placement = new Random().nextInt(9) + 1;
            System.out.println("CPU placed a point at : " + placement);
            placePiece(placement, gameBoard, "cpu");
            printGameBoard(gameBoard);

            String winningString = checkWinner();
            if(!winningString.equalsIgnoreCase("")) {
                System.out.println(winningString);
                gameOver = true;
            }
        }
    }

    public static void placePiece(int placement, char[][] gameBoard, String user) {
        char symbol = ' ';
        if(user.equalsIgnoreCase("cpu")) {
            symbol = 'O';
            cpuPositions.add(placement);
        } else if(user.equalsIgnoreCase("player")){
            symbol = 'X';
            playerPositions.add(placement);
        }

        switch (placement) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][0] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                System.out.println("Please enter a proper placement number!");
                break;
        }
    }

    public static String checkWinner() {

        List<Integer> topRow = Arrays.asList(1,2,3);
        List<Integer> midRow = Arrays.asList(4,5,6);
        List<Integer> bottomRow = Arrays.asList(7,8,9);
        List<Integer> leftCol = Arrays.asList(1,4,7);
        List<Integer> midCol = Arrays.asList(2,5,8);
        List<Integer> rightCol = Arrays.asList(3,6,9);
        List<Integer> cross1 = Arrays.asList(1,5,9);
        List<Integer> cross2 = Arrays.asList(3,5,7);

        List<List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(bottomRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List<Integer> winningCondition: winningConditions) {
            if(playerPositions.containsAll(winningCondition)) {
                return "Congratulations! You've won!";
            } else if(cpuPositions.containsAll(winningCondition)) {
                return "CPU wins! Sorry :(";
            } else if(playerPositions.size() + cpuPositions.size() == 9) {
                return "It's a draw!";
            }
        }
        return "";
    }

    public static void printGameBoard(char[][] gameBoard) {
        for(char[] row : gameBoard) {
            for(char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }
}
