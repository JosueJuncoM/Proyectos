document.getElementById("button_cf_prev").addEventListener("click", function() {
  generar();
});

document.getElementById("button_cf").addEventListener("click", function() {
  generar_prev_cf();
});

//Cuota Fija
var cuota_A = 0;

//numero de periodos
var n_peridos = 0;

//Fecha actual
var f = new Date();
var fecha = (f.getDate() + "/" + "0" + (f.getMonth() + 1) + "/" + f.getFullYear());

//Datos fecha
var dia = f.getDate();
var mes = (f.getMonth() + 1);
var año = f.getFullYear();
var cont_mes = 0;

//Calcular las tasas
var tasa_ea_cf = 0;
var tasa_na_cf = 0;
var tasa_ip_cf = 0;
var amort_cf_num = 0;
var amort_cf = "";

//Datos del cuadro
var datos = [
  []
];

//Calcular las tasas
function calcular_tasa_cf(id) {
  //Obtener el valor de la Amortizacion
  var lista = document.getElementById("opc_amort_cf");
  amort_cf = lista.options[lista.selectedIndex].text;
  //Si el usuario ingresa el valor Efectivo anual
  if (id == "EA") {
    //Si es mensual
    if (amort_cf == "Mensual") {
      amort_cf_num = 1;
      tasa_ip_cf = (Math.pow((1 + (parseFloat(document.getElementById("tasa_EA_cf").value) / 100)), (30 / 360)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 12;
      tasa_ea_cf = (Math.pow((1 + (tasa_ip_cf / 100)), (360 / 30)) - 1) * 100;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    } else {
      //Si es bimestral
      amort_cf_num = 2;
      tasa_ip_cf = (Math.pow((1 + (parseFloat(document.getElementById("tasa_EA_cf").value) / 100)), (60 / 360)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 6;
      tasa_ea_cf = (Math.pow((1 + (tasa_ip_cf / 100)), (360 / 60)) - 1) * 100;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    }
  }

  if (id == "NA") {
    //Si es mensual
    if (amort_cf == "Mensual") {
      amort_cf_num = 1;
      tasa_ip_cf = (parseFloat(document.getElementById("tasa_NA_cf").value) / 12);
      tasa_ea_cf = (Math.pow((1 + (tasa_ip_cf / 100)), (360 / 30)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 12;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    } else {
      //Si es bimestral
      amort_cf_num = 2;
      tasa_ip_cf = (parseFloat(document.getElementById("tasa_NA_cf").value) / 6);
      tasa_ea_cf = (Math.pow((1 + (tasa_ip_cf / 100)), (360 / 60)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 6;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    }
  }

  if (id == "Ip") {
    //Si es mensual
    if (amort_cf == "Mensual") {
      amort_cf_num = 1;
      tasa_ea_cf = (Math.pow((1 + (parseFloat(document.getElementById("tasa_Ip_cf").value) / 100)), (360 / 30)) - 1) * 100;
      tasa_ip_cf = (Math.pow((1 + (tasa_ea_cf / 100)), (30 / 360)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 12;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    } else {
      //Si es bimestral
      amort_cf_num = 2;
      tasa_ea_cf = (Math.pow((1 + (parseFloat(document.getElementById("tasa_Ip_cf").value) / 100)), (360 / 60)) - 1) * 100;
      tasa_ip_cf = (Math.pow((1 + (tasa_ea_cf / 100)), (60 / 360)) - 1) * 100;
      tasa_na_cf = tasa_ip_cf * 6;
      document.getElementById("tasa_Ip_cf").value = tasa_ip_cf.toFixed(2) + " %";
      document.getElementById("tasa_NA_cf").value = tasa_na_cf.toFixed(2) + " %";
      document.getElementById("tasa_EA_cf").value = tasa_ea_cf.toFixed(2) + " %";
    }
  }
}

//Calcular el numero de periodos
function calcular_n() {
  //Obtener el valor del plazo
  var lista = document.getElementById("opc_plazo_cf");
  var plazo = lista.options[lista.selectedIndex].text;
  n_peridos = (parseFloat(plazo) / amort_cf_num);
}

//Calcular la cuota fija
function cuota_fija() {
  cuota_A = ((monto) * (((Math.pow((1 + ((tasa_ip_cf) / 100)), n_peridos)) * ((tasa_ip_cf) / 100)) / ((Math.pow((1 + ((tasa_ip_cf) / 100)), n_peridos)) - 1))).toFixed(2);
}

//Completar el cuadro de amortizacion
function calcular_cuadro() {
  //Datos del cuador de Amortizacion
  var saldo_cap = monto;
  var amort_cap = 0;
  var amort_inte = 0;

  for (var i = 0; i < (n_peridos + 1); i++) {
    if (i == 0) {
      datos[i] = [i, fecha, formatNumber(saldo_cap), formatNumber(amort_cap), formatNumber(amort_inte), "-", formatNumber(saldo_cap)];
    } else {
      amort_inte = (saldo_cap * ((tasa_ip_cf) / 100)).toFixed(2);
      amort_cap = (cuota_A - amort_inte).toFixed(2);
      saldo_cap = (saldo_cap - amort_cap).toFixed(2);
      datos[i] = [i, calcular_fecha(), formatNumber(saldo_cap), formatNumber(amort_cap), formatNumber(amort_inte), formatNumber(cuota_A), "(" + formatNumber(cuota_A) + ")"];
    }
  }
}

//calcular fechas
function calcular_fecha() {

  if (amort_cf == "Mensual") {
    mes = (mes + 1) % 12;
    if (mes == 0) {
      mes = 12;
      if (dia < 10 && mes < 10) {
        fecha = ("0" + dia + "/" + "0" + mes + "/" + año);
      } else {
        if (dia < 10) {
          fecha = ("0" + dia + "/" + mes + "/" + año);
        } else {
          if (mes < 10) {
            fecha = (dia + "/" + "0" + mes + "/" + año);
          } else {
            fecha = (dia + "/" + mes + "/" + año);
          }
        }
      }
      año = año + 1;
      return fecha;
    } else {
      if (dia < 10 && mes < 10) {
        fecha = ("0" + dia + "/" + "0" + mes + "/" + año);
      } else {
        if (dia < 10) {
          fecha = ("0" + dia + "/" + mes + "/" + año);
        } else {
          if (mes < 10) {
            fecha = (dia + "/" + "0" + mes + "/" + año);
          } else {
            fecha = (dia + "/" + mes + "/" + año);
          }
        }
      }
      return fecha;
    }
  } else {
    //Si es bimestral
    cont_mes = mes + 2;
    mes = (mes + 2) % 12;
    if (cont_mes > 12) {
      año = año + 1;
      cont_mes = cont_mes % 12;
      if (dia < 10 && mes < 10) {
        fecha = ("0" + dia + "/" + "0" + mes + "/" + año);
      } else {
        if (dia < 10) {
          fecha = ("0" + dia + "/" + mes + "/" + año);
        } else {
          if (mes < 10) {
            fecha = (dia + "/" + "0" + mes + "/" + año);
          } else {
            fecha = (dia + "/" + mes + "/" + año);
          }
        }
      }
      return fecha;
    } else {
      if (mes == 0) {
        mes = 12;
        if (dia < 10 && mes < 10) {
          fecha = ("0" + dia + "/" + "0" + mes + "/" + año);
        } else {
          if (dia < 10) {
            fecha = ("0" + dia + "/" + mes + "/" + año);
          } else {
            if (mes < 10) {
              fecha = (dia + "/" + "0" + mes + "/" + año);
            } else {
              fecha = (dia + "/" + mes + "/" + año);
            }
          }
        }
        año = año + 1;
        return fecha;
      } else {
        if (dia < 10 && mes < 10) {
          fecha = ("0" + dia + "/" + "0" + mes + "/" + año);
        } else {
          if (dia < 10) {
            fecha = ("0" + dia + "/" + mes + "/" + año);
          } else {
            if (mes < 10) {
              fecha = (dia + "/" + "0" + mes + "/" + año);
            } else {
              fecha = (dia + "/" + mes + "/" + año);
            }
          }
        }
        return fecha;
      }
    }
  }
}

//Genera el PDF del cuadro
function generar_cuadro_pdf() {
  var pdf = new jsPDF();


  var header = function(data) {
    var image = "data:image/png;base64,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";
    pdf.addImage(image, 'JPEG', 90, 0, 30, 30);


    pdf.setFontSize(12);
    pdf.text(20, 35, 'Nombre: ' + nombre + '    Cedula: ' + cedula);
    //Obtener el valor del plazo
    var lista = document.getElementById("opc_plazo_cf");
    var plazo = lista.options[lista.selectedIndex].text;
    pdf.text(20, 45, 'Monto: $ ' + monto_pdf + '    Interes periodico: ' + tasa_ip_cf.toFixed(2) + '%' + ' ' + amort_cf + "    Plazo: " + plazo);
    pdf.text(20, 55, 'No. Cuotas: '+ n_peridos + '    Cuota Fija: $ '+ formatNumber(cuota_A));
    pdf.text(20, 70, 'Cuadro de amortizacion cuota fija:');
  };


  var columns = ["Cuota", "Fecha", "Saldo Capital", "Amort. Capital", "Amort. Intereses", "Cuota Fija", "Flujo de Caja"];

  pdf.autoTable(columns, datos, {
    beforePageContent: header,
    margin: {
      top: 75
    }
  });

  pdf.save('Cuadro Cuota Fija.pdf');
}

//Dar formato a los numeros
function formatNumber(num) {
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
  return (((sign) ? '' : '-') + num + ',' + cents);
}

var ban_prev = 0;
//Generar preview
function generar_prev_cf() {
  document.getElementById("credito_Cf1").focus();
  if (ban_prev == 0) {
    if (validar_principal() == true && validar_text_cf() == true) {
      document.getElementById('credito_cf_prev').style.display = "block";
      document.getElementById("credito_cf_prev").focus();
      var lista = document.getElementById("opc_amort_cf");
      amort_cf = lista.options[lista.selectedIndex].text;
      if (amort_cf == "Mensual") {
        amort_cf_num = 1;
      } else {
        amort_cf_num = 2;
      }
      calcular_n();
      cuota_fija();
      calcular_cuadro();
      genera_tabla_cf();
      ban_prev = 1;
    } else {
      alert("Hay campos vacios.");
    }
  } else {
    if (validar_principal() == true && validar_text_cf() == true) {
      //eliminar tabla
      var d = document.getElementById("credito_cf_prev");
      var d_nested = document.getElementById("tabla_cf_prev");
      var throwawayNode = d.removeChild(d_nested);
      document.getElementById('credito_cf_prev').style.display = "block";
      document.getElementById("credito_cf_prev").focus();
      var lista = document.getElementById("opc_amort_cf");
      amort_cf = lista.options[lista.selectedIndex].text;
      if (amort_cf == "Mensual") {
        amort_cf_num = 1;
      } else {
        amort_cf_num = 2;
      }
      //Resetear variables
      cuota_A = 0;
      n_peridos = 0;
      mes = (f.getMonth() + 1);
      año = f.getFullYear();
      fecha = (f.getDate() + "/" + "0" + (f.getMonth() + 1) + "/" + f.getFullYear());
      cont_mes = 0;
      saldo_cap = monto;
      amort_cap = 0;
      amort_inte = 0;
      amort_cf = "";
      datos = [
        []
      ];
      calcular_n();
      cuota_fija();
      calcular_cuadro();
      genera_tabla_cf();
      ban_prev = 1;
    } else {
      alert("Hay campos vacios.");
    }
  }

}


//Generar tabla en div
function genera_tabla_cf() {
  var body = document.getElementById("credito_cf_prev");
  var tabla = document.createElement("table");
  var tblBody = document.createElement("tbody");

  for (var i = 0; i < datos.length + 1; i++) {
    var hilera = document.createElement("tr");

    for (var j = 0; j < 7; j++) {
      var celda = document.createElement("td");
      if (i == 0) {
        if (j == 0) {
          var textoCelda = document.createTextNode("Cuota");
        } else {
          if (j == 1) {
            var textoCelda = document.createTextNode("Fecha");
          } else {
            if (j == 2) {
              var textoCelda = document.createTextNode("Saldo Capital");
            } else {
              if (j == 3) {
                var textoCelda = document.createTextNode("Amort. Capital");
              } else {
                if (j == 4) {
                  var textoCelda = document.createTextNode("Amort. Intereses");
                } else {
                  if (j == 5) {
                    var textoCelda = document.createTextNode("Cuota Fija");
                  } else {
                    var textoCelda = document.createTextNode("Flujo de Caja");
                  }
                }
              }
            }
          }
        }
      } else {
        var textoCelda = document.createTextNode(datos[i - 1][j]);
      }

      celda.appendChild(textoCelda);
      hilera.appendChild(celda);
    }

    tblBody.appendChild(hilera);
  }

  tabla.appendChild(tblBody);
  body.appendChild(tabla);
  tabla.setAttribute("class", "democlass");
  tabla.setAttribute("id", "tabla_cf_prev");

}


//Generar pdf
function generar() {
  document.getElementById("credito_Cf1").focus();
  //eliminar tabla
  var d = document.getElementById("credito_cf_prev");
  var d_nested = document.getElementById("tabla_cf_prev");
  var throwawayNode = d.removeChild(d_nested);
  document.getElementById("credito_cf_prev").blur();
  document.getElementById('credito_cf_prev').style.display = "none";
  generar_cuadro_pdf();


  //Resetear variables
  ban_prev = 0;
  cuota_A = 0;
  n_peridos = 0;
  mes = (f.getMonth() + 1);
  año = f.getFullYear();
  fecha = (f.getDate() + "/" + "0" + (f.getMonth() + 1) + "/" + f.getFullYear());
  cont_mes = 0;
  saldo_cap = monto;
  amort_cap = 0;
  amort_inte = 0;
  amort_cf = "";
  datos = [
    []
  ];

}
