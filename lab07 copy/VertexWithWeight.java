import java.util.*;

public class VertexWithWeight{
	private final Integer vertex;
	private Double weight;
	
	VertexWithWeight(int v, double w){
		this.vertex = v;
		this.weight = w;
	}
	
	//@Override
	public Integer getVertex() {
		return vertex;
	}

	//@Override
	public Double getWeight() {
		// TODO Auto-generated method stub
		return weight;
	}

	//@Override
	public void setWeight(double weight) {
		this.weight=weight;	
		
	}
	public String toString() {
		return "(" + vertex + "," + weight + ")";
	}
	public boolean equals(Object o) {
		if(o==null) {
			return false;
		}
		if(this == o) {
			return true;
		}
		if(getClass()==o.getClass()) {
			if(vertex.intValue() == ((VertexWithWeight) o).getVertex().intValue() ){
				return true;
			}
		}
		return false;
	}
}
