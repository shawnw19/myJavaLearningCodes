package AlgebraicGeometry;
/*
a shortest path implementation
adapted from [C++ Programming with Applications in Administration, Finance and Statistics]
pp359 -361. It demonstrates a simple route-finding principle:
with an adjacency matrix, find shortest path within maximum 2 vertices
(max 1 intermediate vertex allowed).
The distances are directed.
 */
public class ShortestPath1 {

    public static void main(String[] args) {
        int n = 6; //num of vertices
        int i, j, minDist;
        //
        int[][] C;//adjacency matrix of distances
        int[] dist = new int[n];//distance of shortest path
        int[] path = new int[n];//through n-th vertex is nearest, 0th if direct

        int inf = 10000;//infinity

        C = new int[][]{{0, 30, inf, 50, 40, 100},
                {inf,0, 40, inf, inf, inf},
                {inf, inf, 0, inf, 10, 30},
                {inf, inf,10, 0, inf,inf},
                {inf,inf, inf, 20, 0, 70},
                {inf, inf, inf, inf, inf, 0} };

        for (j = 0; j <n ; j++) {
            dist[j] = C[0][j];
            if(C[0][j]==inf){
                path[j] =-1;//no path
            }else
                path[j] =0;//direct path
        }

        for (i = 1; i <n ; i++) {//between 0th and ith vertex
            minDist = C[0][i];

            for (j = 1; j< n; j++) {
                if( C[0][j] + C[j][i]< minDist){//find(S,n,j) &&
                    minDist =C[0][j] + C[j][i];
                    path[i] = j;
                }
            }

            dist[i] = minDist;
        }

        //output
        for (i = 0; i<n; i++) {
            System.out.print("dist["+i+"]= "+dist[i]+" ");
        }
        System.out.println();
        for (i = 0; i<n; i++) {
            System.out.print("path["+i+"]= "+path[i]+" ");
        }

    }
}


/*
    static boolean find(int[] S, int n, int x){
        boolean found=false;
        for (int k = 0; k <n ; k++) {
            if(x==S[k])
                found = true;
        }
        return found;
    }

int[] S = new int[n];

        for (i = 0; i <n ; i++) {
            S[i] =0;
        }
for (j = 0; j <n ; j++) {
                if( dist[j] < minDist){//find(S,n,j) &&
                    minDist = dist[j];
                    w = j;
                }
            }
            S[i] = w;

for (i = 0; i<n; i++) {
            System.out.print("S["+i+"]= "+S[i]+" ");
        }
 */