import java.util.Scanner;

public class Battleship {

    public static void main(String[] args) {
        int gridSize = 8;
        char empty = '•';
        char hit = 'X';
        char miss = ' ';
        boolean isVertical = false;
        char[][] grid = new char[gridSize][gridSize];
        int[][] shipPositions = new int[3][2];
        int hits = 0;

        // Inizializzazione griglia
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = empty;
            }
        }

        // L'utente inserisce la nave
        Scanner scanner = new Scanner(System.in);
        System.out.println("Posiziona la tua nave di lunghezza 3.");
        System.out.print("Riga iniziale (da 0 a 7): ");
        int startRow = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Colonna iniziale (da 0 a 7): ");
        int startCol = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Inserire verticalmente? (sì/no): ");
        if (scanner.next().equalsIgnoreCase("sì")) {
            isVertical = true;
        }

        // Posizionamento nave
        if (isVertical) {
            for (int i = 0; i < 3; i++) {
                shipPositions[i][0] = startRow + i;
                shipPositions[i][1] = startCol;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                shipPositions[i][0] = startRow;
                shipPositions[i][1] = startCol + i;
            }
        }

        // Controllo che non esca dalla griglia
        if ((isVertical && startRow + 2 >= gridSize) || (!isVertical && startCol + 2 >= gridSize)) {
            System.out.println("La nave va oltre il bordo! Riprova.");
        }

        // Loop del game
        while (hits < 3) {
            // Stampa griglia
            System.out.print("  ");
            for (int i = 0; i < gridSize; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
            for (int i = 0; i < gridSize; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < gridSize; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }

            // Input utente
            System.out.print("Inserisci riga(da 0 a 7): ");
            int row = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Inserisci colonna(da 0 a 7):");
            int col = scanner.nextInt();
            scanner.nextLine();

            // Validazione input
            if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
                System.out.println("Mossa invalida. Per favore riprovare.");
                continue;
            }

            if (grid[row][col] == hit || grid[row][col] == miss) {
                System.out.println("Hai già provato questa mossa.");
                continue;
            }

            // Controllo se viene colpito
            boolean hitFound = false;
            for (int i = 0; i < 3; i++) {
                if (shipPositions[i][0] == row && shipPositions[i][1] == col) {
                    hitFound = true;
                    break;
                }
            }

            if (hitFound) {
                grid[row][col] = hit;
                hits++;
                if (hits == 3) {
                    System.out.println("Affondato!");
                } else {
                    System.out.println("Colpito!");
                }
            } else {
                grid[row][col] = miss;
                System.out.println("Mancato.");
            }
        }

        System.out.println("Tutte le navi sono state affondate!");
        scanner.close();
    }
}
