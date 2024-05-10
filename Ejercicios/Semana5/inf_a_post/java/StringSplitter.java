
package infijaprudenciocolas;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/* @author Yayiiiiiir */

public class StringSplitter {
    private Queue<Character> characters;
    private String token;
    public static final String SpecialCharacters = "()+-*/^";

    public StringSplitter(String line){
        characters = new LinkedList<>();
        
        for(int i=0; i < line.length(); i++){
            characters.add(line.charAt(i));
        }
        findNextToken();
    }
    
    public boolean hasNext(){
        return token != null;
    }
    
    public String next(){
        checkToken();
        String result = token;
        findNextToken();
        return result;
    }
    
    public String peek(){
        checkToken();
        return token;
    }
    
    public void findNextToken(){
        while(!characters.isEmpty() && Character.isWhitespace(characters.peek())){
            characters.remove();
        }
        
        if(characters.isEmpty()){
            token = null;
        } else{
            token = "" + characters.remove();
            if(!SpecialCharacters.contains(token)){
                boolean done = false;
                while(!characters.isEmpty() && !done){
                    char ch = characters.peek();
                    if(Character.isWhitespace(ch) || SpecialCharacters.indexOf(ch) >= 0){
                        done = true;
                    }
                    else{
                        token = token + characters.remove();
                    }
                }
            }
        }
    }
    
    private void checkToken(){
        if(!hasNext()){
            throw new NoSuchElementException();
        }
    }
}
