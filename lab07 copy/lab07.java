import java.util.*;

public class lab07{
	public static void main(String[] args) {
		
		PriorityQueue<VertexWithWeight> p = new PriorityQueue<>(new VertexWithWeightWeightComparator());
		
		VertexWithWeight[] vww = new VertexWithWeight[10];
		vww[0] = new VertexWithWeight(0,0.0);
		vww[1] = new VertexWithWeight(1,1.0);
		vww[2] = new VertexWithWeight(2,2.0);
		vww[3] = new VertexWithWeight(3,3.0);
		vww[4] = new VertexWithWeight(4,4.0);
		vww[5] = new VertexWithWeight(5,0.0);
		vww[6] = new VertexWithWeight(6,1.0);
		vww[7] = new VertexWithWeight(7,2.0);
		vww[8] = new VertexWithWeight(8,3.0);
		vww[9] = new VertexWithWeight(9,4.0);
		
		//1
		for(int i=0;i<vww.length; i++) {
			p.add(vww[i]);
		}
		
		Iterator<VertexWithWeight> i = p.iterator(); //Iterator
		System.out.println("iterator after initial addition");
		 while (i.hasNext()) {
	            System.out.println(i.next());
	        }
		 VertexWithWeight[] vwwa;
			vwwa = p.toArray(new VertexWithWeight[0]); //toArray
			System.out.println("toArray after initial addition");
			
			for(int t=0;t<vwwa.length; t++) {
			 System.out.println(vwwa[t]);
			}
			
			 System.out.println("poll after initial addition");
			 for(int z=0;z<vww.length; z++) { //Poll
				 System.out.println(p.poll());
			 }
			 
			 
		//2	
		for(int k=0;k<vww.length; k++) {
			p.add(vww[k]);
		}
		
		vww[0].setWeight(10.0);
		
		System.out.println("iterator after initial modification");
		Iterator<VertexWithWeight> it = p.iterator();
		 while (it.hasNext()) {//Iterator
	            System.out.println(it.next());
	        }
		 
		 vwwa = p.toArray(new VertexWithWeight[0]); //toArray
		 System.out.println("toArray after initial modification");
		 for(int t=0;t<vwwa.length; t++) {
			 System.out.println(vwwa[t]);
			}
		
		 System.out.println("poll after initial modification");
		 for(int z=0;z<vww.length; z++) { //Poll
			 System.out.println(p.poll());
		 }
		 
		 
		//3 
		 vww[0].setWeight(0.0);
		 for(int k=0;k<vww.length; k++) {
				p.add(vww[k]);
			}
		 p.remove(vww[0]);
		 
		 vww[0].setWeight(10.0);
		 
		 p.add(vww[0]);
		 System.out.println("poll after deletion, modification, and addition");
		 for(int z=0;z<vww.length; z++) { //Poll
			 System.out.println(p.poll());
		 }
		 
	}
}
