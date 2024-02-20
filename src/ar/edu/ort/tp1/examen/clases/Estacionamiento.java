/**
 * 
 */
package ar.edu.ort.tp1.examen.clases;

import java.nio.file.attribute.FileOwnerAttributeView;

import ar.edu.ort.tp1.tdas.implementaciones.ColaNodos;
import ar.edu.ort.tp1.tdas.implementaciones.PilaNodos;
import ar.edu.ort.tp1.tdas.interfaces.Cola;
import ar.edu.ort.tp1.tdas.interfaces.Pila;

/**
 * Entidad que engloba el funcionamiento de un estacionamiento de autos y
 * motocicletas. cada tipo de vehículo será estacionado de la forma determinada
 * y en base a su capacidad asignada al momento de la creación del
 * estacionamiento.
 * 
 * Se debe tene en cuenta que el funcionamiento del estacionamiento para cada
 * tipo de vehículo es del tipo LIFO Utilizar la implementación de TAD que
 * corresponda
 */
public class Estacionamiento {

	// TODO: COMPLETAR

	/**
	 * Constructor del estacionamiento, recibe las capacidades de autos y motos y
	 * los precios por hora completas.
	 * 
	 * @param capacidadAutos
	 * @param capacidadMotocicletas
	 * @param precioAutosPorHora
	 * @param precioMotocicletasPorHora
	 */
	
	private int capacidadAutos;
	private int capacidadMotocicletas; 
	private float precioAutosPorHora;
	private float precioMotocicletasPorHora;
	private int contadorVehiculos[];
	private float AcumRecaudacionVehiculos[];
	
	private Pila<Vehiculo> pilaAutos = new PilaNodos<>();
	private Cola<Vehiculo> colaMotos = new ColaNodos<>();
	
	
	public Estacionamiento(int capacidadAutos, int capacidadMotocicletas, float precioAutosPorHora,
			float precioMotocicletasPorHora) {
		
		setCapacidadAutos(capacidadAutos);
		setCapacidadMotocicletas(capacidadMotocicletas);
		setPrecioAutosPorHora(precioAutosPorHora);
		setPrecioMotocicletasPorHora(precioMotocicletasPorHora);
		
		pilaAutos = new PilaNodos<>(capacidadAutos);
		colaMotos = new ColaNodos<>(capacidadMotocicletas);
		contadorVehiculos = new int[TipoVehiculo.values().length];
		AcumRecaudacionVehiculos = new float[TipoVehiculo.values().length];
		}

	private int getCapacidadAutos() {
		return capacidadAutos;
	}

	private void setCapacidadAutos(int capacidadAutos) {
		this.capacidadAutos = capacidadAutos;
	}

	private int getCapacidadMotocicletas() {
		return capacidadMotocicletas;
	}

	private void setCapacidadMotocicletas(int capacidadMotocicletas) {
		this.capacidadMotocicletas = capacidadMotocicletas;
	}

	private float getPrecioAutosPorHora() {
		return precioAutosPorHora;
	}

	private void setPrecioAutosPorHora(float precioAutosPorHora) {
		this.precioAutosPorHora = precioAutosPorHora;
	}

	private float getPrecioMotocicletasPorHora() {
		return precioMotocicletasPorHora;
	}

	private void setPrecioMotocicletasPorHora(float precioMotocicletasPorHora) {
		this.precioMotocicletasPorHora = precioMotocicletasPorHora;
	}



	/**
	 * Permite estacionar un vehículo en el estacionamiento. La patente debe ser
	 * válida segun el tipo de vehículo el horario de ingreso debe ser válido
	 * 
	 * @param tipo
	 * @param patente
	 * @param horaIngreso
	 * @param minutosIngreso
	 */
	public void estacionar(TipoVehiculo tipo, String patente, Hora hora) throws EstacionamientoException{

		// TODO: COMPLETAR
		
		if(Helper.esPatenteAuto(patente)) {
			Vehiculo v = buscarAuto(patente);
			if(v == null && hora.esValida() && contadorVehiculos[TipoVehiculo.AUTO.ordinal()] < capacidadAutos) {
				contadorVehiculos[TipoVehiculo.AUTO.ordinal()]++;
				pilaAutos.push(v = new Auto(patente, hora, precioAutosPorHora));
				System.out.println("Se estacionó correctamente el auto patente: " + patente);
			}
			else if (!hora.esValida()) {
				throw new EstacionamientoException("Hora de ingreso inválida");
			}
				else if (contadorVehiculos[TipoVehiculo.AUTO.ordinal()] >= capacidadAutos) {
					throw new EstacionamientoException("Estacionamiento de autos completo");
			}
		}
		
		if(Helper.esPatenteMoto(patente)) {
		Vehiculo v = buscarMoto(patente);
		if(v == null&& hora.esValida() && contadorVehiculos[TipoVehiculo.AUTO.ordinal()] < capacidadAutos) {
			contadorVehiculos[TipoVehiculo.MOTOCICLETA.ordinal()]++;
			colaMotos.add(v = new Motocicleta(patente, hora, precioMotocicletasPorHora));
			System.out.println("Se estacionó correctamente la motocicleta patente: " + patente);
			}
		else if (contadorVehiculos[TipoVehiculo.MOTOCICLETA.ordinal()] >= capacidadAutos) {
			throw new EstacionamientoException("Estacionamiento de Motocicletas completo:");
		}

		}
	}
	
	public Vehiculo  buscarAuto(String patente) throws EstacionamientoException{
		Vehiculo autoEncontrado = null;
		Pila<Vehiculo> pilaAux = new PilaNodos<>();
		
		while(!pilaAutos.isEmpty() && autoEncontrado == null) {
			Vehiculo v = pilaAutos.pop();
			if (v.getPatente().equals(patente)) {
				autoEncontrado = v;
			}
			else {
				pilaAux.push(v);
			}
			
		}
		
		while(!pilaAux.isEmpty()) {
			Vehiculo v = pilaAux.pop();
			pilaAutos.push(v);
		}
				
		return autoEncontrado;
	}

	public Vehiculo  buscarMoto(String patente) throws EstacionamientoException{
		Vehiculo centinela = null;
		Vehiculo vehiculo = null;
		Vehiculo motoEncontrada = null;
		colaMotos.add(centinela);
		vehiculo = colaMotos.remove();
		
		
		while(vehiculo != centinela && motoEncontrada == null) {
			if (vehiculo.getPatente().equals(patente)) {
				motoEncontrada = vehiculo;
				}
			colaMotos.add(vehiculo);
			vehiculo = colaMotos.remove();
			
		}
				
		return motoEncontrada;
	}
	
	/**
	 * Retira un vehículo del estacionamiento. debe detectar el tipo de vehículo en
	 * base a su patente (aprovechar los métodos del helper)
	 * el horario de egreso debe ser válido si el vehículo no está estacionado debe
	 * lanzar una excepción.
	 * 
	 * @param patente
	 * @param horaEgreso
	 * @param minutosEgreso
	 * @return
	 */
	public float retirar(String patente, Hora hora) {
		float importe = 0;
		if(Helper.esPatenteAuto(patente)) {
			Vehiculo v = buscarAuto(patente);
			if(v != null && hora.esValida() ) {
				importe = v.calcularImporte(hora);
				AcumRecaudacionVehiculos[TipoVehiculo.AUTO.ordinal()] += importe;
				
			}
		}
		
		if(Helper.esPatenteMoto(patente)) {
		Vehiculo v = buscarMoto(patente);
		if(v == null&& hora.esValida() ) {
			importe = v.calcularImporte(hora);
			AcumRecaudacionVehiculos[TipoVehiculo.MOTOCICLETA.ordinal()] = importe;
			}		
		}
		return importe;
	}
	
	/**
	 * Muestra por pantalla el resumen del final del día (cantidad de autos y motos
	 * estacionados)
	 */
	public void finalizarDia() {
		System.out.println("\r\n--------- Resumen final del día --------------\r\n");
		//TODO: Descomentar y completar para que funcione correctamente
		System.out.printf("Por estacionamiento de autos se ha recaudado $ %4.2f\n", + AcumRecaudacionVehiculos[TipoVehiculo.AUTO.ordinal()]);
		System.out.printf("Por estacionamiento de motocicletas se ha recaudado $ %4.2f\n", + AcumRecaudacionVehiculos[TipoVehiculo.MOTOCICLETA.ordinal()]);
		System.out.println("\r\n--------- Fin del reporte resumen final del día --------------\r\n");
	}

}
