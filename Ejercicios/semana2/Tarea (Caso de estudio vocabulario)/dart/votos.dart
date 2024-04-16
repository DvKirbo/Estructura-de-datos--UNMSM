import 'dart:io';

class Voleta implements Comparable<Voleta>{
  late List<String> preferencias;
  
  Voleta(List<String> nombres){
    preferencias = List.from(nombres);
  }
  String obtenerCandidato(){
    if(!preferencias.isEmpty){
      return preferencias[0];
    } 
    else{
      return "ninguno";
    }
  }
  void eliminado(String nombre){
    preferencias.remove(nombre);
  }
  bool estaVacio(){
    return preferencias.isEmpty;
  }
  @override
  int compareTo(Voleta otro) {
    return obtenerCandidato().compareTo(otro.obtenerCandidato());
  }
}

List<Voleta> leerArchivo(File archivo){
  List<Voleta> resultado = [];
  archivo.readAsLinesSync().forEach((linea) { 
    linea = linea.trim();
    if(linea.isNotEmpty){
      List<String> votos = linea.split(",");
      resultado.add(Voleta(votos));
    }
  });
  return resultado;
}

bool unaRonda(List<Voleta> voletas){
  String? primero = null;
  String? ultimo = null;
  int contadorInicial = 0;
  int contadorFinal = voletas.length + 1;
  Map<String, int> conteoVotos = {};
  for(Voleta voleta in voletas){
    String candidato = voleta.obtenerCandidato();
    conteoVotos[candidato] = (conteoVotos[candidato] ?? 0) + 1;
  }
  for (var entrada in conteoVotos.entries) {
  String candidato = entrada.key;
  int conteo = entrada.value;
  if (conteo > contadorInicial) {
    contadorInicial = conteo;
    primero = candidato;
  }
  if (conteo < contadorFinal) {
    contadorFinal = conteo;
    ultimo = candidato;
  }
}
  for(var entrada in conteoVotos.entries){
    String candidato = entrada.key;
    int conteo = entrada.value;
    double porcentaje = 100*conteo/voletas.length;
   print('$conteo votes for $candidato (${porcentaje.toStringAsFixed(1)}%)');
  }
  if(contadorInicial == contadorFinal){
    print("No hay ganador en la Eleccion");
    return true;
  }
  else if(contadorInicial > voletas.length/2){
    print("Ganador es: ${primero}");
    return true;
  }
  else{
    print("No hay ganador, eliminando:  ${ultimo}");
    for(Voleta voleta in voletas){
      if(!voleta.estaVacio()){
        voleta.eliminado(ultimo!);
      }
    }
    if(!voletas.isEmpty){
      return unaRonda(voletas);
    }
    else{
      print("Todas las voletas estan vacias. No hay ganador");
      return true;
    }
  }
}

int procesarVotos(String nombre,int indice,List<Voleta> voletas){
  int conteo = 0;
  while(indice < voletas.length && voletas[indice].obtenerCandidato() == nombre){
    indice++;
    conteo++;
  }
  double porcentaje = 100*conteo/voletas.length;
  print('$conteo votes for $nombre (${porcentaje.toStringAsFixed(1)}%)');
  return conteo;
}

void eliminar(String candidato, List<Voleta> voletas){
  for(Voleta v in voletas){
    v.eliminado(candidato);
  }
}
void main(){
  final archivo = File('vote.txt');
  List<Voleta> voletas = leerArchivo(archivo);
  int ronda = 1;
  bool hecho = false;
  while(!hecho){
    print("Ronda # $ronda");
    voletas.sort();
    hecho = unaRonda(voletas);
    print("------------------------------");
    ronda++;
  }
}