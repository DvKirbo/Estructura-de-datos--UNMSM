import 'dart:math';
import 'package:stack/stack.dart';
int preferencia(String operador){
switch(operador){
  case '+':
  case '-':
    return 1;
  case '*':
  case '/':
    return 2;
  case '^':
    return 3;
  default:
    return 0;
  }
}

String infapost(String expresion){
Stack<String> pila = Stack();
String postfija = '';
for(int i = 0; i < expresion.length; i++){
  String caracteres = expresion[i];
  if(caracteres.contains(RegExp(r'[0-9]'))){
    postfija += caracteres;
  }
  else if(caracteres == '('){
    pila.push(caracteres);  
  }
  else if(caracteres == ')'){
    while(pila.isNotEmpty && pila.top() != '('){
      postfija += pila.pop();
    }
    pila.pop();
  }
  else if(caracteres.contains(RegExp(r'[a-zA-z]'))){
    String error = "expresion ilegal";
    return error;
    }
  else{
    while(pila.isNotEmpty && preferencia(caracteres) <= preferencia(pila.top())){
      postfija += pila.pop();
    }
    pila.push(caracteres);
  }
 }
  while(pila.isNotEmpty){
    postfija += pila.pop();
  }
  return postfija;
}

double Comprobar(String postfija){
  Stack<double> pila = Stack();
  for(int i = 0; i < postfija.length; i++){
    String caracter = postfija[i];
    if(caracter.contains(RegExp(r'[0-9]'))){
      pila.push(double.parse(caracter));
    }
    else{
      double operando2 = pila.pop();
      double operando1 = pila.pop();
      switch(caracter){
        case '+':
          pila.push(operando1 + operando2);
          break;
        case '-':
          pila.push(operando1 - operando2);
          break;
        case '*':
          pila.push(operando1 * operando2);
          break;
        case '/':
          pila.push(operando1 / operando2);
          break;
        case '^':
          pila.push(pow(operando1,operando2) as double);
          break;
      }
    }
  }
  return pila.pop();
}

void main(){
  String infija = 'wasa';
  String postfija = infapost(infija);
  print(postfija);
  if(postfija != "expresion ilegal"){
    double resultado = Comprobar(postfija);
    print(resultado);
  }
  else{
    print("expresion ilegal");
  }
}
