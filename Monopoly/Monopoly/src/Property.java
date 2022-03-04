
public abstract class Property extends GameObject {

	// Sahiplenilebilir t�m m�lkleri temsil eder.
	// abstract class: Kodun herhangi bir yerinde bu class�n objesi olu�turulmuyor.
	
	protected Player owner;
	// m�lk�n sahibini tutmak i�in
	
	protected boolean hasOwner;
	// m�lk�n sahiplenilmi� olup olmad���n� tutmak i�in
	
	public Property(String type, String title, String description, int id) 
	{
		super(type, title, description, id);
		// bu class�n constructor�na verilen type, title, description ve id oldu�u gibi GameObject'in constructor�na veriliyor. bkz. GameObject.java constructor
		
		owner = null;
		hasOwner = false;
		// ba�lang��ta her m�lk sahipsiz. Bu nedenle owner = null ve hasOwner = false
		
	}
	
	public Player getOwner() 
	{
		return owner;
	}
	
	public void setOwner(Player player) 
	{
		// e�er bu metoda null verilirse, m�lk sahipsizle�tirilmi� olur. bkz: if (player == null) hasOwner = false;
		
		
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
		// m�lke sahip atar.
		setOwner(player);
		hasOwner = true;
	}

	@Override
	public String getInformation() 
	{
		
		String info = super.getInformation();
		// GameObject class�n�n getInformation metodunun d�nd�rd��� stringe m�lk�n sahip bilgisini a�a��da ekler ve olu�an info Stringini d�nd�r�r.
		
		
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
