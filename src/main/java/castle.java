import java.io.*;
import java.util.*;

/*
ID: soldier7
LANG: JAVA
TASK: castle
*/
public class castle {

    public static void main(String[] args) throws IOException {
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("castle.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
//        long start = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(f.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        walls = new int[n][m];
        visited = new boolean[n][m];
        posRoom = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(f.readLine());
            for (int j=0; j<m; j++) {
                walls[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        traverse(n, m);
        out.println(roomList.size());
        out.println(maxRoom());
        tryRemove(n, m);
        out.println(max);
        out.println((x+1) + " " + (y+1) + " " + direction);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
        out.close();
    }

    static List<Set> roomList = new ArrayList<>();
    static int[][] walls;
    static boolean[][] visited;
    static int[][] posRoom;

    static int max;
    static int x;
    static int y;
    static char direction;

    static String encode(int i, int j) {
        return i+","+j;
    }

    static int maxRoom() {
        int max = 0;
        for (Set s : roomList) {
            if (max < s.size()) {
                max = s.size();
            }
        }
        return max;
    }

    static int tryRemove(int n, int m) {
        int max = 0;
        for (int j=0; j<m; j++) {
        for (int i=n-1; i>=0; i--) {
//                if (i == 3 && j ==0) {
//                    System.out.println(i + " " + j);
//                }
                removeOneWall(n, m, i, j);
            }
        }
        return max;
    }

    /*
    1: wall to the west
    2: wall to the north
    4: wall to the east
    8: wall to the south
     */
    static int removeOneWall(int n, int m, int i, int j) {
        int tmp;
        if (i>0
                && (walls[i][j] & 2) != 0
                && !isSameRoom(i, j, i-1, j)){
//                && (walls[i-1][j] & 8) == 0) {
            tmp = findRoomSize(i, j) + findRoomSize(i-1, j);
            if (max < tmp) {
                max = tmp;
                x = i;
                y = j;
                direction = 'N';
            }
        }
        if (j<m-1
                && (walls[i][j] & 4) != 0
                && !isSameRoom(i, j, i, j+1)){
//                && (walls[i][j+1] & 1) == 0) {
            tmp = findRoomSize(i, j) + findRoomSize(i, j+1);
            if (max < tmp) {
                max = tmp;
                x = i;
                y = j;
                direction = 'E';
            }
        }
        if (i<n-1
                && (walls[i][j] & 8) != 0
                && !isSameRoom(i, j, i+1, j)){
//                && (walls[i+1][j] & 2) == 0) {
            tmp = findRoomSize(i, j) + findRoomSize(i+1, j);
            if (max < tmp) {
                max = tmp;
                x = i;
                y = j;
                direction = 'S';
            }
        }
        if (j>0
                && (walls[i][j] & 1) != 0
                && !isSameRoom(i, j, i, j-1)){
//                && (walls[i][j-1] & 4) == 0) {
            tmp = findRoomSize(i, j) + findRoomSize(i, j-1);
            if (max < tmp) {
                max = tmp;
                x = i;
                y = j;
                direction = 'W';
            }
        }
        return max;
    }

    static int findRoomSize(int i, int j) {
        int pos = posRoom[i][j];
        return roomList.get(pos).size();
    }

    static boolean isSameRoom(int i, int j, int x, int y) {
        return posRoom[i][j] == posRoom[x][y];
    }

    static int[] decode(String str) {
        String[] tmp = str.split(",");
        int[] result = new int[2];
        for (int i=0; i<2; i++) {
            result[i] = Integer.parseInt(tmp[i]);
        }
        return result;
    }

    static void traverse(int n, int m) {
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                Set set = new HashSet();
                df(n, m, i, j, set, roomList.size());
                if (set.size() > 0) {
                    roomList.add(set);
                }
            }
        }
    }

    static void df(int n, int m, int i, int j, Set<String> set, int pos) {
        if (i<0 || i>= n || j<0 || j>=m) {
            return;
        }
        if (visited[i][j]) {
            return;
        }

        String code = encode(i, j);
        set.add(code);
        visited[i][j] = true;
        posRoom[i][j] = pos;

        /*
        1: wall to the west
        2: wall to the north
        4: wall to the east
        8: wall to the south
         */
        if ((walls[i][j] & 1) == 0) {
            df(n, m, i, j-1, set, pos);
        }
        if ((walls[i][j] & 2) == 0) {
            df(n, m, i-1, j, set, pos);
        }
        if ((walls[i][j] & 4) == 0) {
            df(n, m, i, j+1, set, pos);
        }
        if ((walls[i][j] & 8) == 0) {
            df(n, m, i+1, j, set, pos);
        }
    }
}
