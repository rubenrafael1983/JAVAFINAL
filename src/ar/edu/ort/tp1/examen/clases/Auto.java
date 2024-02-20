/**
 * 
 */
package ar.edu.ort.tp1.examen.clases;


/**
 * Representa un auto a ser estacionado. El precio de los autos se fracciona
 * cada 10 minutos.
 */
public class Auto extends Vehiculo {

	/**
	 * Precio de los 10 minutos de estacionamiento
	 */
	private float precioDiezMinutos;

	/**
	 * Constructor de auto
	 * 
	 * @param patente       patente del auto
	 * @param horaIngreso   hora del ingreso
	 * @param minutoIngreso minutos del ingreso
	 * @param precioPorHora precio de la hora completa de los autos
	 */

	private float precioPorHora;

	public Auto(String patente, Hora hora, float precioPorHora) {
		super(patente, hora);
		setPrecioPorHora(precioPorHora);
	}

	
	private float getPrecioPorHora() {
		return precioPorHora;
	}

	private void setPrecioPorHora(float precioPorHora) {
		this.precioPorHora = precioPorHora;
	}


	@Override
	public void validarPatente(String patente) {
		if (!Helper.esPatenteAuto(patente)) {
			throw new IllegalArgumentException("Patente errónea");	
		}
	}

	
	/**
	 * Calcula el importe de la estadia del auto, recibiendo el horario de salida.
	 * Debe redondearse a 10 minutos la cantidad de minutos de la estadía, si la
	 * duración da 7 minutos, se deben cobrar 10.
	 * TODO: Completar
	 */	
	
	@Override
	public float calcularImporte(Hora horaEgreso) {
		float importe = 400;

		return importe;		
		
	}



}
