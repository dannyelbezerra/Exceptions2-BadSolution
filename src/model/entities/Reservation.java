package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkIn;
	private Date checkOut;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	/* Para formatar a data, foi declarado um private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 * O dado sdf foi colocado como static, para que não fosse instanciado um novo SimpleDateFormat,
	 * para cada objeto Reservation que a aplicação tiver, sendo necessário apenas um.
	 */
	
	public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {

		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}
	public Integer getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}
	
	/* Começa calculando a diferença entre as duas datas em milissegundos;
	 * Cria uma variável do tipo long, que recebe o checkOut.getTime(),
	 * que devolve a quantidade em milissegundos daquela data, menos o checkIn.getTime();
	 * Para converter de milissegundos para dias, utilizou uma classe do Java, chamada TimeUnit,
	 * que é um tipo enumerado complexo, que tem algumas operações;
	 * TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	 */
	
	
	
	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime(); 
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public String updateDates(Date checkIn, Date checkOut) {
		
		Date now = new Date();
		if (checkIn.before(now) || checkOut.before(now)) {
			return "Reservation dates for update must be future dates";
		}
		if (!checkOut.after(checkIn)) {
			return "Check-out date must be after check-in date";
		}
		
		this.checkIn = checkIn; //O checkIn do objeto, vai receber o checkIn, que veio como argumento do método;
		this.checkOut = checkOut;
		return null;
	}
	
	@Override
	public String toString() {
		return "Room "
			+ roomNumber
			+ ", check-in: "
			+ sdf.format(checkIn)
			+ ", check-out: "
			+ sdf.format(checkOut)
			+ ", "
			+ duration()
			+ " nights";
	
	}
	
}
