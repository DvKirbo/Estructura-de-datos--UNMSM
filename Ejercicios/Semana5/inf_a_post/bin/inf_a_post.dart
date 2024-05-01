import 'dart:collection';
import 'dart:math';
import 'dart:io';
import 'package:stack/stack.dart';
import 'package:string_validator/string_validator.dart';

class ExpressionEvaluator {

  static num evaluatePostfix({required String expression, required Map context}) {
    return _evaluate(expression.split(''), context: context, reverseOperands: true);
  }

  static num _evaluate(List<String> tokens, {required Map context, bool reverseOperands = false}) {
    final resultStack = Stack<num>();
    for (var token in tokens) {
      if (isAlpha(token)) {
        resultStack.push(context[token]);
      } else {
        final a = resultStack.pop(), b = resultStack.pop();
        if (token == '+') {
          resultStack.push(a + b);
        } else if (token == '-') {
          resultStack.push(reverseOperands ? b - a : a - b);
        } else if (token == '*') {
          resultStack.push(a * b);
        } else if (token == '/') {
          resultStack.push(reverseOperands ? b / a : a / b);
        } else if (token == '^') {
          resultStack.push(reverseOperands ? pow(b, a) : pow(a, b));
        }
      }
    }
    return resultStack.top();
  }
}


class ExpressionConverter {
  static Map<String, double> convertExpression(String expression) {
    List<String> operators = ["+", "-", "*", "/", "^"];
    Map<String, double> contexto = LinkedHashMap();
    String variable = 'a';
    String nuevaExpresion = "";
    List<String> partes = expression.split(' ');

    for (int i = 0; i < partes.length; i++) {
      if (!operators.contains(partes[i])) {
        nuevaExpresion += variable;
        contexto[variable] = double.parse(partes[i]);
        if (i < partes.length - 1 && operators.contains(partes[i + 1])) {
          nuevaExpresion += partes[i + 1];
          i++;
        }
        variable = String.fromCharCode(variable.codeUnitAt(0) + 1);
      } else {
        nuevaExpresion += partes[i];
      }
    }
    return contexto;
  }

  static String generateNewExpression(String expression) {
    List<String> operators = ["+", "-", "*", "/", "^"];
    String variable = 'a';
    String nuevaExpresion = "";
    List<String> partes = expression.split(' ');

    for (int i = 0; i < partes.length; i++) {
      if (!operators.contains(partes[i])) {
        nuevaExpresion += variable;
        if (i < partes.length - 1 && operators.contains(partes[i + 1])) {
          nuevaExpresion += partes[i + 1];
          i++;
        }
        variable = String.fromCharCode(variable.codeUnitAt(0) + 1);
      } else {
        nuevaExpresion += partes[i];
      }
    }
    return nuevaExpresion;
  }
}

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
  bool isValid = true;
  for(int i = 0; i < expresion.length; i++){
    if(expresion[i] == ' '){
      continue;
    }
    String caracteres = expresion[i];
    if(!caracteres.contains(RegExp(r'[0-9\.+\-\*\//\^\(\)]'))){
      isValid = false;
      break;
    }
  }
  if(!isValid){
    return "expresión ilegal";
  }

  Stack<String> pila = Stack();
  String postfija = '';
  for(int i = 0; i < expresion.length; i++){
    if(expresion[i] == ' '){
      continue;
    }
    String caracteres = expresion[i];
    if(caracteres.contains(RegExp(r'[0-9\.]'))){ 
      postfija += caracteres;
    }
    else if(caracteres == '('){
      pila.push(caracteres);
    }
    else if(caracteres == ')'){
      while(pila.isNotEmpty && pila.top()!= '('){
        postfija += pila.pop();
      }
      pila.pop();
    }
    else if(caracteres.contains(RegExp(r'[a-zA-Z]'))){
      postfija += caracteres;
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

String infapost_validado(String expresion){
  Stack<String> pila = Stack();
  String postfija = '';
  for(int i = 0; i < expresion.length; i++){
    if(expresion[i] == ' '){
      continue;
    }
    String caracteres = expresion[i];
    if(caracteres.contains(RegExp(r'[a-zA-Z]'))){ 
      postfija += caracteres;
    }
    else if(caracteres == '('){
      pila.push(caracteres);
    }
    else if(caracteres == ')'){
      while(pila.isNotEmpty && pila.top()!= '('){
        postfija += pila.pop();
      }
      pila.pop();
    }
    else if(caracteres.contains(RegExp(r'[a-zA-Z]'))){
      postfija += caracteres;
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


void main(){
  print("Ingrese la expresión en notación infija:");
  print("Ingresar los valores separados para el correcto funcionamiento");
  String infija = stdin.readLineSync()?? ""; 
  String postfija = infapost(infija);
  print("Expresión en notación postfija: $postfija");
  if(postfija!= "expresión ilegal"){
    String nuevaExpresion = ExpressionConverter.generateNewExpression(infija);
    String postfija2 = infapost_validado(nuevaExpresion);
    Map<String, double> contexto = ExpressionConverter.convertExpression(infija);
    final value = ExpressionEvaluator.evaluatePostfix(expression:postfija2, context: contexto);
    print("Resultado de la comprobacion: $value");
  }
}
