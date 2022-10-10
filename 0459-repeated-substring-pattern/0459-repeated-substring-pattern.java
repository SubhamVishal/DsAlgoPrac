class Solution {
    public boolean repeatedSubstringPattern(String st) {
        int l=st.length();
        int Pi[]=new int[l];
        int p=0;
        int i=1;
         while(i<l){
            if(st.charAt(p)==st.charAt(i)){
                Pi[i]=++p;
                i++;
            }else{
                if(p!=0){
                    p=Pi[p-1];
                }else{
                    Pi[i]=0;
                    i++;
                }
            }
            
        }
     
       return (Pi[l-1]>=(l-Pi[l-1]) && l%(l-Pi[l-1])==0) ? true:false;
      
    }
}