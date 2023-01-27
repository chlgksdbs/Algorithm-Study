

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS {

    static int sum = 0;
    static int N;
    static int M;
    static char[][] iceStructure;
    static int[][] vector = {{1,0},{0,1},{-1,0},{0,-1}};
    // static int[][] length;

    public static int bfs(){

        Queue<int[]> queue = new LinkedList<>();

        int[] firstIndex = {0,0};
        queue.offer(firstIndex);
        char currentLength = 1;
        // length[0][0] = 1;

        while(true){

            int[] currentIndex = queue.poll();
            int currentI = currentIndex[0];
            int currentJ = currentIndex[1];
            if(currentI == N-1 && currentJ == M-1){
                return iceStructure[currentI][currentJ];
            }
            currentLength = iceStructure[currentI][currentJ];
            // iceStructure[currentI][currentJ] = '0'+1;


            

            for(int index = 0; index < 4; index++){
                int[] nextIndex = {currentI+vector[index][0],currentJ+vector[index][1]};
    
                if(nextIndex[0] >= 0 && nextIndex[0] < N && nextIndex[1] >= 0 && nextIndex[1] < M){
                    if(iceStructure[nextIndex[0]][nextIndex[1]] == '1'){
                        iceStructure[nextIndex[0]][nextIndex[1]] = currentLength;
                        iceStructure[nextIndex[0]][nextIndex[1]]++;

                        queue.offer(nextIndex);
                    }
                        
                }
                
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        StringTokenizer st = new StringTokenizer(line);

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        iceStructure = new char[N][M];
        // length = new int[N][M];
        
        for(int i = 0; i < N; i++){
            line = br.readLine();

            for(int j = 0; j < M; j++){
                iceStructure[i][j] = line.charAt(j);
            }
        }

        System.out.println(bfs()-'0');

    }
}