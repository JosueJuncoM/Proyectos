var monto = 0;
var monto_pdf = "";

function form_monto() {
  num = parseFloat(document.getElementById("usuario_monto").value)
  if (isNaN(num)) {
    alert("El monto no es valido");
    document.getElementById("usuario_monto").value = "";
  }else{
    monto = parseFloat(document.getElementById("usuario_monto").value)
    if (!num || num == 'NaN') return '-';
    if (num == 'Infinity') return '&#x221e;';
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num))
      num = "0";
    sign = (num == (num = Math.abs(num)));
    num = Math.floor(num * 100 + 0.50000000001);
    cents = num % 100;
    num = Math.floor(num / 100).toString();
    if (cents < 10)
      cents = "0" + cents;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
      num = num.substring(0, num.length - (4 * i + 3)) + '.' + num.substring(num.length - (4 * i + 3));

    document.getElementById("usuario_monto").value = (((sign) ? '' : '-') + num + ',' + cents);
    monto_pdf = (((sign) ? '' : '-') + num + ',' + cents);
  }


}

var cedula = "";

function form_cedula() {
  var cedu = document.getElementById("usuario_cedula").value;
  if (cedu.length == 10) {
    var s1 = cedu.slice(0, 1) + ".";
    var s2 = cedu.slice(1, 4) + "."
    var s3 = cedu.slice(4, 7) + ".";
    var s4 = cedu.slice(7, 10);
    cedula = s1 + s2 + s3 + s4;

    if (validar_cedula() == true) {
      document.getElementById("usuario_cedula").value = cedula;
    } else {
      alert("La cedula ingresada no es valida.");
      document.getElementById("usuario_cedula").value = "";
    }
  } else {
    if (cedu.length == 8) {
      var s1 = cedu.slice(0, 2) + ".";
      var s2 = cedu.slice(2, 5) + "."
      var s3 = cedu.slice(5, 8);
      cedula = s1 + s2 + s3;
      if (validar_cedula() == true) {
        document.getElementById("usuario_cedula").value = cedula;
      } else {
        alert("La cedula ingresada no es valida.");
        document.getElementById("usuario_cedula").value = "";
      }
    } else {
      alert("La cedula ingresada no es valida.");
      document.getElementById("usuario_cedula").value = "";
    }
  }

}


var nombre = "";

function form_nombre() {
  var nom = document.getElementById("nombre_usuario").value;
  nombre = nom.toUpperCase();
  document.getElementById("nombre_usuario").value = nombre

}
