class Solution {
    static final long MOD=1_000_000_007L;

    public int sumCounts(int[] nums) {
        int n=nums.length;
        long[] sum=new long[4*n];
        long[] sq=new long[4*n];
        long[] lazy=new long[4*n];
        int[] last=new int[100001];
        Arrays.fill(last,-1);

        long ans=0;

        for(int r=0;r<n;r++){
            int x=nums[r];
            int l=last[x]+1;
            update(1,0,n-1,l,r,sum,sq,lazy);
            last[x]=r;
            ans=(ans+sq[1])%MOD;
        }

        return (int)ans;
    }

    void update(int node,int start,int end,int l,int r,
                long[] sum,long[] sq,long[] lazy){
        if(l>end||r<start)return;

        if(l<=start&&end<=r){
            apply(node,start,end,1,sum,sq,lazy);
            return;
        }

        push(node,start,end,sum,sq,lazy);
        int mid=(start+end)/2;
        update(node*2,start,mid,l,r,sum,sq,lazy);
        update(node*2+1,mid+1,end,l,r,sum,sq,lazy);

        sum[node]=(sum[node*2]+sum[node*2+1])%MOD;
        sq[node]=(sq[node*2]+sq[node*2+1])%MOD;
    }

    void apply(int node,int start,int end,long val,
               long[] sum,long[] sq,long[] lazy){
        long len=end-start+1L;
        sq[node]=(sq[node]+2*val*sum[node]+len*val*val)%MOD;
        sum[node]=(sum[node]+len*val)%MOD;
        lazy[node]+=val;
    }

    void push(int node,int start,int end,
              long[] sum,long[] sq,long[] lazy){
        if(lazy[node]==0||start==end)return;

        int mid=(start+end)/2;
        apply(node*2,start,mid,lazy[node],sum,sq,lazy);
        apply(node*2+1,mid+1,end,lazy[node],sum,sq,lazy);
        lazy[node]=0;
    }
}