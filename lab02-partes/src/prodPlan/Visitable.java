package prodPlan;

public interface Visitable {
	
	public Object accept(ProdPlanVisitor visitor);

}
