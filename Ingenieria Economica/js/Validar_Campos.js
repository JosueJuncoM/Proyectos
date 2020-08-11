function validar_nombre() {
  var nombre = document.getElementById("nombre_usuario").value;
  if (nombre.length == 0) {
    return false;
  } else {
    return true;
  }
}

function validar_cedula() {
  var cedula = document.getElementById("usuario_cedula").value;
  if (cedula == null || cedula.length < 7 || /^\s+$/.test(cedula)) {
    return false;
  } else {
    return true;
  }
}

function validar_monto() {
  var monto = document.getElementById("usuario_monto").value;
  if (monto.length == 0) {
    return false;
  } else {
    return true;
  }
}

function validar_text_cf() {
  var tasa_EA_cf = document.getElementById("tasa_EA_cf").value;
  var tasa_NA_cf = document.getElementById("tasa_NA_cf").value;
  var tasa_ip_cf = document.getElementById("tasa_Ip_cf").value;

  if (tasa_EA_cf.length == 0 || tasa_NA_cf.length == 0 || tasa_ip_cf.length == 0) {
    return false;
  } else {
    return true;
  }
}

function validar_text_Pg() {
  var tasa_EA_PG = document.getElementById("tasa_EA_Pg").value;
  var tasa_NA_PG = document.getElementById("tasa_NA_Pg").value;
  var tasa_ip_PG = document.getElementById("tasa_Ip_Pg").value;

  if (tasa_EA_PG.length == 0 || tasa_NA_PG.length == 0 || tasa_ip_PG.length == 0) {
    return false;
  } else {
    return true;
  }
}

function validar_principal() {
  if (validar_nombre() == true && validar_monto() == true && validar_cedula() == true) {
    return true;
  } else {
    return false;
  }
}
