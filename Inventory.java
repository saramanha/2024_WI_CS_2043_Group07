


public class Inventory{
	
	private String type;
	private int amount;

	public Inventory(String type, int amount){
		this.type = type;
		this.amount = amount;
	}
	

	public String getType(){
		return type;
	}

	public int getAmount(){
		return amount;
	}


	public void setAmount(int amountIn){
		amount = amountIn;
	}

}