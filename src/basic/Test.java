package basic;

public class Test {
    public static void main(String[] args) {
        int[][] mat = {{1,1,0,0,0},{1,1,1,1,0},{1,0,0,0,0},{1,1,0,0,0},{1,1,1,1,1}};

        bubbleSort(mat,3);
    }

    static int[] bubbleSort(int[][] mat,int k){
        int N = mat.length;

        int[] ans = new int[N];
        int[] fires = new int[N];
        for(int i =0;i<N;i++){
            int[] cur = mat[i];
            int sum = 0;
            for(int j = 0;j<cur.length;j++){
                sum+=cur[j];
            }
            fires[i] = sum;
            ans[i] = i;
        }

        for(int i =0;i<N;i++){
            for(int second = i+1;second<N-i;second++){
                if(fires[second-1]>fires[second]){
                    swap(fires,ans,second-1,second);
                }
            }
        }

        int[] res = new int[k];
        for(int i=0;i<k;i++){
            res[i] = ans[i];
        }

        return res;
    }

    static void swap(int[] fires,int[] ans,int i, int j){
        int temp1 = fires[i];
        fires[i] = fires[j];
        fires[j] = temp1;

        int temp2 = ans[i];
        ans[i] = ans[j];
        ans[j] = temp2;
    }
}
