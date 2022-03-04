
public abstract class Property extends GameObject {

	// Sahiplenilebilir tüm mülkleri temsil eder.
	// abstract class: Kodun herhangi bir yerinde bu classýn objesi oluþturulmuyor.
	
	protected Player owner;
	// mülkün sahibini tutmak için
	
	protected boolean hasOwner;
	// mülkün sahiplenilmiþ olup olmadýðýný tutmak için
	
	public Property(String type, String title, String description, int id) 
	{
		super(type, title, description, id);
		// bu classýn constructorýna verilen type, title, description ve id olduðu gibi GameObject'in constructorýna veriliyor. bkz. GameObject.java constructor
		
		owner = null;
		hasOwner = false;
		// baþlangýçta her mülk sahipsiz. Bu nedenle owner = null ve hasOwner = false
		
	}
	
	public Player getOwner() 
	{
		return owner;
	}
	
	public void setOwner(Player player) 
	{
		// eðer bu metoda null verilirse, mülk sahipsizleþtirilmiþ olur. bkz: if (player == null) hasOwner = false;
		
		
		owner = player;
		if (player == null) 
			hasOwner = false;
	}
	
	public boolean isOwned() 
	{
		return hasOwner;
	}
	
	public void assignOwner(Player player) 
	{
		// mülke sahip atar.
		setOwner(player);
		hasOwner = true;
	}

	@Override
	public String getInformation() 
	{
		
		String info = super.getInformation();
		// GameObject classýnýn getInformation metodunun döndürdüðü stringe mülkün sahip bilgisini aþaðýda ekler ve oluþan info Stringini döndürür.
		
		
		if (!hasOwner) 
		{
			info += "This property does not have an owner.\n";
		}
		else 
		{
			info += "Owner of this place is " + owner + "\n";
		}

		return info;
		
	}
	
}
