
public class FreeParking extends GameObject 
{
	//Öncelikle bkz. GameObject.java
	
	//Bu class Free Parking karesinde tutulan objeyi temsil eder.
	//Classýn bir account hesabý vardýr. FreeParking alanýnda biriken para burada tutulur.
	
	private Account account;
	
	public FreeParking (String title, String description, int id)
	{
		super("freeparking", title, description, id);
		// GameObject'in constructor'ý çaðrýlýyor. Type "freeparking" olarak ayarlanýyor.
		account = new Account(0.0);
		// Ýçinde $0 bulunan bir account oluþturulup FreeParking'in account'u olarak atanýyor. 
	}
	
	public Account getAccount() 
	{
		return account;
	}
	
	public void setAccount(Account account) 
	{
		this.account = account;
	}
	
	public double getMoneyAmount ()
	{
		return account.getMoney();
	}
	
	public void giveMoneyTo(Player player) 
	{
		// bu metod çalýþtýrýldýðýnda, belirtilen oyuncuya Free Parking alanýnda biriken tüm para aktarýlýr.
		player.earnMoney(account.getMoney()); // belirtilen oyuncu, FreeParking classýnýn account'unda bulunan para kadar para kazanýr.
		account.withdraw(account.getMoney()); // Free Parking'in hesabýndan, hesaptaki miktar kadar para çekilir. Böylece FreeParking'te biriken para sýfýrlanmýþ olur.

		
	}
	
	public void putMoneyIn(double amount) 
	{
		account.deposit(amount);
		//Belirtilen miktarda parayý FreeParking'in hesabýna yatýrýr.
	}
	
	@Override
	public String getInformation() 
	{
		String info = super.getInformation();
		
		info += "Amount of money accumulated : $" + account.getMoney();
		return info;
		// Bu classýn extend ettiði getInformation metodunun döndürdüðü info'ya (bkz. String info = super.getInformation()), biriken para bilgisi de eklenir (bkz. info += "Amount of money accumulated : $" + account.getMoney();) ve oluþan yeni info döndürülür (return info).
		
	}
	

}
