
public class FreeParking extends GameObject 
{
	//�ncelikle bkz. GameObject.java
	
	//Bu class Free Parking karesinde tutulan objeyi temsil eder.
	//Class�n bir account hesab� vard�r. FreeParking alan�nda biriken para burada tutulur.
	
	private Account account;
	
	public FreeParking (String title, String description, int id)
	{
		super("freeparking", title, description, id);
		// GameObject'in constructor'� �a�r�l�yor. Type "freeparking" olarak ayarlan�yor.
		account = new Account(0.0);
		// ��inde $0 bulunan bir account olu�turulup FreeParking'in account'u olarak atan�yor. 
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
		// bu metod �al��t�r�ld���nda, belirtilen oyuncuya Free Parking alan�nda biriken t�m para aktar�l�r.
		player.earnMoney(account.getMoney()); // belirtilen oyuncu, FreeParking class�n�n account'unda bulunan para kadar para kazan�r.
		account.withdraw(account.getMoney()); // Free Parking'in hesab�ndan, hesaptaki miktar kadar para �ekilir. B�ylece FreeParking'te biriken para s�f�rlanm�� olur.

		
	}
	
	public void putMoneyIn(double amount) 
	{
		account.deposit(amount);
		//Belirtilen miktarda paray� FreeParking'in hesab�na yat�r�r.
	}
	
	@Override
	public String getInformation() 
	{
		String info = super.getInformation();
		
		info += "Amount of money accumulated : $" + account.getMoney();
		return info;
		// Bu class�n extend etti�i getInformation metodunun d�nd�rd��� info'ya (bkz. String info = super.getInformation()), biriken para bilgisi de eklenir (bkz. info += "Amount of money accumulated : $" + account.getMoney();) ve olu�an yeni info d�nd�r�l�r (return info).
		
	}
	

}
