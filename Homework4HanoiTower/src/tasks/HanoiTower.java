package tasks;

/*
 * Ifj. Dravecz Tibor
 * 
 */
public class HanoiTower {

    static final int x = 5;

    static final long startTime = System.nanoTime();
    static long endTime;
    static int[][] tower = new int[3][x];
    static final int mainTower = 0;
    static final int helpTower = 1;
    static final int destinationTower = 2;
    static int counter = 0;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        printall();
        setFirstTower();
        printall();
        int i;
        if (x % 2 == 1)
            i = 2;
        else
            i = 1;
        while (checkFinish()) {
            displacer(i);
            if (x % 2 == 1) {
                if (i == 0)
                    i = 2;
                else
                    i--;
            } else {
                if (i == 2)
                    i = 0;
                else
                    i++;
            }
            printall();
            counter++;
        }
        printall();
        System.out.println("Counter: " + counter);
    }

    private static void displacer(int setTower) {
        getANDsetValue(getOne(), setTower);
        moveSecond(setTower);
    }

    private static int getOne() {
        if (getTop(0) == 1)
            return 0;
        else if (getTop(1) == 1)
            return 1;
        else
            return 2;
    }

    private static void moveSecond(int smallTower) {
        int saveNumber0 = 0;
        int saveNumber1 = 0;
        int saveNumber2 = 0;
        if (smallTower != 0) {
            saveNumber0 = getTop(0);
        }
        if (smallTower != 1) {
            saveNumber1 = getTop(1);
        }
        if (smallTower != 2) {
            saveNumber2 = getTop(2);
        }
        if (smallTower == 0) {
            if (saveNumber1 > saveNumber2)
                getANDsetValue(2, 1);
            else
                getANDsetValue(1, 2);
        } else if (smallTower == 1) {
            if (saveNumber0 > saveNumber2)
                getANDsetValue(2, 0);
            else
                getANDsetValue(0, 2);
        } else {
            if (saveNumber0 > saveNumber1)
                getANDsetValue(1, 0);
            else
                getANDsetValue(0, 1);
        }
    }

    private static int getTop(int towerNumber) {
        for (int i = x - 1; i >= 0; i--) {
            if (tower[towerNumber][i] != 0) {
                return tower[towerNumber][i];
            }
        }
        return x;
    }

    private static boolean checkFinish() {
        boolean rtn = true;
        for (int i = 0; i < x; i++) {
            if (tower[destinationTower][i] == x - i)
                rtn = false;
            else
                rtn = true;
        }
        return rtn;
    }

    private static void getANDsetValue(int getTower, int setTower) {
        int save = 0;
        if (tower[setTower][x - 1] == 0) {
            for (int i = 0; i < x; i++) {
                if (tower[getTower][i] != 0) {
                    if (i != x - 1) {
                        if (tower[getTower][i + 1] == 0) {
                            save = tower[getTower][i];
                            tower[getTower][i] = 0;
                        }
                    } else {
                        save = tower[getTower][i];
                        tower[getTower][i] = 0;
                    }
                }
            }
            if (save != 0) {
                for (int i = 0; i < x; i++) {
                    if (tower[setTower][i] == 0) {
                        tower[setTower][i] = save;
                        break;
                    }
                }
            }
        }
    }

    private static void setFirstTower() {
        for (int i = 0; i < x; i++) {
            tower[mainTower][i] = x - i;
        }
    }

    private static void printall() {
        print(0);
        print(1);
        print(2);
        endTime = System.nanoTime();
        System.out.println("Time:   " + (endTime - startTime) + "ns");
    }

    private static void print(int towerNumber) {
        System.out.print("Tower " + (towerNumber + 1) + ": ");
        for (int i = 0; i < x; i++) {
            if (tower[towerNumber][i] != 0) {
                System.out.print(tower[towerNumber][i] + " ");
            } else
                break;
        }
        System.out.println();
    }
}
