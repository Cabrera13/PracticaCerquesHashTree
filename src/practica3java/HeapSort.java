package practica3java;


import java.util.ArrayList;

public class HeapSort<E extends Comparable<E>> {   
    public ArrayList<E> h;
    public int SIZE;
    public int n;
    
    public HeapSort(int s){
        SIZE=s;
        n=1;
        h=new ArrayList<>(SIZE);
        for(int i=0;i<SIZE;i++)
            h.add(null);
    }
    
    public void Insert(E e){
        h.set(n,e);
        n++;
        // swapping up
        if(n!=2){           // more than 1 element
            int s=n-1;      // last added element 
            int p=s/2;      // its parent
            while(s!=1 && LT(s,p)){
                swap(p,s);
                s=p;
                p=p/2;
            }
        }
    }
    
    public boolean Empty(){
        return n==1;
    }
    
    public E Lesser(){
        return h.get(1);
    }
    
    public void Delete(){
        n--;
        h.set(1,h.get(n));  // last element to the root
        h.set(n,null);
        // swapping down
        int ip=1;
        int ils=2;
        int irs=3;
        while(ConditionSwapDown(ip,ils,irs)){
            if(Exists(ils) && Exists(irs)){
                // two sons
                if(LT(ils,ip) || LT(irs,ip)){
                    // at least one child is lesser than the parent -> swap
                    if(LT(ils,irs)){
                        swap(ip,ils);   // swapping down left
                        ip=ils;
                        ils=ip*2;
                        irs=ils+1;
                    }
                    else{
                        swap(ip,irs);   // swapping down right
                        ip=irs;
                        ils=ip*2;
                        irs=ils+1;
                    }
                }
            }
            else
                // only left son
                if(Exists(ils) && !Exists(irs)){
                    if(LT(ils,ip)){     // left son is lesser than the parent
                        swap(ip,ils);
                        ip=ils;
                        ils=ip*2;
                        irs=ils+1;
                    }
                }
                // else no children and end of swapping down
        }
    }
    
    public boolean ConditionSwapDown(int p,int l,int r){
        // (has 2 children and (parent>left or parent>right)) or
        // (has 1 children and parent>left)
        return (((Exists(l) && Exists(r)) && (LT(l,p) || LT(r,p)))) ||
        (Exists(l) && !Exists(r) && LT(l,p));
    }
    
    public boolean Exists(int p){
        if(p>=SIZE)
            return false;
        return h.get(p)!=null;
    }
    
    public void swap(int a,int b){
        E c=h.get(a);
        h.set(a,h.get(b));
        h.set(b,c);
    }
    
    public boolean LT(int a,int b){     // h[a]<h[b]
        return h.get(a).compareTo(h.get(b))<0;
    }
    
    @Override
    public String toString(){
        String r="";
        for(int i=1;i<n;i++)
            r+=h.get(i)+" ";
        return r;
    }

    public String Print(){
        return PrintR(1,"");
    }
    public String PrintR(int i,String p){
        String r=p+h.get(i)+"\n";
        if(h.get(i*2)!=null || h.get(i*2+1)!=null){
            if(h.get(i*2)!=null)
                r+=PrintR(i*2,p+"   ");
            else
                r+=p+"   -\n";
            if(h.get(i*2+1)!=null)
                r+=PrintR(i*2+1,p+"   ");
            else
                r+=p+"   -\n";
        }
        return r;
    }
}
