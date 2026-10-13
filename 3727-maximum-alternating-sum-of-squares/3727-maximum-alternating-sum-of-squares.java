import java.util.*;
class Solution{
    public long maxAlternatingSum(int[] nums){
        long[] a=new long[nums.length];

        for(int i=0;i<nums.length;i++) 
        a[i]=(long)nums[i]*nums[i];
        Arrays.sort(a);
        int n=a.length;

        long ans=0;

        for(int i=0;i<n/2;i++) ans-=a[i];
        for(int i=n/2;i<n;i++) ans+=a[i];
        return ans;
    }
}