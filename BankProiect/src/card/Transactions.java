package card;

public class Transactions {
	private String amount;
	private String transactionType;
	private String date;
	private String hour;
	private String card;
	private String name;

	public Transactions(String amount, String transactionType, String date, String hour, String card, String name) {
		super();
		this.amount = amount;
		this.transactionType = transactionType;
		this.date = date;
		this.hour = hour;
		this.card = card;
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Transactions [amount=" + amount + ", transactionType=" + transactionType + ", date=" + date + ", hour="
				+ hour + ", card=" + card + ", name=" + name + " ";
	}

}
