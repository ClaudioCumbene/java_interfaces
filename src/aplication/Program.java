package aplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entities.CarRental;
import entities.Vehicle;
import model.services.MozTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter dtf= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro:");
		String model = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime initial = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
		
		CarRental cr = new CarRental(initial, finish, new Vehicle(model));
		
		System.out.print("Entre com o preço por hora:");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia:");
		double pricePerDay = sc.nextDouble();
		
		RentalService renatalService = new RentalService(pricePerHour, pricePerDay, new MozTaxService());
		
		renatalService.processInvoice(cr);
		
		System.out.println("FACTURA: ");
		System.out.println("Pagamento basico: "+ String.format("%.2f", cr.getInvoice().getBasicPayment()) );
		System.out.println("Imposto:" + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total:"+ String.format("%.2f", cr.getInvoice().getTotalPayment()));
		
				
				
		sc.close();	
	}

}
