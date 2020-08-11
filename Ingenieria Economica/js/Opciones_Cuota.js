function mostrar(id) {
  /*
  var elementos = document.getElementsByName("credito");
  alert(elementos);
  */
  //alert(id);
  if (id == "credito_Cf") {
    //alert("hola");
    //Resetear variables
    a_cuota_pg = 0;
    n_periodo_pg = 0;
    periodo_pg = 0;
    mes = (f.getMonth() + 1);
    año = f.getFullYear();
    fecha = (f.getDate() + "/" + "0" + (f.getMonth() + 1) + "/" + f.getFullYear());
    cont_mes = 0;
    saldo_cap_pg = 0;
    amort_cap_pg = 0;
    amort_inte_pg = 0;
    datos_pg = [
      []
    ];
    document.getElementById('credito_Pg1').style.display = "none";
    document.getElementById('credito_pg_prev').style.display = "none";
    document.getElementById('credito_Cf1').style.display = "block";
  }

  if (id == "credito_Pg") {
    //alert("hola2");
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
    document.getElementById('credito_Cf1').style.display = "none";
    document.getElementById('credito_cf_prev').style.display = "none";
    document.getElementById('credito_Pg1').style.display = "block";
  }




}
