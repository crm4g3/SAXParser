/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxparser;

import java.util.ArrayList;

/**
 *
 * @author codyioslaptop
 */
public class Element {
    
    public String name;
    public String value;
    public ArrayList<Attribute> attributes;
    public ArrayList<Element> children;
    
    public Element() {
        attributes = new ArrayList<Attribute>();
        children = new ArrayList<Element>();
    }
    
    public ArrayList<Element> getElements(){
        return children;
    }
}