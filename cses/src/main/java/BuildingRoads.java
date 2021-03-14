import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class BuildingRoads {

    public static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));


    public static void main(String... args) throws IOException {
        Reader sc = new Reader();

        int cities = sc.nextInt();
        int roads = sc.nextInt();

        Set<Integer> visitedCities = new HashSet<>();
        HashMap<Integer, List<Integer>> adjacenyList = new HashMap<>();

        // populate the map
        int read = 0;
        for (int i = 1; i <= roads; i++) {

            int fromCity = sc.nextInt();
            int toCity = sc.nextInt();

            if (adjacenyList.containsKey(fromCity)) {
                adjacenyList.get(fromCity).add(toCity);
            } else {

                adjacenyList.put(fromCity, new LinkedList<>(List.of(toCity)));
            }

            if (adjacenyList.containsKey(toCity)) {
                adjacenyList.get(toCity).add(fromCity);
            } else {
                adjacenyList.put(toCity, new LinkedList<>(List.of(fromCity)));
            }
        }
        // run dfs for each unvisited node

        List<Integer> answer = new ArrayList<>();
        int totalComponents = 0;


        for (int city = 1; city <= cities; city++) {

            if (!adjacenyList.containsKey(city)){
                totalComponents += 1;
                answer.add(city);
            }
            else{
                if (!visitedCities.contains(city)) {
                    runDfsExplore(city, adjacenyList, visitedCities);
                    totalComponents += 1;
                    answer.add(city);
                }
            }
        }

        if (totalComponents > 1) {
            out.print(totalComponents - 1);
            out.print("\n");
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < answer.size(); i++) {
                out.print(answer.get(i-1));
                out.print(" ");
                out.print(answer.get(i));
                out.print("\n");
            }
            out.flush();
        } else {
            System.out.println(0);
        }
    }

    public static void runDfsExplore(int city, HashMap<Integer, List<Integer>> adjacencyList,
        Set<Integer> visited) {

        Stack<Integer> stck = new Stack<>();
        stck.push(city);

        while (!stck.isEmpty()) {
            int newCity = stck.pop();
            if (visited.contains(newCity)) {
                continue;
            } else {
                visited.add(newCity);
                List<Integer> adjacentCities = adjacencyList.get(newCity);
                if (adjacentCities != null && adjacentCities.size() > 0) {

                    for (int adjacentCity : adjacentCities) {
                        if (!visited.contains(adjacentCity)) {
                            stck.push(adjacentCity);
                        }
                    }
                }
            }
        }
    }
    //---------

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
