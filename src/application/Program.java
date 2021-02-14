package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {
	
	/* Quando se tem um método e dentro dele, é feita uma chamada que pode gerar uma exceção,
	 * ou trata a exceção, que no exemplo seria utilizado Try/catch,
	 * ou propaga a exceção, colocando a palavra throws e o nome da exceção ParseException;
	 */

	public static void main(String[] args) throws ParseException {
		
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.print("Room number: ");
		int number = sc.nextInt();
		System.out.print("Check-in date (dd/MM/yyyy): ");
		Date checkIn = sdf.parse(sc.next()); //Entra com uma data no formato de texto e o SimpleDateFormat converte em Date;
		
		System.out.print("Check-out date (dd/MM/yyyy): ");
		Date checkOut = sdf.parse(sc.next());
		
		if (!checkOut.after(checkIn)) { //se não checkOut é depois do checkIn, ou seja, se a data do checkOut for posterior a do checkIn, significa que as datas são invalidas;
			System.out.println("Error in reservation: Check-out date must be after check-in date");
		}
		else { //caso contrário, vai ser instanciado um objeto do tipo Reservation
			Reservation reservation = new Reservation(number, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
			
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
		
			String error = reservation.updateDates(checkIn, checkOut); //atualiza as datas;
			if (error != null) {
				System.out.println("Error in reservation: " + error);
			}
			else {		
				System.out.println("Reservation: " + reservation);
			}

		}
		
		sc.close();
	}

}
