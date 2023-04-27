

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TC_음료얼려먹기 {

    static int sum = 0;
    static char[][] iceStructure;
    static int[][] vector = {{1,0},{0,1},{-1,0},{0,-1}};

    public static boolean dfs(int i, int j){
        if(iceStructure[i][j] == '1'){
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();
        for(int index = 0; index < 4; index++){
            // vector[index][0];
            int[] nextIndex = {i+vector[index][0],j+vector[index][1]};

            if(iceStructure[nextIndex[0]][nextIndex[1]] == '0'){
                queue.offer(nextIndex);
            }
            
        }

        return true;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        iceStructure = new char[N][M];
        
        for(int i = 0; i < N; i++){
            line = br.readLine();

            for(int j = 0; j < M; j++){
                iceStructure[i][j] = line.charAt(j);
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(dfs(i,j)){
                    sum++;
                }
                // dfs(i,j);
            }
        }

        System.out.println(sum);
    }
}