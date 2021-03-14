import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class MessageRoute {

    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int computers = sc.nextInt();
        int connections = sc.nextInt();

        HashMap<Integer, Integer> level = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        HashMap<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        int sourceVertex = 1;
        int destinationVertex = computers;

        for (int i=1;i<=connections;i++){

            int fromComputer1 = sc.nextInt();
            int toComputer2 = sc.nextInt();

            if (adjacencyMap.containsKey(fromComputer1)){
                adjacencyMap.get(fromComputer1).add(toComputer2);
            }
            else{
                adjacencyMap.put(fromComputer1, new ArrayList<>(List.of(toComputer2)));
            }

            if (adjacencyMap.containsKey(toComputer2)){
                adjacencyMap.get(toComputer2).add(fromComputer1);
            }
            else{
                adjacencyMap.put(toComputer2, new ArrayList<>(List.of(fromComputer1)));
            }
        }

//        adjacencyMap.forEach((k,v)->{
//            System.out.println(k+","+v);
//        });

        if(bfs(sourceVertex, destinationVertex, level, parent, visited, adjacencyMap)){
            LinkedList<String> path = new LinkedList<>();
            System.out.println(level.get(destinationVertex)+1);
            int currentVertex = destinationVertex;

            while (currentVertex != -1){
                path.offerFirst(String.valueOf(currentVertex));
                currentVertex = parent.get(currentVertex);
            }

            System.out.println(String.join(" ", path));

        }else{
            System.out.println("IMPOSSIBLE");
        }

    }

    public static boolean bfs(int sourceVertex, int destinationVertex, HashMap<Integer, Integer> level,
        HashMap<Integer, Integer> parent, Set<Integer> visited, HashMap<Integer, List<Integer>> adjacencyMap){

        Queue<Integer> frontier = new LinkedList<>();
        level.put(sourceVertex, 0);
        parent.put(sourceVertex, -1);
        visited.add(sourceVertex);
        frontier.add(sourceVertex);

        while (!frontier.isEmpty()){

            int currentVertex = frontier.poll();
            //System.out.println("discovered " +currentVertex);

            if (currentVertex == destinationVertex){
                //System.out.println("found destination ");
                return true;
            }
            List<Integer> adjacentVertexes = adjacencyMap.get(currentVertex);

            if (adjacentVertexes != null && adjacentVertexes.size() >0 ){

                for(int adjacentVertex : adjacentVertexes){
                    if (!visited.contains(adjacentVertex)){
                        visited.add(adjacentVertex);
                        level.put(adjacentVertex, level.get(currentVertex)+1);
                        parent.put(adjacentVertex, currentVertex);
                        frontier.add(adjacentVertex);
                    }
                }
            }
        }

        return false;
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(
                new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
