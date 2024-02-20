/**
 * 
 */
package ar.edu.ort.tp1.examen.clases;

/**
 * Representa una motocicleta a ser estacionada. el precio de los autos se
 * fracciona cada 5 minutos.
 */
public class Motocicleta extends Vehiculo {
	/**
	 * Precio de los 5 minutos de estacionamiento
	 */
	private float precioCincoMinutos;

	/**
	 * Constructor de motocicleta
	 * 
	 * @param patente       patente de la motocicleta
	 * @param horaIngreso   hora del ingreso
	 * @param minutoIngreso minutos del ingreso
	 * @param precioPorHora precio de la hora completa de las motocicletas
	 */
	
	private float precioPorHora;
	
	public Motocicleta(String patente, Hora horaIngreso, float precioPorHora) {
		super(patente, horaIngreso);
		setPrecioPorHora(precioPorHora);
	}

	private float getPrecioPorHora() {
		return precioPorHora;
	}

	private void setPrecioPorHora(float precioPorHora) {
		this.precioPorHora = precioPorHora;
	}
	
	/**
	 * Calcula el importe de la estadia del auto, recibiendo la hora y minutos de
	 * salida. Debe redondearse a 5 minutos la cantidad de minutos de la estadía, si
	 * la duración da 12 minutos, se deben cobrar 15. si la duración da 7 minutos,
	 * se deben cobrar 10. TODO: completar
	 */
	@Override
	public float calcularImporte(Hora horaEgreso) {
		float importe = 400;
		
		return importe;
	}

	@Override
	public void validarPatente(String patente) {
		if (!Helper.esPatenteMoto(patente)) {
			throw new IllegalArgumentException("Patente errónea");	
		}
		
	}
	
}
