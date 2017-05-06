package edu.ort.parcial.t3.v2_enCasa;

public enum EspecialidadEnum {
	DR("DERMATOLOGIA"), KI("KINESIOLOGIA"), TR("TRAUMATOLOGIA"), OF("OFTALMOLOGIA"), OD("ODONTOLOGIA");

	private String descripcion;

	private EspecialidadEnum(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
