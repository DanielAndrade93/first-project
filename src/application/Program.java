package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entitites.Department;
import entitites.HourContract;
import entitites.Worker;
import entitites.enums.WorkerLevel;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		//Creating worker:
		System.out.print("Enter department's name: ");
		String departmentName = sc.nextLine();
		Department department = new Department(departmentName);
		System.out.println("Enter worker data:");
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Level: ");
		String aux = sc.nextLine();
		WorkerLevel level = WorkerLevel.valueOf(aux);
		System.out.print("Base salary: ");
		Double baseSalary = sc.nextDouble();
		Worker worker = new Worker(name, level, baseSalary, department);
		
		//Creating contracts:
		System.out.print("How many contracts to this worker? ");
		int numberOfContracts = sc.nextInt();
		for (int i = 0; i < numberOfContracts; i++) {
			System.out.printf("Enter contract #%d data:\n", i+1);
			System.out.print("Date (DD/MM/YYYY): ");
			Date date = sdf.parse(sc.next());
			System.out.print("Value per hour: ");
			Double valuePerHour = sc.nextDouble();
			System.out.print("Duration (hours): ");
			Integer hours = sc.nextInt();
			HourContract contract = new HourContract(date, valuePerHour, hours);
			worker.addContract(contract);
		}
		
		//Calculating income
		System.out.print("\nEnter month and year to calculate income (MM/YYYY): ");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/yyyy");
		Date incomeDate = sdf2.parse(sc.next());
		Calendar cal = Calendar.getInstance();
		cal.setTime(incomeDate);
		int incomeMonth = cal.get(Calendar.MONTH) + 1;
		int incomeYear = cal.get(Calendar.YEAR);
		System.out.println("Name: " + worker.getName());
		System.out.println("Department: " + worker.getDepartment().getName());
		System.out.println("Income for " + sdf2.format(incomeDate) + ": " + String.format("%.2f", worker.income(incomeYear, incomeMonth)));
		
		sc.close();
		
	}

}
